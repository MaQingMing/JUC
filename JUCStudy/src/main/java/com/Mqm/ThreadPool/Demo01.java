package com.Mqm.ThreadPool;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/18 17:07
 */

import java.util.concurrent.*;

/**
 * 线程池方法
 * Executors  表示工具类
 */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();   //单个线程
//        ExecutorService executorService1 = Executors.newFixedThreadPool(5); //创建一个固定的线程池大小
//        ExecutorService executorService2 = Executors.newCachedThreadPool();    //创建一个缓存线程池，遇强则强，
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        //自定义线程池 工作ThreadPoolExecutor

        //最大线程到底该如何定义
        //1.CPU 密集型 几核，就是几，可以保持Cpu的效率最高
        //2.IO 密集型 > 判断程序中有几个十分耗Io的线程  io线程数+2
        // 程序 15个大型任务，io占用资源
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),      //获取最大CPU核数
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()       //拒绝策略 :   银行满了，还有人进来了，不处理这个了，抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()     // 哪来的去哪里
//                new ThreadPoolExecutor.DiscardPolicy()      //队列满了，丢点任务，不会抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy()    //队列满了，尝试去和最早的竞争，也不会抛出异常
        );
        //最大承载 :  Deque + max
        for (int i = 1; i <= 10; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            });
        }
        threadPoolExecutor.shutdown();  //关闭线程池，程序结束

//        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return Thread.currentThread().getName();
//            }
//        }, 5, TimeUnit.SECONDS);
//        System.out.println(schedule.get());
//        scheduledExecutorService.shutdown();
//    }

        //7大参数，线程池源码
//    public ThreadPoolExecutor(int corePoolSize,        核心线程池大小
//                              int maximumPoolSize,        最大核心线程池大小
//                              long keepAliveTime,         超时了没人调用就会释放
//                              TimeUnit unit,              超时单位
//                              BlockingQueue<Runnable> workQueue,   阻塞队列
//                              ThreadFactory threadFactory,         线程工厂，创建线程的，一般不用动
//                              RejectedExecutionHandler handler     拒绝策略
//                              ) {
//        if (corePoolSize < 0 ||
//                maximumPoolSize <= 0 ||
//                maximumPoolSize < corePoolSize ||
//                keepAliveTime < 0)
//            throw new IllegalArgumentException();
//        if (workQueue == null || threadFactory == null || handler == null)
//            throw new NullPointerException();
//        this.corePoolSize = corePoolSize;
//        this.maximumPoolSize = maximumPoolSize;
//        this.workQueue = workQueue;
//        this.keepAliveTime = unit.toNanos(keepAliveTime);
//        this.threadFactory = threadFactory;
//        this.handler = handler;
    }
}

