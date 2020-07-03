package com.itheima.demo.productconsumer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        PublicQueue01 publicQueue = new PublicQueue01();
        ProducerThread producerThread = new ProducerThread(publicQueue);
        ConsumerThread consumerThread = new ConsumerThread(publicQueue);
        producerThread.start();
        consumerThread.start();
    }
}
//
/**
 * 公共缓存队列
 * 只做两件事：（1）生产；（2）消费
 */
class PublicQueue01<T> {

    private int putIndex = 0;//数据插入的角标
    private int maxCount = 50;//缓存区最大长度

    private LinkedHashMap<Integer, T> linkedHashMap = new LinkedHashMap<>();//缓冲区

    public synchronized void add(T msg){
        if(linkedHashMap.size() == maxCount){
            //如果缓存区达到最大数量，则阻塞生产者线程
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            notifyAll();//唤醒所有线程
        }

        linkedHashMap.put(putIndex, msg);
        System.out.println("生产一个产品，当前商品角标为："+putIndex+"===文本为："+msg+"===缓存长度为："+linkedHashMap.size());
        putIndex = (putIndex + 1 >= maxCount) ? (putIndex + 1) % maxCount : putIndex + 1;
    }

    public synchronized T remove(){

        if(linkedHashMap.size() == 0){
            //如果缓存区没有数据，则阻塞消费线程
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            notifyAll();//唤醒所有线程
        }

        Iterator it = linkedHashMap.entrySet().iterator();
        T t = null;
        if(it.hasNext()){
            Map.Entry<Integer, T> entry = (Map.Entry<Integer, T>) it.next();
            t = entry.getValue();
            int index = entry.getKey();
            linkedHashMap.remove(index);
            System.out.println("消费一个产品，当前商品角标为："+index+"===文本为："+ t +"===缓存长度为："+linkedHashMap.size());
        }
        return t;
    }
}

//使用阻塞队列
class PublicQueue<T>{
    private BlockingDeque<T> blockingDeque=new LinkedBlockingDeque<>(50);
    public void add(T msg){
        try {
            blockingDeque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产一个产品,文本为:"+msg);
    }

    public T remove(){
        T t = null;
        try {
            t = blockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费一个产品,文本为:"+t);
        return t;
    }
}
//生产者线程
class ProducerThread extends Thread{
    private PublicQueue01 publicQueue;

    public ProducerThread(PublicQueue01 publicQueue) {
        this.publicQueue = publicQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 60; i++) {
            publicQueue.add(String.valueOf(i));
        }
    }
}

//消费者线程
class ConsumerThread extends Thread{
    private PublicQueue01 publicQueue;

    public ConsumerThread(PublicQueue01 publicQueue) {
        this.publicQueue = publicQueue;
    }

    @Override
    public void run() {
        while (true){
            publicQueue.remove();
        }
    }
}