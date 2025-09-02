package com.xtysoft.smarttask.api.controller;


import com.xtysoft.smarttask.api.dto.TaskDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.xtysoft.common.common.BaseController;
import com.xtysoft.smarttask.api.service.TaskService;
import com.xtysoft.smarttask.entity.TaskEntity;
import com.xtysoft.smarttask.mapper.TaskMapper;


/**
 * (Transfer)表控制层
 *
 * @author makejava
 * @since 2024-11-30 20:05:56
 */
@RestController@Slf4j
@RequestMapping("/api/index")
public class IndexController extends BaseController<TaskDTO, TaskEntity, TaskMapper, TaskService>  {
      public IndexController() {
        skipDefaultMethod =true;
    }

    @GetMapping("hello")
    public String hello() {
          return "hello world";
    }


}

