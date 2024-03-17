package com.Mqm;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/17 22:09
 */

/**
 * 减法计数器
 */
public class CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须在执行任务的时候，再使用
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go out");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();

        }
        countDownLatch.await();         //等待计数器归零，然后再向下执行
        System.out.println("close door");
    }

}
