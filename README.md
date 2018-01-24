### spring boot 定时任务

## 功能
- @EnableScheduling //开启定时任务
- 配置定时规则
- 指定线程程中并行任务
- url : https://github.com/opensourceteams/n_100009_spring-boot-Scheduled-threadpool

## 定义线程池中最大线程数
```java
package com.opensourceteam.modules.configuration.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }
}

```

## 开启定时任务
```java
package com.opensourceteam.modules.configuration.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 开发人:刘文
 * 日期:  2017/11/25.
 * 功能描述:
 */
@Configuration
@ComponentScan("com.opensourceteam")
@EnableScheduling
public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

}

```

## 设置定时规则
```java
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

    @Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次
    public void cron(){
        logger.info("[SheduledService cron] 调度");
    }
}

```

## 测试类

```java
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
        while (true){
            logger.info("[SheduledServiceTest cron]");
            Thread.sleep(1000l);
        }
    }

}
```
