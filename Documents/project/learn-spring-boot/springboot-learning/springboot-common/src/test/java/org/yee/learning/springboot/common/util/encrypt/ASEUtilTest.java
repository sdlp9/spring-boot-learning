package org.yee.learning.springboot.common.util.encrypt;

import org.junit.Test;

/**
 * Created by 0opslab
 */
public class ASEUtilTest {
    @Test
    public void test() throws Exception {
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        String key = "E7B3BBE7BB9FE689BEE4B88DE588B0E68C87E";
        for (String str : arr) {
            String str_en1 = ASEUtil.encrypt(key, str);
            System.out.println(str_en1);
            if (!str.equals(ASEUtil.decode(key, str_en1))) {
                System.out.printf(String.format("加密解密结果不同:%s-%s", str, str_en1));
            }
        }
    }


}