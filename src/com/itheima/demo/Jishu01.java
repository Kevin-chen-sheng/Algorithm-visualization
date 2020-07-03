package com.itheima.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Jishu01 {
    public static void main(String[] args) {
        int sum=0;
        Scanner sc =new Scanner(System.in);
        int line = sc.nextInt();
        for (int i = 0; i <line ; i++) {

            int n=sc.nextInt();
            int k=sc.nextInt();
            if(i==line-1){
                System.out.println(Integer.max(sum,k+sum));
                return;
            }
            sum+=k;

        }
//        res.stream().forEach(System.out::println);

    }

}
