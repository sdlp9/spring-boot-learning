package org.yee.learning.springboot.filehanding.generator.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.yee.learning.springboot.common.util.RandomUtil;
import org.yee.learning.springboot.filehanding.generator.domain.BillDemo;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @Package: org.yee.learning.springboot.filehanding.generator.util
 * @Class: FileUtilTest
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 12/02/2018 11:40 40
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 **/
public class FileUtilTest {

    public String geneartorData(int count) {
        int cun = count == 0 ? 1 : count;
        StringBuffer stringBuffer = new StringBuffer();
        BillDemo billDemo;
        for (int i = 0; i < cun; i++) {
            billDemo = new BillDemo();
            billDemo.setAmount(String.valueOf(RandomUtil.integer(1, 9999)));
            billDemo.setCooperator(RandomUtil.LowerString(8));
            billDemo.setDrawBatchNo(RandomUtil.number(9));
            billDemo.setFee(RandomUtil.number(11));
            billDemo.setMerchantCode(RandomUtil.UUID());
            billDemo.setPayType(String.valueOf(RandomUtil.integer(0, 1)));
            billDemo.setOriReqMsgId(RandomUtil.number(10));
            stringBuffer.append(JSON.toJSON(billDemo) + "\r\n");
        }
        return stringBuffer.toString();
    }

    @Test
    public void read() {
    }

    @Test
    public void append2File() {
    }

    @Test
    public void writeByNio() throws IOException {
        for (int i = 0; i < 400; i++) {
            FileUtil.writeByNio(
                    geneartorData(1000),
                    "/Users/brucewong/Documents/conf/data/cctv.txt"
            );
        }
    }

    @Test
    public void readByNio() {
    }

    @Test
    public void readFile() {
    }

    @Test
    public void largeFileIO() {
    }

    @Test
    public void getFileLines() {
    }

    @Test
    public void main() {
    }
}