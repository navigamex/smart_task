package com.xtysoft.smarttask.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xtysoft.common.common.Response;
import com.xtysoft.common.constance.BaseErrorCodeEnum;
import com.xtysoft.smarttask.constance.ErrorCodeEnum;
import com.xtysoft.smarttask.mapper.AdminMapper;
import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.service.AdminService;
import com.xtysoft.smarttask.dto.AdminDTO;
import com.xtysoft.common.common.BaseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 管理员表(Admin)表服务实现类
 *
 * @author makejava
 * @since 2025-09-04 11:51:00
 */
@Service
@Slf4j
public class AdminServiceImpl extends BaseService<AdminDTO, AdminEntity, AdminMapper>  implements AdminService {

    @Override
    public Response<String> login(AdminDTO dto) {
        // 检查用户名和密码是否为空
        if (dto == null || !StringUtils.hasText(dto.getUsername()) || !StringUtils.hasText(dto.getPassword())) {
            return Response.error(ErrorCodeEnum.LOGIN_FAILED.getCode(), ErrorCodeEnum.LOGIN_FAILED.getMessage());
        }
        
        // 根据用户名查询用户
        AdminEntity adminEntity = baseMapper.selectOne(new LambdaQueryWrapper<AdminEntity>()
                .eq(AdminEntity::getUsername, dto.getUsername())
        );
        
        // 用户不存在返回登录失败
        if (adminEntity == null) {
            return Response.error(ErrorCodeEnum.LOGIN_FAILED.getCode(), ErrorCodeEnum.LOGIN_FAILED.getMessage());
        }
        
        // 验证密码 用户提交的密码,进行sha256,再与salt混合,再次sha256 之后对比
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String firstHash = digester.digestHex(dto.getPassword());
        String salt = adminEntity.getSalt();
        String saltedPassword = firstHash + salt;
        String secondHash = digester.digestHex(saltedPassword);
        
        if (!secondHash.equals(adminEntity.getPassword())) {
            return Response.error(ErrorCodeEnum.LOGIN_FAILED.getCode(), ErrorCodeEnum.LOGIN_FAILED.getMessage());
        }
        StpUtil.login(adminEntity.getId());
        StpUtil.getSession().set("admin", adminEntity);

        return Response.success(StpUtil.getTokenValue());
    }
}