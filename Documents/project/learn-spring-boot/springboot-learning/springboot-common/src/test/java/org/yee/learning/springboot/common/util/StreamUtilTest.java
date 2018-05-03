package org.yee.learning.springboot.common.util;

import org.yee.learning.springboot.common.util.CharsetUtil;
import org.yee.learning.springboot.common.util.StreamUtil;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class StreamUtilTest {


    @Test
    public void testStream2Byte() throws Exception {
        String str = "中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        byte[] bt = StreamUtil.stream2Byte(in);
        assertEquals(str, new String(bt));

    }

    @Test
    public void testInputStream2Byte() throws Exception {
        String str = "中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        byte[] bt = StreamUtil.inputStream2Byte(in);
        assertEquals(str, new String(bt));
    }

    @Test
    public void testByte2InputStream() throws Exception {
        String str = "中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        String result = StreamUtil.streamToString(in);
        assertEquals(result, str);
    }


}