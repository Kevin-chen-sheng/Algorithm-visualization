package com.itheima.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Jishu02 {
    public static void main(String[] args) {
        //int[] arr={1,5,2,6};

        Scanner sc =new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr=new int[num];
        int lun = sc.nextInt();
        for (int i = 0; i < num; i++) {
            arr[i]=sc.nextInt();
        }
        for (int i = 0; i <lun ; i++) {
            Arrays.sort(arr);
            if(arr[num-1]-arr[0]>1){
                arr[num-1]=arr[num-1]-1;
                arr[0]=arr[0]+1;
            }
            Arrays.sort(arr);
        }
        System.out.println(arr[num-1]-arr[0]);
        //res.stream().forEach(System.out::println);

    }

}
