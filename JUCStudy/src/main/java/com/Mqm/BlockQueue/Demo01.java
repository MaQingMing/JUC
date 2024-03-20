package com.Mqm.BlockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/18 16:24
 */

/**
 * 阻塞队列
 * 队列 的添加和移除
 * 4组API  熟练掌握
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }

    /**
     * 抛出异常
     */
    public static void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        blockingQueue.add("d");
        System.out.println("+++++++++++++++++++++++++++++++++");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }


    /**
     * 阻塞等待 (程序不结束，一直在等待)
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");        //队列满了，一直等待
        System.out.println("=============================");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();            //没有这个元素，一直等待
    }

    /**
     *  不抛出异常，有返回值
     */
    public static void test3(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));      //false
        System.out.println("=====================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());   //null  不抛出异常
    }

    /**
     * 校测出队首元素
     * element  peek
     */
    public static void test4(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
//        blockingQueue.add("a");
//        blockingQueue.add("b");
//        blockingQueue.add("c");
//        blockingQueue.element();
        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        blockingQueue.peek();
    }

    /**
     * 超时等待
     * @throws InterruptedException
     */
    public static void test5() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        blockingQueue.offer("C",2, TimeUnit.SECONDS);          //2秒后添加
        blockingQueue.poll(2,TimeUnit.SECONDS);               //2秒后取出元素
    }
}
