package com.Mqm;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/17 17:14
 */
public class ProviderAndCustomer {

    public static void main(String[] args) {
        factory factory = new factory();
        new Thread(()->{
            try {
                factory.Provider();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                factory.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class factory{
    private static int num = 1;
    public synchronized void Provider() throws InterruptedException {
        while (num==1){
            this.wait();
        }
        num+=1;
        System.out.println("num = " + num+Thread.currentThread().getName());
        this.notifyAll();
    }

    public synchronized void customer() throws InterruptedException {
        while (num==0){
            this.wait();
        }
        num-=1;
        System.out.println("num = " + num+Thread.currentThread().getName());
        this.notifyAll();
    }

}
