package com.xtysoft.smarttask.entity;

import java.util.Date;
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
 * 任务定义表(Tasks)实体类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@TableName("tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksEntity  extends BaseEntity implements Serializable {
    // private static final long serialVersionUID = 918728016519254980L;
/**
     * 任务唯一ID
     */    @TableField("id")
    private Integer id;
/**
     * 任务名称，用于Web界面显示
     */    @TableField("name")
    private String name;
/**
     * 任务的详细描述
     */    @TableField("description")
    private String description;
/**
     * 要执行的主命令（包含路径和参数）
     */    @TableField("command")
    private String command;
/**
     * 主命令成功后（exit code = 0）执行的命令
     */    @TableField("command_on_success")
    private String commandOnSuccess;
/**
     * 主命令失败后（exit code != 0）执行的命令
     */    @TableField("command_on_failure")
    private String commandOnFailure;
/**
     * 任务执行超时时间（秒），0为不限制
     */    @TableField("timeout_seconds")
    private Integer timeoutSeconds;
/**
     * 是否启用该任务，0=禁用, 1=启用
     */    @TableField("enabled")
    private Integer enabled;
/**
     * 创建时间
     */    @TableField("created_at")
    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
