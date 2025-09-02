package com.xtysoft.smarttask.task;

import com.xtysoft.smarttask.service.CmdService;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Async;

@Component
public class MainTask {

    @Resource
    private CmdService cmdService;

    @Resource
    private TaskScheduler taskScheduler;

    @PostConstruct
    public void init() {
        // 每秒执行一次
        taskScheduler.schedule(this::execute, new CronTrigger("*/1 * * * * *"));
        // 每小时执行一次（在每小时的第0分钟执行）
        taskScheduler.schedule(this::updateCache, new CronTrigger("0 0 * * * *"));
    }

    public void execute() {
      cmdService.execute();
        // 执行您的业务逻辑
    }
    public void updateCache() {
      cmdService.updateCache();
        // 执行您的业务逻辑
    }

}