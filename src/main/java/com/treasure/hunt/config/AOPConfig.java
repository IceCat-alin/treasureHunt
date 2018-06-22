package com.treasure.hunt.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Description 类描述：Controller 的所有方法在执行前后都会进入functionAccessCheck方法
 * @Author 创建人：linying
 * @Date 创建时间：2018/5/21 10:54
 * @Version 版本号：v1.0.0
 */
@Aspect
@Configuration
public class AOPConfig {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(AOPConfig.class);

    /**
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable 异常
     */
    @Around("@within(org.springframework.stereotype.Controller) ")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        LOG.info("####传入参数：" + Arrays.asList(args));

        Object o = pjp.proceed();

        LOG.info("####返回参数 :" + JSON.toJSONString(o));
        return o;

    }
}