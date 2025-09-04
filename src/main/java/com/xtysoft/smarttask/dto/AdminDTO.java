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


    private String username;
  private String password;

}
