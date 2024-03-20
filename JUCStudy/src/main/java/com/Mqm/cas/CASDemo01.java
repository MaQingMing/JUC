package com.Mqm.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/20 19:54
 */
public class CASDemo01 {
    //AtomicStampedReference 注意：如果泛型是一个包装类型，注意对象的引用问题
    static AtomicStampedReference<Integer> atomicStampedReference =new AtomicStampedReference<>(1,1);

    //CAS compareAndSet 比较并交换
    public static void main(String[] args) {

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();       //获取版本号
            System.out.println("a1=> " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1,2,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("atomicStampedReference = " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2,1,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());

        },"a");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();       //获取版本号
            System.out.println("b1=> " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(2,1,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());

        },"b");

    }
}
