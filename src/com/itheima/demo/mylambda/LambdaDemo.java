package com.itheima.demo.mylambda;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {
    public static final List<Integer> list = Arrays.asList(1,2,86,34,65,69);

    public static void main(String[] args) {
        /*//jdk1.7
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println(list);*/

        //jdk1.8
        list.sort((o1,o2)->{return o1-o2;});
        System.out.println(list);

        //1.7遍历
        for (Integer integer : list) {
            System.out.println(integer);
        }
        //1.8遍历
        list.forEach(i -> System.out.println(i));

        /*// 定义一个局部变量
        int num = -1;
        //Lambda表达式的实质其实还是匿名内部类，所以我们其实可以把Lambda表达式赋值给某个变量
        Runnable r = () -> {
            // 在Lambda表达式中使用局部变量num，num会被隐式声明为final,比如num++,就是错的,不能修改
            System.out.println(num);
        };
        new Thread(r).start();// -1*/
    }
}
