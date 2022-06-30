package com.yike.apis.task;

import com.yike.apis.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Lazy(false)
@Slf4j
@Component
public class GameTask {
    @Autowired
    private GameService gameService;

    @Async
    @Scheduled(cron = "0 30 * * * ?")
    public void GameThreePartyDataSynchronization(){
        log.info("Game：GameThreePartyDataSynchronization-------------start");
        gameService.GameThreePartyDataSynchronization();
        log.info("Game：GameThreePartyDataSynchronization-------------stop");
    }

    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 40)
    public void GameSymbolThreePartyDataSynchronization(){
        log.info("Game：GameSymbolThreePartyDataSynchronization-------------start");
        gameService.GameSymbolThreePartyDataSynchronization();
        log.info("Game：GameSymbolThreePartyDataSynchronization-------------stop");
    }

    @Async
    @Scheduled(cron = "0 30 * * * ?")
    public void commentLikedDataSynchronization(){
        log.info("Game：commentLikedDataSynchronization-------------start");
        gameService.commentLikedDataSynchronization();
        log.info("Game：commentLikedDataSynchronization-------------stop");
    }
}
