package org.yee.learning.springboot.thread;

import java.util.Currency;

/**
 * @Package: org.yee.learning.springboot.thread
 * @Class: ThreadProperty
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 24/02/2018 17:26 26
 * @Version 1.0.0
 * @CopyRight: CopyRight (c)2018
 **/
public class ThreadProperty implements Runnable {
    public void run() {
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread curThread = Thread.currentThread();
        String curThreadName = curThread.getName();
        System.out.println("当前线程的名称是：" + curThreadName);
        System.out.println("返回当前线程" + curThreadName + "的线程组中活动线程的数目：" + Thread.activeCount());
        System.out.println("返回该线程" + curThreadName + "的的标识符：" + curThread.getId());
        System.out.println("返回该线程" + curThreadName + "的的优先级：" + curThread.getPriority());
        System.out.println("返回该线程" + curThreadName + "的的状态：" + curThread.getState());
        System.out.println("返回该线程" + curThreadName + "所属的线程组" + curThread.getThreadGroup());
        System.out.println("测试线程" + curThreadName + "是否处于活动状态：" + curThread.isAlive());
        System.out.println("测试线程" + curThreadName + "是否是守护线程" + curThread.isDaemon());
    }

    public static void main(String[] args) {
        ThreadProperty threadProperty = new ThreadProperty();

        for (int i = 0; i < 5; i++) {
            new Thread(threadProperty, "线程名称：（" + i + "）").start();
        }
    }
}
