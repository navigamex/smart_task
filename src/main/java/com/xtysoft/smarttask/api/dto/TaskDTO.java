package com.xtysoft.smarttask.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.xtysoft.common.common.BaseDTO;

import java.io.Serializable;


/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-11-17 21:32:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO extends BaseDTO implements Serializable {



}

