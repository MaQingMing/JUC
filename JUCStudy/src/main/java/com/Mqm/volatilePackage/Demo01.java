package com.Mqm.volatilePackage;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/19 23:00
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 */
public class Demo01 {

//    private volatile  static int num =0;\
    //原子类
    private volatile static AtomicInteger num = new AtomicInteger();

    public  static void add(){
        num.getAndIncrement();
    }
    public static void main(String[] args) {
        for (int i = 1; i <=20; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(num);
    }
}
