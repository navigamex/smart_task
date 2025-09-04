package com.xtysoft.smarttask.service;


import com.xtysoft.smarttask.entity.TasksEntity;
import com.xtysoft.smarttask.entity.SchedulesEntity;
import com.xtysoft.smarttask.mapper.SchedulesMapper;
import com.xtysoft.smarttask.mapper.TasksMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CmdService {

    @Resource
    SchedulesMapper schedulesMapper;
    @Resource
    TasksMapper taskMapper;

    // 存储任务实体的Map，供execute方法使用
    private Map<Integer, TasksEntity> taskMap = new HashMap<>();

    // 存储调度计划列表的缓存，避免每次都查询数据库
    private List<SchedulesEntity> schedulesCache;

    @PostConstruct
    public void init() {
        updateCache();
    }
    public void execute() {

        //检查schedules列表, 有没有执行时间在当前时间的.如果有就执行task的命令
        //如果执行时间在本次之前, 根据类型和循环方式计算出下次执行的时间 更新到库 并调用updateCache
        //
        LocalDateTime now = LocalDateTime.now();
        boolean needUpdateCache = false;

        for (SchedulesEntity schedule : schedulesCache) {


            if (schedule.getEnabled() == 1 && schedule.getRunAt() != null &&
                    (now.truncatedTo(ChronoUnit.SECONDS).equals(schedule.getRunAt().truncatedTo(ChronoUnit.SECONDS)))) {                // 获取对应的任务
                TasksEntity task = taskMap.get(schedule.getTaskId());
                if (task != null && task.getEnabled() == 1) {
                    // 判断当前时间是否在允许执行的时间范围内
                    if (isExecutionTimeValid(schedule, now)) {
                        log.info("正常, 执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                        executeCommand(task.getCommand());
                    } else {
                        log.info("正常时间匹配但不在允许时间范围内, 不执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                    }

                    // 计算下次执行时间
                    LocalDateTime nextRunAt = calculateNextRunTime(schedule, now);
                    if (nextRunAt != null) {
                        schedule.setRunAt(nextRunAt);
                        schedulesMapper.updateById(schedule);
                    }
                }
            }



            // 检查调度是否启用
            if (schedule.getEnabled() == 1 && schedule.getRunAt() != null && schedule.getRunAt().isBefore(now)) {
                // 获取对应的任务
                TasksEntity task = taskMap.get(schedule.getTaskId());
                if (task != null && task.getEnabled() == 1) {
                    // 检查超时执行行为
                    boolean shouldExecute = true;
                    if (schedule.getMissedExecutionBehavior() != null) {
                        SchedulesEntity.MissedExecutionBehavior behavior =
                                SchedulesEntity.MissedExecutionBehavior.fromValue(schedule.getMissedExecutionBehavior());
                        if (behavior == SchedulesEntity.MissedExecutionBehavior.SKIP) {
                            // 超时不执行
                            shouldExecute = false;
                        }
                    }

                    if (shouldExecute) {
                        // 判断当前时间是否在允许执行的时间范围内
                        if (isExecutionTimeValid(schedule, now)) {
                            // 执行任务命令
                            log.info("超时, 执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                            // 这里应该实际执行任务命令
                            executeCommand(task.getCommand());
                        } else {
                            log.info("超时时间匹配但不在允许时间范围内, 不执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                        }
                    }

                    // 根据类型和循环方式计算下次执行时间
                    LocalDateTime nextRunAt = calculateNextRunTime(schedule, now);
                    if (nextRunAt == null) {
                        schedule.setEnabled(0);
                    }

                    schedule.setRunAt(nextRunAt);
                    schedulesMapper.updateById(schedule);
                    needUpdateCache = true;

                }
            }

        }

        // 如果有调度计划更新，则更新缓存
        if (needUpdateCache) {
            updateCache();
        }

    }

    /**
     * 根据调度类型和值计算下次执行时间
     *
     * @param schedule 调度实体
     * @param now 当前时间
     * @return 下次执行时间，如果是一次性任务则返回null
     */
    private LocalDateTime calculateNextRunTime(SchedulesEntity schedule, LocalDateTime now) {
        Integer whileType = schedule.getWhileType();
        Double whileValue = schedule.getWhileValue();
        LocalDateTime runAt = schedule.getRunAt();
        if (whileValue == null) {
            whileValue = 1.0;
        }

        try {
            SchedulesEntity.ScheduleType scheduleType = SchedulesEntity.ScheduleType.fromValue(whileType);

            switch (scheduleType) {
                case ONE_TIME: // 一次性任务
                    return null;
                case EVERY_SECOND: // 每秒循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间，而不是当前时间
                    return runAt != null ? runAt.plusSeconds(whileValue.longValue()) : now.plusSeconds(whileValue.longValue());
                case EVERY_MINUTE: // 每分钟循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusMinutes(whileValue.longValue()) : now.plusMinutes(whileValue.longValue());
                case EVERY_HOUR: // 每小时循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusHours(whileValue.longValue()) : now.plusHours(whileValue.longValue());
                case EVERY_DAY: // 每天循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusDays(whileValue.longValue()) : now.plusDays(whileValue.longValue());
                case EVERY_WEEK: // 每周循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusWeeks(whileValue.longValue()) : now.plusWeeks(whileValue.longValue());
                case EVERY_MONTH: // 每月循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusMonths(whileValue.longValue()) : now.plusMonths(whileValue.longValue());
                case EVERY_YEAR: // 每年循环
                    // 修复时间计算逻辑：基于上次执行时间计算下次执行时间
                    return runAt != null ? runAt.plusYears(whileValue.longValue()) : now.plusYears(whileValue.longValue());
                default:
                    return null;
            }
        } catch (IllegalArgumentException e) {
            log.warn("未知的调度类型: {}", whileType);
            return null;
        }
    }

    public void updateCache() {
        //        taskMapper 查出所有实体
//        schedulesMapper 查出所有实体
        //把task查出的实体放到一个map中
        //schedulesMapper 遍历,拿到所有实体 根据上面的实体拿到自己的对应task信息
        //然后写入成员变量中, 后备execute使用

        List<TasksEntity> tasks = taskMapper.selectList(null);
        taskMap.clear();
        for (TasksEntity task : tasks) {
            taskMap.put(task.getId(), task);
        }

        // 更新调度计划缓存
        schedulesCache = schedulesMapper.selectList(null);

        log.info("updateCache更新了 {} 个任务实体和 {} 个调度计划", taskMap.size(), schedulesCache.size());
    }

    /**
     * 执行系统命令
     *
     * @param command 要执行的命令
     */
    private void executeCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            log.warn("命令为空，无法执行");
            return;
        }

        try {
            log.info("开始执行命令: {}", command);
            
            // 使用 Runtime 执行命令
            Process process = Runtime.getRuntime().exec(command);
            
            // 等待命令执行完成
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                log.info("命令执行成功: {}", command);
            } else {
                log.error("命令执行失败，退出码: {}，命令: {}", exitCode, command);
            }
        } catch (Exception e) {
            log.error("执行命令时发生异常: {}", command, e);
        }
    }

    /**
     * 判断当前时间是否在允许执行的时间范围内
     * @param schedule 调度实体
     * @param now 当前时间
     * @return 是否在允许执行的时间范围内
     */
    private boolean isExecutionTimeValid(SchedulesEntity schedule, LocalDateTime now) {
        // 获取允许执行的开始和结束时间
        LocalTime allowRunStartTime = schedule.getAllowRunStartTime();
        LocalTime allowRunEndTime = schedule.getAllowRunEndTime();
        
        // 如果没有设置时间范围限制，则允许执行
        if (allowRunStartTime == null || allowRunEndTime == null) {
            return true;
        }
        
        // 获取当前时间的时间部分
        LocalTime currentTime = now.toLocalTime();
        
        // 判断当前时间是否在允许的时间范围内（包含边界）
        if (allowRunStartTime.isBefore(allowRunEndTime)) {
            // 正常情况：开始时间早于结束时间（如 09:00 - 17:00）
            return !currentTime.isBefore(allowRunStartTime) && !currentTime.isAfter(allowRunEndTime);
        } else {
            // 跨天情况：开始时间晚于结束时间（如 22:00 - 06:00）
            return !currentTime.isBefore(allowRunStartTime) || !currentTime.isAfter(allowRunEndTime);
        }
    }
}