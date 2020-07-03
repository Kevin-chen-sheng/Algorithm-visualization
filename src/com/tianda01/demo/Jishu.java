package com.itheima.demo;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Jishu {
    public static void main(String[] args) {
//        Reader
        ArrayList res=new ArrayList();
        Scanner sc =new Scanner(System.in);
        int line = sc.nextInt();
        for (int i = 0; i <line ; i++) {
            int n=sc.nextInt();
            int k=sc.nextInt();

            int count=0;
            for (int j = 1; j < Integer.MAX_VALUE; j++) {
                if(j%n==0){
                    continue;
                }
                count++;
                if(count==k){
                    res.add(j);
                    break;
                }
            }
        }
        res.stream().forEach(System.out::println);

    }

}
