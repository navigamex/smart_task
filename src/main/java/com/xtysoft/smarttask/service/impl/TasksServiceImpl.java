package com.xtysoft.smarttask.service.impl;

import com.xtysoft.smarttask.mapper.TasksMapper;
import com.xtysoft.smarttask.entity.TasksEntity;
import com.xtysoft.smarttask.service.TasksService;
import com.xtysoft.smarttask.dto.TasksDTO;
import com.xtysoft.common.common.BaseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
/**
 * 任务定义表(Tasks)表服务实现类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Service
@Slf4j
public class TasksServiceImpl extends BaseService<TasksDTO, TasksEntity, TasksMapper>  implements TasksService {

}
