package com.xtysoft.smarttask.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

// 引入 Spring BeanUtils
import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import com.xtysoft.smarttask.entity.SchedulesEntity;
import com.xtysoft.smarttask.dto.SchedulesDTO;

/**
 * 任务调度计划表(Schedules)VO类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulesVO implements Serializable {

    private static final long serialVersionUID = 1L;

/**
     * 调度计划唯一ID
     */    private Integer id;
/**
     * 关联的任务ID
     */    private Integer taskId;
/**
     * 对该调度计划的描述，如“每日备份”
     */    private String description;
/**
     * 类型,0 一次性 1,每秒循环 2,每分循环 3,每小时循环 4每天循环 5每周 6每月,7每年
     */    private Integer whileType;
/**
     * 循环值, 与上面的类型搭配使用.默认为1
     */    private Double whileValue;
/**
     * 执行后需要计算并更新下次执行的结果
     */    private LocalDateTime runAt;
/**
     * 是否启用该调度计划，0=禁用, 1=启用
     */    private Integer enabled;
/**
     * 错过的,是否马上执行

     */    private Integer missedExecutionBehavior;
/**
     * 创建时间
     */    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    private LocalDateTime updatedAt;
/**
     * 自动允许开始时间
     */    private LocalTime allowRunStartTime;
/**
     * 自动允许结束时间
     */    private LocalTime allowRunEndTime;

    // 从 Entity 转换为 VO (Converts from Entity to VO)
    public static SchedulesVO fromEntity(SchedulesEntity entity) {
        SchedulesVO vo = new SchedulesVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
    
    // 从 DTO 转换为 VO (Converts from DTO to VO)
    public static SchedulesVO fromDTO(SchedulesDTO dto) {
        SchedulesVO vo = new SchedulesVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
}
