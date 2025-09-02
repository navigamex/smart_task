package com.xtysoft.smarttask.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 任务调度计划表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("schedules")

public class TaskScheduleEntity {

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

        public static ScheduleType fromValue(int value) {
            for (ScheduleType type : ScheduleType.values()) {
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

        public static MissedExecutionBehavior fromValue(int value) {
            for (MissedExecutionBehavior behavior : MissedExecutionBehavior.values()) {
                if (behavior.value == value) {
                    return behavior;
                }
            }
            throw new IllegalArgumentException("无效的超时执行行为值: " + value);
        }
    }

    /**
     * 调度计划唯一ID
     */
    private Integer id;

    /**
     * 关联的任务ID
     */
    private Integer taskId;

    /**
     * 对该调度计划的描述，如"每日备份"
     */
    private String description;

    /**
     * 类型,0 一次性 1,每秒循环 2,每分循环 3,每小时循环 4每天循环 5每周 6每月,7每年
     */
    private Integer whileType;

    /**
     * 循环值, 与上面的类型搭配使用.默认为1
     */
    private Float whileValue;

    /**
     * 执行后需要计算并更新下次执行的结果
     */
    private LocalDateTime runAt;

    /**
     * 是否启用该调度计划，0=禁用, 1=启用
     */
    private Boolean isEnabled;
    
    /**
     * 超时执行行为，0=超时不执行，1=超时执行
     */
    private Integer missedExecutionBehavior;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 最后修改时间
     */
    private LocalDateTime updatedAt;

}