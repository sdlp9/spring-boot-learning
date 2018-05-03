package org.yee.learning.springboot.annotation.service.impl;

import org.springframework.stereotype.Service;
import org.yee.learning.springboot.annotation.annotation.SystemLogAnnotation;
import org.yee.learning.springboot.annotation.annotation.TimeConsumerAnnotation;
import org.yee.learning.springboot.annotation.service.DemoService;

/**
 * package: org.yee.learning.springboot.annotation.service.impl
 * class: DemoServiceImpl
 * describe:
 *
 * @author: brucewong
 * time: 09/02/2018 11:50
 **/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    @SystemLogAnnotation("afterSystemLogAnnotation")
    public void testAnnotation1() {
        System.out.println(" ==> 我爸是 systemLogAnnotation ~ ");
    }

    @Override
    @TimeConsumerAnnotation(value = "", name = "shakespeare")
    public void testAnnotation2() {
        for (int i = 0; i < 1000000000; i++) {

        }

        System.out.println(" ==> 我爸是 shakespeare ~ ");
    }

    @Override
    @SystemLogAnnotation("beforeSystemLogAnnotation")
    public void testAnnotation3() {
        System.out.println(" ==> 我爸是 bruceWong ~ ");
    }

}
