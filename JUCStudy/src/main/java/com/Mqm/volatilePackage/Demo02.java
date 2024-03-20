package com.Mqm.volatilePackage;

import java.util.concurrent.TimeUnit;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/19 23:11
 */
public class Demo02 {
    //保证可见性
    private volatile  static int num =0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num==0){

            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        num+=1;
        System.out.println("num = " + num);
    }
}
