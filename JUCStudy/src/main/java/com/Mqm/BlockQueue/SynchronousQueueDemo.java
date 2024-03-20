package com.Mqm.BlockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/18 16:50
 */

/**
 * 同步队列
 * 和 BlockingQueue 不一样 , SynchronousQueue 不存储元素
 * put 了一个元素 , 必须先从里面take 出来,否则不能 put 进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Object> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"b");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"c");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"====>"+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"====>"+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"====>"+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
