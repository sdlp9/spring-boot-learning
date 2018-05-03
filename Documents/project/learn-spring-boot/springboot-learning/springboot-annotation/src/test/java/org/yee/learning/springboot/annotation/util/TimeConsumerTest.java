package org.yee.learning.springboot.annotation.util;

import org.junit.Test;
import org.yee.learning.springboot.annotation.annotation.SystemLogAnnotation;
import org.yee.learning.springboot.annotation.annotation.TimeConsumerAnnotation;

/**
 * @Package: org.yee.learning.springboot.annotation.util
 * @Class: TimeConsumerTest
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 12/02/2018 15:44 44
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 **/
public class TimeConsumerTest {
    @Test
    @TimeConsumerAnnotation
    @SystemLogAnnotation("systemlogannotation")
    public void testTimeConsumer() {
        for (int i = 0; i < 1111; i++) {
        }
    }
}