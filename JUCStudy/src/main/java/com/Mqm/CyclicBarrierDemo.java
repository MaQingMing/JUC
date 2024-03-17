package com.Mqm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/17 22:17
 */

/**
 * 加法计数器
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐7个龙珠");
        });

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println("Thread.currentThread().getName()+ finalI = " + Thread.currentThread().getName()+ finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
