package com.xtysoft.smarttask.controller;




import com.xtysoft.smarttask.dto.TasksDTO;
import com.xtysoft.smarttask.entity.TasksEntity;
import com.xtysoft.smarttask.mapper.TasksMapper;
import com.xtysoft.smarttask.service.impl.TasksServiceImpl; 
import com.xtysoft.common.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xtysoft.common.common.BaseController; 
/**
 * 任务定义表(Tasks)表控制层
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@RestController
@Slf4j
@RequestMapping("/tasks")
public class TasksController extends BaseController<TasksDTO, TasksEntity, TasksMapper, TasksServiceImpl> {

    /**
     * 构造函数
     */
    public TasksController() {
        // 设置为true以跳过BaseController中的默认方法实现
        skipDefaultMethod = true;
    }

    // 您可以在下方根据具体业务需求添加自定义的Controller方法
    // 例如：
    /*
    @GetMapping("/myCustomEndpoint")
    public R myCustomMethod() {
        // 自定义逻辑
        return success();
    }
    */
}
