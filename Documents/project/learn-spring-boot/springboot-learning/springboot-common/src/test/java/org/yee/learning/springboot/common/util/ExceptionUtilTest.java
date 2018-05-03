package org.yee.learning.springboot.common.util;

import org.junit.Test;

/**
 * 测试异常工具类
 */
public class ExceptionUtilTest {
    @Test
    public void testStackTraceToString() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(ExceptionUtil.stackTraceToString(e, "org.yee.learning.springboot.common"));
            System.out.println(ExceptionUtil.stackTraceToString(e));
        }
    }

}