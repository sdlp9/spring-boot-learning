package org.yee.learning.springboot.filehanding.filedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @version 1.0.0
 * @package: org.yee.learing.springboot.filehanding
 * @class: FileStreamDemo
 * @describe: springboot-learning
 * @author: brucewong
 * @time: 11/02/2018 15:26 26
 **/
public class FileStreamDemo {
    public static void main(String[] args) {
        try {
            // 源文件
            File fSource = new File(args[0]);
            try {
                // 目地文件
                File fDeist = new File(args[1]);

                // 创建流
                FileInputStream fis = new FileInputStream(fSource);
                FileOutputStream fos = new FileOutputStream(fDeist);

                try {
                    // 缓冲区
                    byte[] buf = new byte[1024 * 1024 * 5];

                    // 拷贝数据
                    while (true) {
                        int res = 0;
                        // 获取源文件大小
                        int size = fis.available();

                        // 从源文件中读取数据，再写入至目标文件中
                        if (size < 1024 * 1024 * 5) {
                            fis.read(buf);
                            fos.write(buf, 0, size);
                            System.out.println("Buffered output!");
                        } else {
                            while ((res = fis.read()) != -1) {
                                fos.write(res);
                            }
                        }
                        break;
                    }
                    // 关闭流
                    fis.close();
                    fos.close();
                    System.out.println("Copy finish");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Please input the aim file!");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Please input the source file!");
            System.exit(0);
        }
    }

}

