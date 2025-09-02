package com.xtysoft.smarttask.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xtysoft.common.common.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 任务实体类 (Task Entity Class)
 * 对应数据库中的 tasks 表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tasks")
public class TaskEntity extends BaseEntity {

    /**
     * 任务唯一ID (Task unique ID)
     */
    private Integer id;

    /**
     * 任务名称，用于Web界面显示 (Task name, for Web display)
     */
    private String name;

    /**
     * 任务的详细描述 (Detailed task description)
     */
    private String description;

    /**
     * 要执行的主命令 (Main command to be executed)
     */
    private String command;

    /**
     * 主命令成功后执行的命令 (Command to execute on main command success)
     */
    private String commandOnSuccess;

    /**
     * 主命令失败后执行的命令 (Command to execute on main command failure)
     */
    private String commandOnFailure;

    /**
     * 任务执行超时时间（秒），0为不限制 (Task execution timeout in seconds, 0 for no limit)
     */
    private Integer timeoutSeconds;

    /**
     * 是否启用该任务，0=禁用, 1=启用 (Whether to enable this task, 0=disabled, 1=enabled)
     */
    private Boolean isEnabled;

    /**
     * 创建时间 (Creation timestamp)
     */
    private LocalDateTime createdAt;

    /**
     * 最后修改时间 (Last modified timestamp)
     */
    private LocalDateTime updatedAt;
}
