package com.itheima.demo;

import java.util.*;

public class LambdaDemo {
//    List arr = new ArrayList<String>();
    public static void main(String[] args) {
        List<String> names=Arrays.asList("abc","gsh","bsd");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        Collections.sort(names,(String a,String b)->{return a.compareTo(b);});
        names.stream().forEach(System.out::println);
    }

}
