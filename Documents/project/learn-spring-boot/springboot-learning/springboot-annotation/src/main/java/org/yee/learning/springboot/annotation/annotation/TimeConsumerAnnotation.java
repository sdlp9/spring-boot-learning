package org.yee.learning.springboot.annotation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Package: org.yee.learning.springboot.annotation.annotation
 * @Class: TimeConsumer
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 12/02/2018 15:19 19
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConsumerAnnotation {
    String value() default "geek";
    String name() default "brc.";
}
