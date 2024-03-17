package com.Mqm.ThreadJUC;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/17 16:36
 */
public class demo01 {


    public static void main(String[] args) {
        new Thread(()-> { for (int i = 0; i < 40; i++) {supermarker.sale(); } },"A").start();
        new Thread(()-> { for (int i = 0; i < 40; i++) {supermarker.sale(); } },"B").start();
        new Thread(()-> { for (int i = 0; i < 40; i++) {supermarker.sale(); } },"C").start();
    }
}

class supermarker{
    private static int nums = 30;
    public static void  sale(){
        //两种方式控制并发
//        Lock lock =new ReentrantLock();
//        lock.lock();
        synchronized (supermarker.class){
            if (nums<0){
                System.out.println("超卖了");
            }else {
                System.out.println("卖出，剩余" + nums--);
            }
        }

    }
}
