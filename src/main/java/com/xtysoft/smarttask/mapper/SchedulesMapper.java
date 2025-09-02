package com.xtysoft.smarttask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtysoft.smarttask.entity.TaskEntity;
import com.xtysoft.smarttask.entity.TaskScheduleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulesMapper extends BaseMapper<TaskScheduleEntity> {
}
