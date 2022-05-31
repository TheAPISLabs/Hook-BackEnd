//package com.yike.apis.task;
//
//import com.yike.apis.service.TrachService;
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
//public class TrachTask {
//    @Autowired
//    private TrachService trachService;
//
//    @Async
//    @Scheduled(fixedDelay = 1000 * 60 * 60)
//    public void setPrice(){
//        log.info("setPrice：setPrice-------------start");
//        trachService.setPrice();
//        log.info("setPrice：setPrice-------------stop");
//    }
//}
