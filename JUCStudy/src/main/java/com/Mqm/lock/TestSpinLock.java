package com.Mqm.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/20 20:47
 */
public class TestSpinLock {

    AtomicReference<Thread> atomicReference =new AtomicReference<>();

    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>mylock");
        //自旋锁
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    //解锁
    //加锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===>myUnlock");
        atomicReference.compareAndSet(thread,null);
    }
}
