package com.xtysoft.smarttask.admin.controller;

import com.xtysoft.common.common.Response;
import com.xtysoft.smarttask.service.CmdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/admin/misc")
public class MiscController {
    @Resource
    private CmdService cmdService;
    @RequestMapping("refreshCache")

    public Response<Boolean> refreshCache() {
        cmdService.updateCache();
        return Response.success(true);
    }
}
