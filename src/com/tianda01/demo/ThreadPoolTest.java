package com.itheima.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        //创建等待队列
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
        //
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, queue);
        Runnable t1 = new MyThread();
        Runnable t2 = new MyThread();
        Runnable t3 = new MyThread();
        Runnable t4 = new MyThread();
        Runnable t5 = new MyThread();
        Runnable t6 = new MyThread();
        poolExecutor.execute(t1);
        poolExecutor.execute(t2);
        poolExecutor.execute(t3);
        poolExecutor.execute(t4);
        poolExecutor.execute(t5);
        poolExecutor.execute(t6);
        //关闭线程池
        poolExecutor.shutdown();
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"zhengzai");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
