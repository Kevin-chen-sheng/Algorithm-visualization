package com.itheima.demo;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
//        System.out.println("hello");
        Scanner sc = new Scanner(System.in);
        int parseInt = Integer.parseInt(sc.nextLine());
        String[] my = new String[parseInt];
        int index=0;
        while(index<parseInt) {
//            my[index++] = sc.nextLine();
        }
        for (String stringme : my) {
            System.out.println("输入的字符串是:"+stringme);
        }
    }
}
