package com.xtysoft.smarttask.service.impl;

import com.xtysoft.smarttask.mapper.TaskLogsMapper;
import com.xtysoft.smarttask.entity.TaskLogsEntity;
import com.xtysoft.smarttask.service.TaskLogsService;
import com.xtysoft.smarttask.dto.TaskLogsDTO;
import com.xtysoft.common.common.BaseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
/**
 * 任务执行日志表(TaskLogs)表服务实现类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Service
@Slf4j
public class TaskLogsServiceImpl extends BaseService<TaskLogsDTO, TaskLogsEntity, TaskLogsMapper>  implements TaskLogsService {

}
