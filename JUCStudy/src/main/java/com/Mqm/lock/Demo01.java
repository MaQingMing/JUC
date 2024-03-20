package com.Mqm.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/20 20:09
 */
public class Demo01 {
    public static void main(String[] args) {
        //true 是公平锁  、false  是非公平锁  、默认是非公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);
//           public ReentrantLock(boolean fair) {
//            sync = fair ? new ReentrantLock.FairSync() : new ReentrantLock.NonfairSync();
//        }
        //  测试可重入锁
        phone phone = new phone();
        new Thread(()->{
            phone.call();
        },"A").start();

        new Thread(()->{
            phone.call();
        },"B").start();


        telephone telephone = new telephone();
        new Thread(()->{
            telephone.call();
        },"C").start();

        new Thread(()->{
            telephone.call();
        },"D").start();
    }
    }
class phone{

    /**
     * 两个方法是同一把锁，进入了一个到第二个才会释放锁
     */
    public synchronized   void call(){
        System.out.println(" call"+Thread.currentThread().getName() );
        send();
    }
    public synchronized    void send(){
        System.out.println(" send"+Thread.currentThread().getName() );

    }
}

class telephone{

    /**
     * lock 加一把锁必须解一把锁，否则会出现死锁
     */
    public  void call(){
        Lock lock =new ReentrantLock();
        lock.lock();
        try {
            System.out.println(" call"+Thread.currentThread().getName() );
            send();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public synchronized    void send(){
        Lock lock =new ReentrantLock();
        lock.lock();
        try {
            System.out.println(" send"+Thread.currentThread().getName() );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}


