package org.yee.learning.springboot.filehanding.generator.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Package: org.yee.learning.springboot.filehanding.generator.util
 * @Class: FileUtil 文件读写处理
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 12/02/2018 11:29 29
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 **/

public class FileUtil {

    /**
     * 日志对象
     */
    private static final Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 读取大文件数据并写入到指定文件
     *
     * @param source
     * @param target
     * @throws Exception
     */
    public static void read(String source, String target) throws Exception {
        // 输入文件
        File fin = new File(source);
        if (!fin.exists()) {
            throw new Exception("读取的文件不存在");
        }
        Long startTime = System.currentTimeMillis();
        int bufSize = 100;
        FileChannel fileChannel = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        String enterStr = "\n";
        try {
            byte[] bs = new byte[bufSize];
            StringBuilder strBuf = new StringBuilder();
            while (fileChannel.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    String[] lineArray = line.split("	");
                    if (lineArray.length == 3) {
                        logger.info(lineArray[2]);
                        append2File(target, lineArray[2] + "\r\n");
                    }
                    System.out.println();
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
            Long endTime = System.currentTimeMillis();
            logger.info("读取总耗时：" + ((endTime - startTime) / 1000));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileChannel) {
                fileChannel.close();
            }
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static synchronized void append2File(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NIO方式写入文件
     *
     * @param content
     * @param fileName
     * @throws IOException
     * @remark 注意FileChannel.write()是在while循环中调用的。
     * 因为无法保证write()方法一次能向FileChannel写入多少字节，
     * 因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节。
     */
    public static void writeByNio(String content, String fileName) throws IOException {
        FileOutputStream fos = null;
        FileChannel fileChannel = null;
        try {
            fos = new FileOutputStream(fileName, true);
            fileChannel = fos.getChannel();
            ByteBuffer buf = ByteBuffer.wrap(content.getBytes());
            buf.put(content.getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                fileChannel.write(buf);
            }
        } finally {
            if (null != fileChannel) {
                // 关闭通道
                fileChannel.close();
            }
            if (null != fos) {
                fos.close();
            }
        }
    }


    /**
     * NIO的方式读取文件
     *
     * @param inputFile
     */
    public static void readByNio(String inputFile) throws IOException {
        // 第一步 获取通道
        FileInputStream fis = null;
        FileChannel channel = null;
        try {
            fis = new FileInputStream(inputFile);
            channel = fis.getChannel();
            // 文件内容的大小
            int size = (int) channel.size();
            // 第二步 指定缓冲区 1M [1024 byte]
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            System.out.println(
                    "限制是：" + buffer.limit() +
                            "容量是：" + buffer.capacity() +
                            "位置是：" + buffer.position());

            // 第三步 将通道中的数据读取到缓冲区中
            channel.read(buffer);
            byte[] bt = buffer.array();
            System.out.println(new String(bt, 0, size));
            buffer.clear();
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                assert channel != null;
                channel.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param filePath
     */
    public static void readFile(String filePath) {
        byte[] bytes = new byte[1024];
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        FileChannel channel = null;
        try {
            // 得到一个通道
            channel = new RandomAccessFile(filePath, "r").getChannel();
            while (channel.read(byteBuffer) != -1) {
                int size = byteBuffer.position();
                byteBuffer.rewind();
                byteBuffer.get(bytes);
                String tmp = new String(bytes, 0, size);
                byteBuffer.clear();
                logger.info(tmp);
            }
            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert channel != null;
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取大文件并输出到指定文件
     *
     * @param inputFile
     * @param outputFile
     */
    public static void largeFileIO(String inputFile, String outputFile) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
            // 10M缓存
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);
            FileWriter fw = new FileWriter(outputFile);
            while (in.ready()) {
                String line = in.readLine();
                fw.append(line).append(" ");
            }
            in.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 统计文件行数
     *
     * @param inputFile
     * @return
     */
    public static int getFileLines(String inputFile) {
        File test = new File(inputFile);
        long fileLength = test.length();
        LineNumberReader rf = null;
        int lines = 0;
        try {
            rf = new LineNumberReader(new FileReader(test));
            rf.skip(fileLength);
            lines = rf.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rf != null) {
                try {
                    rf.close();
                } catch (IOException ignored) {
                }
            }
        }
        return lines;
    }

    public static void main(String[] args) {
        try {
//			read("D://grab/file/url_lineout.txt", "D://grab/file/grab_urls.txt");
//			read("G://url_lineout.txt/url_lineout.txt", "F://url_lineout.txt/url_lineout_clean.txt");
            System.out.println(getFileLines("G://url_lineout.txt/url_lineout.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
