package org.yee.learning.springboot.thread.threadstart;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Package: org.yee.learning.springboot.thread
 * @Class: ThreadC
 * @Describe: springboot-learning
 * @Author: brucewong
 * @Time: 24/02/2018 16:50 50
 * @Version 1.0.0
 * @CopyRight: CopyRight (c)2018
 **/
public class ThreadC implements Callable<String> {
    public String call() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" 这里是线程 B");
        return " thread B";
    }

    public static void main(String[] args) {
        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<String>(threadC);
        new Thread(futureTask).start();
        System.out.println(" 这里是主线程：begin");
        try {
            System.out.println(" 得到的返回结果是：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(" 这里是主线程：end");
    }
}
