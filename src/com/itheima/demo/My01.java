package com.itheima.demo;

import java.util.Scanner;

public class My01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A= sc.nextInt();
        int B= sc.nextInt();
        int C= sc.nextInt();
        int D= sc.nextInt();
        int count=0;
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < B; b++) {
                for (int c = 0; c < C; c++) {
                    for (int d = 0; d < D; d++) {
                        if((a-b)%3==0||(b-a)%3==0){
                            if((b+c)%5==0){
                                if((a*c)%4==0){
                                    if(gongyue(a,d)==1){
                                        count++;
                                    }
                                }
                            }

                        }

                    }

                }

            }
        }
        System.out.println(count);
    }
    public static int gongyue(int a,int d){
        for (int i = Math.min(a,d); i >=1 ; i--) {
            if((a%i==0)&&(d%i==0)){
                return i;
            }
        }
        return 1;
    }
}
