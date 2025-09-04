package com.xtysoft.smarttask.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

// 引入 Spring BeanUtils
import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.dto.AdminDTO;

/**
 * 管理员表(Admin)VO类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO implements Serializable {

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

    // 从 Entity 转换为 VO (Converts from Entity to VO)
    public static AdminVO fromEntity(AdminEntity entity) {
        AdminVO vo = new AdminVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
    
    // 从 DTO 转换为 VO (Converts from DTO to VO)
    public static AdminVO fromDTO(AdminDTO dto) {
        AdminVO vo = new AdminVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
}
