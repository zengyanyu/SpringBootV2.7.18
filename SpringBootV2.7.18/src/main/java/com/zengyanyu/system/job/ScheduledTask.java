package com.zengyanyu.system.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zengyanyu
 */
@Slf4j
@Component
public class ScheduledTask {

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void testTask() {
        log.info("com.zengyanyu.system.job.ScheduledTask 定时任务执行成功");
    }
}
