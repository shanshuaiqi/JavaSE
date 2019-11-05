package com.example.stream.media.component;



import com.example.stream.media.service.StreamMediaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ProjectName:ry-city
 * Description:
 * Author: ssq
 * Date: 2019-06-20
 */
@Component
@EnableScheduling
@Slf4j
public class MonitoringStreamMediaStatus {
    private static final Log LOG = LogFactory.getLog(MonitoringStreamMediaStatus.class);

    @Autowired
    private StreamMediaService streamMediaService;

    //@Scheduled(cron = "0/30 * * ? * *")
    @Scheduled(cron = "0 */2 * * * ?")
    public void monitoringStreamWithCron() throws InterruptedException {
        System.out.println(new Date());
        streamMediaService.updateTaskMap(null);
    }

}
