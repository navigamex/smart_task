package com.xtysoft.smarttask.controller;




import com.xtysoft.smarttask.dto.SchedulesDTO;
import com.xtysoft.smarttask.entity.SchedulesEntity;
import com.xtysoft.smarttask.mapper.SchedulesMapper;
import com.xtysoft.smarttask.service.impl.SchedulesServiceImpl; 
import com.xtysoft.common.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xtysoft.common.common.BaseController; 
/**
 * 任务调度计划表(Schedules)表控制层
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@RestController
@Slf4j
@RequestMapping("/schedules")
public class SchedulesController extends BaseController<SchedulesDTO, SchedulesEntity, SchedulesMapper, SchedulesServiceImpl> {

    /**
     * 构造函数
     */
    public SchedulesController() {
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
