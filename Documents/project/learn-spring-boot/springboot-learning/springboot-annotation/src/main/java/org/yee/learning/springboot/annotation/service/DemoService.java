package org.yee.learning.springboot.annotation.service;

/**
 * package: org.yee.learning.springboot.annotation.service
 * class: DemoService
 * describe:
 *
 * @author: brucewong
 * time: 09/02/2018 11:49
 **/
public interface DemoService {
    /**
     * 后置注解
     */
    void testAnnotation1();

    /**
     * 不带注解
     */
    void testAnnotation2();

    /**
     * 前置注解
     */
    void testAnnotation3();
}
