package org.yee.learning.springboot.thread.threadstart;

/**
 * @Package: org.yee.learning.springboot.thread
 * @Class: ThreadA
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 24/02/2018 15:32 32
 * @Version 1.0.0
 * @CopyRight: CopyRight (c)2018
 **/
public class ThreadA extends Thread {
    @Override
    public void run() {
        super.run();
        try {
//            while (true) {
                Thread.sleep(5000L);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是线程 A");
    }

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        // start() 方法的作用是启动一个新线程，新线程会执行相应的run()方法，start()不能被重复调用。
        threadA.start();
        // run()方法则只是普通的方法调用，在调用线程中顺序运行而已
        // threadA.run();
    }
}
