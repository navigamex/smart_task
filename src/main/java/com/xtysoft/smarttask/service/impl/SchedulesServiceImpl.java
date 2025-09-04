package com.xtysoft.smarttask.service.impl;

import com.xtysoft.smarttask.mapper.SchedulesMapper;
import com.xtysoft.smarttask.entity.SchedulesEntity;
import com.xtysoft.smarttask.service.SchedulesService;
import com.xtysoft.smarttask.dto.SchedulesDTO;
import com.xtysoft.common.common.BaseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
/**
 * 任务调度计划表(Schedules)表服务实现类
 *
 * @author makejava
 * @since 2025-09-04 11:37:45
 */
@Service
@Slf4j
public class SchedulesServiceImpl extends BaseService<SchedulesDTO, SchedulesEntity, SchedulesMapper>  implements SchedulesService {

}
