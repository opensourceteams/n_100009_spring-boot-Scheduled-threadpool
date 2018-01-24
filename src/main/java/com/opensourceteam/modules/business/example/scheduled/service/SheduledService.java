package com.opensourceteam.modules.business.example.scheduled.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
@Component
public class SheduledService {

    Logger logger = LoggerFactory.getLogger(SheduledService.class);

    @Scheduled(cron="0/1 * * * * ? ")   //每5秒执行一次
    public void cron(){
        logger.info("[SheduledService cron] 调度");
    }
}
