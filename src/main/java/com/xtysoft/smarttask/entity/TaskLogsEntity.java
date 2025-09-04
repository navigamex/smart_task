package com.xtysoft.smarttask.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

// 引入你需要的注解
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime; // 新增导入
import com.xtysoft.common.common.BaseEntity;

/**
 * 任务执行日志表(TaskLogs)实体类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@TableName("task_logs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskLogsEntity  extends BaseEntity implements Serializable {
    // private static final long serialVersionUID = 821225904135818588L;
/**
     * 日志唯一ID
     */    @TableField("id")
    private Long id;
/**
     * 关联的任务ID
     */    @TableField("task_id")
    private Integer taskId;
/**
     * 触发本次执行的调度ID
     */    @TableField("schedule_id")
    private Integer scheduleId;
/**
     * 执行状态
     */    @TableField("status")
    private String status;
/**
     * 命令执行的标准输出和错误输出
     */    @TableField("output")
    private String output;
/**
     * 主命令的退出码
     */    @TableField("exit_code")
    private Integer exitCode;
/**
     * 执行开始时间（精确到微秒）
     */    @TableField("started_at")
    private LocalDateTime startedAt;
/**
     * 执行完成时间（精确到微秒）
     */    @TableField("completed_at")
    private LocalDateTime completedAt;
/**
     * 任务执行耗时（毫秒）
     */    @TableField("duration_ms")
    private Integer durationMs;
}
