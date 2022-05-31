package com.yike.apis.task;

import com.yike.apis.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Lazy(false)
@Slf4j
@Component
public class LikeTask {
    @Autowired
    private GameService gameService;


    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 30)
    public void likeInformationSynchronization(){
        log.info("Game：likeInformationSynchronization-------------start");
        //Synchronize the likes in Redis to the database
        gameService.transLikedFromRedis2DB();
        gameService.transLikedCountFromRedis2DB();
        log.info("Game：likeInformationSynchronization-------------stop");
    }
}
