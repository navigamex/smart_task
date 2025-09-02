package com.xtysoft.smarttask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.xtysoft.smarttask.entity.TaskEntity;

@Mapper
public interface TaskMapper extends BaseMapper<TaskEntity> {
}
