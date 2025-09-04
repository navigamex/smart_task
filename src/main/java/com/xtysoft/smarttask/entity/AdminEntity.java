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
 * 管理员表(Admin)实体类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Data
@TableName("admin")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEntity  extends BaseEntity implements Serializable {
    // private static final long serialVersionUID = 508568145982388063L;
/**
     * 管理员ID
     */    @TableField("id")
    private Integer id;
/**
     * 用户名
     */    @TableField("username")
    private String username;
/**
     * 密码，SHA256加密结果
     */    @TableField("password")
    private String password;
/**
     * 用于密码哈希的盐值
     */    @TableField("salt")
    private String salt;
/**
     * 状态，1=启用，0=禁用
     */    @TableField("status")
    private Integer status;
/**
     * 创建时间
     */    @TableField("created_at")
    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
