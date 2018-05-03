package org.yee.learning.springboot.thread.threadstart;

/**
 * @Package: org.yee.learning.springboot.thread
 * @Class: ThreadB
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 24/02/2018 16:46 46
 * @Version 1.0.0
 * @CopyRight: CopyRight (c)2018
 **/
public class ThreadB implements Runnable {
    public void run() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这里是线程 B");
    }

    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.printf(" 这里是主线程：");
    }
}
