package org.yee.learning.springboot.annotation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yee.learning.springboot.annotation.annotation.TimeConsumerAnnotation;

/**
 * @Package: org.yee.learning.springboot.annotation.util
 * @Class: TimeConsumer
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 12/02/2018 15:32 32
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 **/
@Aspect
@Component
public class TimeConsumerAop {
    private final static Logger logger = LoggerFactory.getLogger(TimeConsumerAop.class);
    public static final String POINT = ("execution (* org.yee.learning.springboot.*..*.*(..))&& @annotation(timeConsumerAnnotation)");

    @Pointcut(POINT)
    public void cut(TimeConsumerAnnotation timeConsumerAnnotation) {
    }

    /**
     * 统计方法执行耗时Around环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around("cut(timeConsumerAnnotation)")
    public Object timeAround(ProceedingJoinPoint joinPoint, TimeConsumerAnnotation timeConsumerAnnotation) {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }

        if ("".equals(timeConsumerAnnotation.value())) {
            logger.error("you had make a mistake.");
        }

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);

        return obj;
    }

    /**
     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
     *
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        if (diffTime > 0) {
            logger.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
        }
    }

}
