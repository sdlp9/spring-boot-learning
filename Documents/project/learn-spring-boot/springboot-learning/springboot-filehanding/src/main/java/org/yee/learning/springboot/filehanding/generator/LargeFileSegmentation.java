package org.yee.learning.springboot.filehanding.generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @version 1.0.0
 * @package: org.yee.learning.springboot.thread.fileprocess
 * @class: LargeFileSegmentation
 * @describe: springboot-learning
 * @author: brucewong
 * @time: 11/02/2018 10:56 56
 **/
public class LargeFileSegmentation {
    public static void main(String[] args) {
        splitLargeFile(
                "/Users/brucewong/Documents/conf/data/cctv.txt",
                1024 * 1024,
                "/Users/brucewong/Documents/conf/data/ccav");
    }


    public static void splitLargeFile(String sourcePath, int capacity, String targetPath) {
        FileChannel fileChannel;
        FileInputStream fin = null;
        FileOutputStream fout;
        FileChannel fcout;
        try {
            fin = new FileInputStream(sourcePath);
            fileChannel = fin.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(capacity);
            int i = 0;
            while (true) {
                i += 1;
                buffer.clear();
                int flag = fileChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                buffer.flip();
                fout = new FileOutputStream(targetPath + i);
                fcout = fout.getChannel();
                fcout.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
