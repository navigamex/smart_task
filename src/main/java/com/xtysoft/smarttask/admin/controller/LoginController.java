package com.xtysoft.smarttask.admin.controller;

import com.xtysoft.common.common.BaseController;
import com.xtysoft.common.common.Response;
import com.xtysoft.smarttask.dto.AdminDTO;
import com.xtysoft.smarttask.entity.AdminEntity;
import com.xtysoft.smarttask.mapper.AdminMapper;
import com.xtysoft.smarttask.service.CmdService;
import com.xtysoft.smarttask.service.impl.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/admin/login")
public class LoginController extends BaseController<AdminDTO, AdminEntity, AdminMapper, AdminServiceImpl> {
    @PostMapping("login")
    public Response<String> login(@RequestBody AdminDTO dto) {

        return service.login(dto);
    }
}
