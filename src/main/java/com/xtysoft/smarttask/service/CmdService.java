package com.xtysoft.smarttask.service;


import com.xtysoft.smarttask.entity.TaskEntity;
import com.xtysoft.smarttask.entity.TaskScheduleEntity;
import com.xtysoft.smarttask.mapper.SchedulesMapper;
import com.xtysoft.smarttask.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    TaskMapper taskMapper;

    // 存储任务实体的Map，供execute方法使用
    private Map<Integer, TaskEntity> taskMap = new HashMap<>();

    // 存储调度计划列表的缓存，避免每次都查询数据库
    private List<TaskScheduleEntity> schedulesCache;

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

        for (TaskScheduleEntity schedule : schedulesCache) {


            if (schedule.getIsEnabled() && schedule.getRunAt() != null &&
                    (now.truncatedTo(ChronoUnit.SECONDS).equals(schedule.getRunAt().truncatedTo(ChronoUnit.SECONDS)))) {                // 获取对应的任务
                TaskEntity task = taskMap.get(schedule.getTaskId());
                if (task != null && task.getIsEnabled()) {
                    log.info("正常, 执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                    // executeCommand(task.getCommand());

                    // 计算下次执行时间
                    LocalDateTime nextRunAt = calculateNextRunTime(schedule, now);
                    if (nextRunAt != null) {
                        schedule.setRunAt(nextRunAt);
                        schedulesMapper.updateById(schedule);
                    }
                }
            }



            // 检查调度是否启用
            if (schedule.getIsEnabled() && schedule.getRunAt() != null && schedule.getRunAt().isBefore(now)) {
                // 获取对应的任务
                TaskEntity task = taskMap.get(schedule.getTaskId());
                if (task != null && task.getIsEnabled()) {
                    // 检查超时执行行为
                    boolean shouldExecute = true;
                    if (schedule.getMissedExecutionBehavior() != null) {
                        TaskScheduleEntity.MissedExecutionBehavior behavior =
                                TaskScheduleEntity.MissedExecutionBehavior.fromValue(schedule.getMissedExecutionBehavior());
                        if (behavior == TaskScheduleEntity.MissedExecutionBehavior.SKIP) {
                            // 超时不执行
                            shouldExecute = false;
                        }
                    }

                    if (shouldExecute) {
                        // 执行任务命令
                        log.info("超时, 执行任务ID: {}, 任务名称: {}", task.getId(), task.getName());
                        // 这里应该实际执行任务命令
                        // executeCommand(task.getCommand());
                    }

                    // 根据类型和循环方式计算下次执行时间
                    LocalDateTime nextRunAt = calculateNextRunTime(schedule, now);
                    if (nextRunAt == null) {
                        schedule.setIsEnabled(false);
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
    private LocalDateTime calculateNextRunTime(TaskScheduleEntity schedule, LocalDateTime now) {
        Integer whileType = schedule.getWhileType();
        Float whileValue = schedule.getWhileValue();
        LocalDateTime runAt = schedule.getRunAt();
        if (whileValue == null) {
            whileValue = 1.0f;
        }

        try {
            TaskScheduleEntity.ScheduleType scheduleType = TaskScheduleEntity.ScheduleType.fromValue(whileType);

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

        List<TaskEntity> tasks = taskMapper.selectList(null);
        taskMap.clear();
        for (TaskEntity task : tasks) {
            taskMap.put(task.getId(), task);
        }

        // 更新调度计划缓存
        schedulesCache = schedulesMapper.selectList(null);

        log.info("updateCache更新了 {} 个任务实体和 {} 个调度计划", taskMap.size(), schedulesCache.size());
    }
}