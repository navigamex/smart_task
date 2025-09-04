package com.xtysoft.smarttask.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import com.xtysoft.smarttask.entity.TasksEntity;

/**
 * 任务定义表(Tasks)请求类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksRequest implements Serializable {

    private static final long serialVersionUID = 1L;

/**
     * 任务唯一ID
     */    private Integer id;
/**
     * 任务名称，用于Web界面显示
     */    private String name;
/**
     * 任务的详细描述
     */    private String description;
/**
     * 要执行的主命令（包含路径和参数）
     */    private String command;
/**
     * 主命令成功后（exit code = 0）执行的命令
     */    private String commandOnSuccess;
/**
     * 主命令失败后（exit code != 0）执行的命令
     */    private String commandOnFailure;
/**
     * 任务执行超时时间（秒），0为不限制
     */    private Integer timeoutSeconds;
/**
     * 是否启用该任务，0=禁用, 1=启用
     */    private Integer enabled;
/**
     * 创建时间
     */    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    private LocalDateTime updatedAt;

    // 你可以在这里添加校验注解，例如 @NotBlank, @NotNull
}
