package org.yee.learning.springboot.annotation.aop;

import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yee.learning.springboot.annotation.annotation.SystemLogAnnotation;

import javax.servlet.http.HttpServletRequest;

/**
 * package: org.yee.learning.springboot.annotation.aop
 * class: SystemLogAop
 * describe:
 *
 * @author: brucewong
 * time: 09/02/2018 10:58
 **/
@Component
@Aspect
public class SystemLogAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAop.class);

    /**
     * 要处理的方法，包名 + 类名 + 方法名
     */
    @Pointcut("execution(* org.yee.learning.springboot.*..*.*(..)) && @annotation(systemLogAnnotation)")
    public void cut(SystemLogAnnotation systemLogAnnotation) {
    }

    /**
     * 后置通知，扫描匹配的表达式包及此包下的所有带有SystemLogAnnotation注解的方法
     */
    @Before("cut(systemLogAnnotation)")
    public void doBeforeAdvice(JoinPoint joinPoint, SystemLogAnnotation systemLogAnnotation) {
        LOGGER.info("==> 用户操作日志-前置通知开始执行......\r\n");
        String value = systemLogAnnotation.value();
        addSystemLog(value);
        LOGGER.info("==> 用户操作日志-前置通知结束执行......\r\n");
    }

    /**
     * 后置通知，扫描匹配的表达式包及此包下的所有带有SystemLogAnnotation注解的方法
     */
    @After("cut(systemLogAnnotation)")
    public void doAfterAdvice(JoinPoint joinPoint, SystemLogAnnotation systemLogAnnotation) {
        LOGGER.info("==> 用户操作日志-后置通知开始执行......\r\n");
        String value = systemLogAnnotation.value();
        addSystemLog(value);
        LOGGER.info("==> 用户操作日志-后置通知结束执行......\r\n");
    }

    /**
     * 保存操作日志
     */
    public void addSystemLog(String operationContent) {
        // 获取此次请求的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 浏览器标标识
        String webIdentifiy = getBrowserInfo(request);

        System.out.println("浏览器" + webIdentifiy);

    }

    /**
     * 根据request获取前台浏览器标识
     */
    private static String getBrowserInfo(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String browserInfo = userAgent.getBrowser().toString();
        return browserInfo;
    }
}
