package com.xtysoft.smarttask.api.service;

import com.xtysoft.common.common.BaseService;
import org.springframework.stereotype.Service;
import com.xtysoft.smarttask.api.dto.TaskDTO;
import com.xtysoft.smarttask.entity.TaskEntity;
import com.xtysoft.smarttask.mapper.TaskMapper;

@Service
public class TaskService extends BaseService<TaskDTO, TaskEntity, TaskMapper> {
}
