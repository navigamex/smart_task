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

import com.xtysoft.smarttask.entity.TaskLogsEntity;
import com.xtysoft.smarttask.vo.TaskLogsVO;

/**
 * 任务执行日志表(TaskLogs)DTO类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskLogsDTO extends BaseDTO implements Serializable {

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

    // 将 DTO 转换为 Entity (Converts DTO to Entity)
    public TaskLogsEntity toEntity(TaskLogsEntity entity) {
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
    
    // 将 DTO 转换为 VO (Converts DTO to VO)
    public TaskLogsVO toVO(TaskLogsVO vo) {
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
    
    // 从 Entity 转换为 DTO (Converts from Entity to DTO)
    public static TaskLogsDTO fromEntity(TaskLogsEntity entity) {
        TaskLogsDTO dto = new TaskLogsDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
