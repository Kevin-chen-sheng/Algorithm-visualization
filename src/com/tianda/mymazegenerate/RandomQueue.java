package com.tianda.mymazegenerate;

import java.util.LinkedList;

//自定义数据结构,达到随机存取的目的
public class RandomQueue<E>{

    private LinkedList<E> queue;

    public RandomQueue(){
        queue = new LinkedList<E>();
    }

    public void add(E e){
        if(Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");


        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size(){
        return queue.size();
    }

    public boolean empty(){
        return size() == 0;
    }
}