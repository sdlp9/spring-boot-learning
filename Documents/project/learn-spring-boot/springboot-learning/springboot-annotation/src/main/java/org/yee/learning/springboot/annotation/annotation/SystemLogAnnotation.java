package org.yee.learning.springboot.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * package: org.yee.learning.springboot.annotation.annotation
 * class: SystemLogAnnotation
 * describe:
 *
 * @author: brucewong
 * time: 09/02/2018 10:30
 **/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLogAnnotation {
    String value() default "log";
}

