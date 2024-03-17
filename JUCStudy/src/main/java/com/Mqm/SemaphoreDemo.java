package com.Mqm;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/17 22:23
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量，停车位，限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();    //得到
                    TimeUnit.SECONDS.sleep(2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();      //释放
                }
            },String.valueOf(i)).start();
        }
    }
}
