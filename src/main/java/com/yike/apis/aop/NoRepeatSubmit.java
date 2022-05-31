package com.yike.apis.aop;


import java.lang.annotation.*;

/**
 * @program: newpay
 * @description: Prevent double submission of comments
 * @author: lp
 * @create: 2020-07-03 15:06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {

}
