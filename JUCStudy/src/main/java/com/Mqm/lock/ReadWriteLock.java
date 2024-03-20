package com.Mqm.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/18 13:13
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i <6 ; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(String.valueOf(finalI),1);
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <6 ; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.get(String.valueOf(finalI));
            },String.valueOf(i)).start();
        }

    }
}

class MyCacheLock{
    private volatile Map<String,Object> map =new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, int value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println("k写入" + key);
            map.put(key,value);
            System.out.println("写入成功" + key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println("读取" + key);
            Object o = map.get(key);
            System.out.println("读取成功" + o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }
}



class MyCache{
    private volatile Map<String,Object> map =new HashMap<>();

    public void put(String key, int value){
        System.out.println("k写入" + key);
        map.put(key,value);
        System.out.println("写入成功" + key);
    }

    public void get(String key){
        System.out.println("读取" + key);
        Object o = map.get(key);
        System.out.println("读取成功" + o);
    }
}
