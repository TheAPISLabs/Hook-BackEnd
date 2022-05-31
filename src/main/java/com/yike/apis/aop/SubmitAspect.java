package com.yike.apis.aop;


import cn.hutool.crypto.SecureUtil;
import com.yike.apis.utils.IpHelper;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @program: newpay
 * @description: Prevent duplicate submission of code implementations
 * @author: xpx
 * @create: 2020-07-03 15:06
 */
@Aspect
@Configuration
@Slf4j
public class SubmitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("execution(public * *(..)) && @annotation(com.yike.apis.aop.NoRepeatSubmit)")
    public Object interceptor(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String name = point.getSignature().getName();
        if(method.equalsIgnoreCase("post")){

            
            //Get submit parameters
            Object[] paramsObj = point.getArgs();
            //Access to build value
            String redisKey = SecureUtil.md5(url+method+ip+name+this.getkk(paramsObj));
            //Add to the cache, +1 each time
            long count = redisTemplate.opsForValue().increment(redisKey, 1);
            //Count == indicates the first submission
            if (count == 1) {
                //Set the cache expiration time to 2 seconds, no repeat commit within 2 seconds
                redisTemplate.expire(redisKey, 5, TimeUnit.SECONDS);
            }

            //If multiple requests are made without the cache being invalidated, a request failure message is returned
            if (count > 1) {
                //Everyone here
                return ResponseDataUtil.buildError("Do not submit twice");
            }
        }
        //Implement target method
        return point.proceed();
    }


    public String getkk(Object[] o){
        String str = "";
        for(int i = 0;i < o.length;i++){
            str = str + o[i];
        }
        return str;
    }

}

