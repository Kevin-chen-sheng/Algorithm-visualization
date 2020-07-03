package com.itheima.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Jishu03 {
    static int count=0;
    public static void main(String[] args) {
        //ArrayList res=new ArrayList();
        int count=0;
        Scanner sc =new Scanner(System.in);
        int r=sc.nextInt();
        int g=sc.nextInt();
        int res= dp(r,g);
        System.out.println(res);




    }
    public static int dp(int r, int g){
        if(r==0&&g==0){
            return 0;
        }
        if(r==1||g==1||g==0||r==0){
            return 1;
        }
        count++;
        return Integer.max(count,dp(r-1,g)+dp(r,g-1));
    }

}
