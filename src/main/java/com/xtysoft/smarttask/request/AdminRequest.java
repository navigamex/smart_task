package com.xtysoft.smarttask.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import com.xtysoft.smarttask.entity.AdminEntity;

/**
 * 管理员表(Admin)请求类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest implements Serializable {

    private static final long serialVersionUID = 1L;

/**
     * 管理员ID
     */    private Integer id;
/**
     * 用户名
     */    private String username;
/**
     * 密码，SHA256加密结果
     */    private String password;
/**
     * 用于密码哈希的盐值
     */    private String salt;
/**
     * 状态，1=启用，0=禁用
     */    private Integer status;
/**
     * 创建时间
     */    private LocalDateTime createdAt;
/**
     * 最后修改时间
     */    private LocalDateTime updatedAt;

    // 你可以在这里添加校验注解，例如 @NotBlank, @NotNull
}
