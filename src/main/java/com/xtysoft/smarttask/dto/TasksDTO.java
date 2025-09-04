package com.xtysoft.smarttask.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

// 引入 Spring BeanUtils
import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.xtysoft.common.common.BaseDTO;

import com.xtysoft.smarttask.entity.TasksEntity;
import com.xtysoft.smarttask.vo.TasksVO;

/**
 * 任务定义表(Tasks)DTO类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO extends BaseDTO implements Serializable {

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

    // 将 DTO 转换为 Entity (Converts DTO to Entity)
    public TasksEntity toEntity(TasksEntity entity) {
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
    
    // 将 DTO 转换为 VO (Converts DTO to VO)
    public TasksVO toVO(TasksVO vo) {
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
    
    // 从 Entity 转换为 DTO (Converts from Entity to DTO)
    public static TasksDTO fromEntity(TasksEntity entity) {
        TasksDTO dto = new TasksDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
