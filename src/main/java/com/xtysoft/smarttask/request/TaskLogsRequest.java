package com.xtysoft.smarttask.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import com.xtysoft.smarttask.entity.TaskLogsEntity;

/**
 * 任务执行日志表(TaskLogs)请求类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskLogsRequest implements Serializable {

    private static final long serialVersionUID = 1L;

/**
     * 日志唯一ID
     */    private Long id;
/**
     * 关联的任务ID
     */    private Integer taskId;
/**
     * 触发本次执行的调度ID
     */    private Integer scheduleId;
/**
     * 执行状态
     */    private String status;
/**
     * 命令执行的标准输出和错误输出
     */    private String output;
/**
     * 主命令的退出码
     */    private Integer exitCode;
/**
     * 执行开始时间（精确到微秒）
     */    private LocalDateTime startedAt;
/**
     * 执行完成时间（精确到微秒）
     */    private LocalDateTime completedAt;
/**
     * 任务执行耗时（毫秒）
     */    private Integer durationMs;

    // 你可以在这里添加校验注解，例如 @NotBlank, @NotNull
}
