package com.xtysoft.smarttask.service;
 
import com.xtysoft.common.common.Response;
import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.dto.AdminDTO;
import com.xtysoft.smarttask.mapper.AdminMapper;
 

/**
 * 管理员表(Admin)表服务接口
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
public interface AdminService  {

    Response<String> login(AdminDTO dto);
}
