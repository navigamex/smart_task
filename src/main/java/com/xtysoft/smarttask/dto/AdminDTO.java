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

import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.vo.AdminVO;

/**
 * 管理员表(Admin)DTO类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends BaseDTO implements Serializable {

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

    // 将 DTO 转换为 Entity (Converts DTO to Entity)
    public AdminEntity toEntity(AdminEntity entity) {
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
    
    // 将 DTO 转换为 VO (Converts DTO to VO)
    public AdminVO toVO(AdminVO vo) {
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
    
    // 从 Entity 转换为 DTO (Converts from Entity to DTO)
    public static AdminDTO fromEntity(AdminEntity entity) {
        AdminDTO dto = new AdminDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
