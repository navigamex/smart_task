package com.xtysoft.smarttask.service.impl;

import com.xtysoft.smarttask.mapper.AdminMapper;
import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.service.AdminService;
import com.xtysoft.smarttask.dto.AdminDTO;
import com.xtysoft.common.common.BaseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
/**
 * 管理员表(Admin)表服务实现类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Service
@Slf4j
public class AdminServiceImpl extends BaseService<AdminDTO, AdminEntity, AdminMapper>  implements AdminService {

}
