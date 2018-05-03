package org.yee.learning.springboot.common.util.algorithmImpl;

import org.yee.learning.springboot.common.util.TestUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class FileTypeImplTest extends TestCase {

    @Test
    public void testFileType() {
        assertEquals("gif", FileTypeImpl.getFileType(new File(TestUtil.path + "ali.gif")));
        assertEquals("png", FileTypeImpl.getFileType(new File(TestUtil.path + "tgepng")));
    }

}