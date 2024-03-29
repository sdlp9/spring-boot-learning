package org.yee.learning.springboot.common.util;

import junit.framework.TestCase;

import java.net.URL;


public class ConfigUtilTest extends TestCase {

    public void testFindAsResource() {
        URL url = ConfigUtil.findAsResource("log4j.properties");
        System.out.println(url);

        url = ConfigUtil.findAsResource("0opslab-default.properties");
        System.out.println(url);

        url = ConfigUtil.findAsResource("ali.gif");
        System.out.println(url);
    }

    public void testResourcePath() {
        System.out.println(ConfigUtil.resourcePath(""));
        System.out.println(ConfigUtil.resourcePath("log4j.properties"));

    }


}