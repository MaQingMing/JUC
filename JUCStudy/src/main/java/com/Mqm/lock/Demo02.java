package com.Mqm.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/20 20:52
 */
public class Demo02 {
    public static void main(String[] args) {
        TestSpinLock testSpinLock = new TestSpinLock();


        new Thread(()->{
            testSpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                testSpinLock.myUnLock();
            }
        },"T1").start();

        new Thread(()->{
            testSpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                testSpinLock.myUnLock();
            }
        },"T2").start();

        testSpinLock.myLock();
        testSpinLock.myUnLock();
    }

}
