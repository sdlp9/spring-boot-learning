package org.yee.learning.springboot.filehanding.filedemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @version 1.0.0
 * @package: org.yee.learing.springboot.filehanding
 * @class: RandomAccessFileDemo
 * @describe: springboot-learning
 * @author: brucewong
 * @time: 11/02/2018 14:27 27
 **/
public class RandomAccessFileDemo {
    /**
     * 学生类
     */
    static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            StringBuilder buf = new StringBuilder(name);
            // 把每个name设置为15个字符长度
            buf.setLength(15);

            this.name = buf.toString();
            this.score = score;
        }

        /**
         * 返回姓名
         *
         * @return
         */
        public String getName() {
            return name.toString();
        }

        /**
         * 返回分数
         *
         * @return
         */
        public int getScore() {
            return score;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        ResourceUtils.getFile("classpath:dataFile/ZH.txt");
        Student[] st = new Student[]{
                new Student("apple", 100),
                new Student("dog", 200),
                new Student("cat", 300)
        };
        doWrite("temp.dat", st);
        doRead("temp.dat");
    }


    /**
     * 从文件中读取数据
     *
     * @param arg
     */
    private static void doRead(String arg) {

        try {
            File file = new File(arg);
            // 以可读可写的方式打开流
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // 用户指定要读取第几个学生的信息
            int pos;
            Scanner sc = new Scanner(System.in);
            pos = sc.nextInt();

            // 读取第pos个学生的信息
            char[] buf = new char[15];
            // 设置读取位置，对于每个学生信息我们分别写入了34个字节
            raf.seek((pos - 1) * 34);

            // 读取数据
            for (int i = 0; i < buf.length; ++i) {
                // 一个字符一个字符地读取
                buf[i] = raf.readChar();
            }
            String str = new String(buf);

            // 显示数据
            // 把buf中的\0全部替换成 空格
            System.out.println(str.replace('\0', ' '));
            System.out.println(raf.readInt());

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写入数据
     *
     * @param arg
     * @param st
     */
    private static void doWrite(String arg, Student[] st) {

        try {
            // 从命令行参数中指定文件名
            File file = new File(arg);
            // 以可读可写的方式打开流
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            for (int i = 0; i < st.length; ++i) {
                // 从命令行参数中指定文件名
                raf.writeChars(st[i].getName());
                // 写入一个整形数据，即分数
                raf.writeInt(st[i].getScore());
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
