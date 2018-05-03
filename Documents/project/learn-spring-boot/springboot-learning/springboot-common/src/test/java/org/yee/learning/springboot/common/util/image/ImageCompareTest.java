package org.yee.learning.springboot.common.util.image;

import org.yee.learning.springboot.common.util.TestUtil;
import junit.framework.TestCase;

import java.io.File;


public class ImageCompareTest extends TestCase {

    public void testCompareImage() throws Exception {
        String path = TestUtil.path + "/image";
        float v = ImageCompare.compareImage(new File(path + "/1.jpg"), new File(path + "/1.jpg"));
        System.out.println(v);
    }
}