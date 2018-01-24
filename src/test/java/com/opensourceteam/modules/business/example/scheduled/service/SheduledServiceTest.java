package com.opensourceteam.modules.business.example.scheduled.service;

import com.opensourceteam.modules.configuration.spring.CustomMVCConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomMVCConfiguration.class)
public class SheduledServiceTest {

    Logger logger = LoggerFactory.getLogger(SheduledServiceTest.class);
    @Test
    public void cron() throws Exception {

/*        while (true){
            logger.info("[SheduledServiceTest cron] i:{}",i++);
            Thread.sleep(1000l);
        }*/


        for(int i = 0 ; i < 5 ;i ++){
            logger.info("[SheduledServiceTest cron] i:{}",i++);
        }
        while (true){
            Thread.sleep(1000l);
        }
    }

}