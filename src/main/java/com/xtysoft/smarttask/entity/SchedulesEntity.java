package com.xtysoft.smarttask.entity;

import java.time.LocalTime;
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
 * 任务调度计划表(Schedules)实体类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Data
@TableName("schedules")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulesEntity  extends BaseEntity implements Serializable {
    // private static final long serialVersionUID = 690432055704958645L;
    /**
     * 调度类型枚举
     */
    public enum ScheduleType {
        /**
         * 一次性任务
         */
        ONE_TIME(0),

        /**
         * 每秒循环
         */
        EVERY_SECOND(1),

        /**
         * 每分钟循环
         */
        EVERY_MINUTE(2),

        /**
         * 每小时循环
         */
        EVERY_HOUR(3),

        /**
         * 每天循环
         */
        EVERY_DAY(4),

        /**
         * 每周循环
         */
        EVERY_WEEK(5),

        /**
         * 每月循环
         */
        EVERY_MONTH(6),

        /**
         * 每年循环
         */
        EVERY_YEAR(7);

        private final int value;

        ScheduleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static SchedulesEntity.ScheduleType fromValue(int value) {
            for (SchedulesEntity.ScheduleType type : SchedulesEntity.ScheduleType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("无效的调度类型值: " + value);
        }
    }

    /**
     * 超时执行行为枚举
     */
    public enum MissedExecutionBehavior {
        /**
         * 超时不执行
         */
        SKIP(0),

        /**
         * 超时执行
         */
        EXECUTE(1);

        private final int value;

        MissedExecutionBehavior(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static SchedulesEntity.MissedExecutionBehavior fromValue(int value) {
            for (SchedulesEntity.MissedExecutionBehavior behavior : SchedulesEntity.MissedExecutionBehavior.values()) {
                if (behavior.value == value) {
                    return behavior;
                }
            }
            throw new IllegalArgumentException("无效的超时执行行为值: " + value);
        }
    }


    /**
     * 调度计划唯一ID
     */    @TableField("id")
    private Integer id;
/**
     * 关联的任务ID
     */    @TableField("task_id")
    private Integer taskId;
/**
     * 对该调度计划的描述，如“每日备份”
     */    @TableField("description")
    private String description;
/**
     * 类型,0 一次性 1,每秒循环 2,每分循环 3,每小时循环 4每天循环 5每周 6每月,7每年
     */    @TableField("while_type")
    private Integer whileType;
/**
     * 循环值, 与上面的类型搭配使用.默认为1
     */    @TableField("while_value")
    private Double whileValue;
/**
     * 执行后需要计算并更新下次执行的结果
     */    @TableField("run_at")
    private LocalDateTime runAt;
/**
     * 是否启用该调度计划，0=禁用, 1=启用
     */    @TableField("enabled")
    private Integer enabled;
/**
     * 错过的,是否马上执行

     */    @TableField("missed_execution_behavior")
    private Integer missedExecutionBehavior;
/**
     * 创建时间
     */    @TableField("created_at")
    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    @TableField("updated_at")
    private LocalDateTime updatedAt;
/**
     * 自动允许开始时间
     */    @TableField("allow_run_start_time")
    private LocalTime allowRunStartTime;
/**
     * 自动允许结束时间
     */    @TableField("allow_run_end_time")
    private LocalTime allowRunEndTime;
}
