//package com.yike.apis.task;
//
//import com.yike.apis.service.SearchService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Lazy(false)
//@Slf4j
//@Component
//public class SearchHeatTask {
//    @Autowired
//    private SearchService searchService;
//
//    @Async
//    @Scheduled(cron = "0 0 0 * * 7")
//    public void clearCacheSynchronizeData(){
//        log.info("SearchHeat：Clear the cache and synchronize the data-------------start");
//        searchService.clearCacheSynchronizeData();
//        log.info("SearchHeat：Clear the cache and synchronize the data-------------stop");
//    }
//}
