package com.itheima.demo.suanfa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MyDoublePointExercise {
    public static void main(String[] args) {
//        List<Integer> integers = Arrays.asList(2, 4, 5, 8, 88, 99);
//        Integer[] arrInt= new Integer[integers.size()];
//        Integer[] array = integers.toArray(arrInt);
        int[] array= {2, 4, 5, 8, 88, 99};
        int[] ints = twoSum(array, 13);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

    /**
     * 左右指针移动
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers,int target){
        if(numbers==null) return null;
        int i=0,j=numbers.length-1;
        while (i<j){
            int sum=numbers[i]+numbers[j];
            if(sum==target){
                return new int[]{numbers[i],numbers[j]};
            }else if(sum<target){
                i++;
            }else {
                j--;
            }
        }
        return null;
    }

    /**
     * 判断一个非负整数是否为两个整数的平方
     * @param target
     * @return
     */
    public static boolean judgeSquareSum(int target){
        if(target<0) return false;
        int i=0,j= (int) Math.sqrt(target);
        while (i<=j){
            int powSum=i*i+j*j;
            if(powSum==target){
                return true;
            }else if(powSum>target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    /**
     * 前后两个指针,交换元素,放到一个新的数组
     */
    private final static HashSet<Character> vowels=new HashSet<>(Arrays.asList('a','e','i','o','u'));
    public static String reverseVowels(String s){
        if(s==null) return null;
        int i=0,j=s.length()-1;
        char[] result = new char[s.length()];
        while (i<=j){
            char ci=s.charAt(i);
            char cj=s.charAt(j);
            if(!vowels.contains(ci)){
                result[i++]=ci;
            }else if(!vowels.contains(cj)){
                result[j--]=cj;
            }else {
                //两个都是元音字符,才会交换
                result[i++]=cj;
                result[j--]=ci;
            }
        }
        return new String(result);
    }

    /**
     * 可以删除一个字符,判断是否能构成回文字符串
     * @param s
     * @return
     */
    public static boolean validPalindrome(String s){
        for (int i = 0,j=s.length()-1; i < j; i++,j--) {
            if(s.charAt(i)!=s.charAt(j)){
                return isPalindrome(s,i,j-1) ||isPalindrome(s,i+1,j);
            }
        }
        return true;
    }
    private static boolean isPalindrome(String s,int i,int j){
        while (i<j){
            if(s.charAt(i++)!=s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    /**
     * nums1=[1,2,3,0,0,0],m=3
     * nums2=[2,4,6],n=3
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1,int m,int[] nums2,int n){
        //要归并到nums1,从尾部开始遍历
        int index1=m-1,index2=n-1;
        int indexMerge=m+n-1;
        //只要有一个还大于大于等于0.就继续
        while (index1>=0||index2>=0){
            if(index1<0){
                nums1[indexMerge--]=nums2[index2--];
            }else if(index2<0){
                nums1[indexMerge--]=nums1[index1--];
            }else if(nums1[index1]>nums2[index2]){
                nums1[indexMerge--]=nums1[index1--];
            }else {
                nums1[indexMerge--]=nums2[index2--];
            }
        }
    }

    /**
     * 找最长子序列,
     * s="agshhj",d=["shhj","gh","ag"]
     * @param s
     * @param d
     * @return
     */
    public static String findLongestWord(String s,List<String> d){
        String longestWord="";
        for (String target : d) {
            int l1=longestWord.length(),l2=target.length();
            if(l1>l2||(l1==l2 && longestWord.compareTo(target)<0)){
                continue;
            }
            if (isSubstr(s, target)) {
                longestWord=target;
            }
        }
        return longestWord;
    }
    private static boolean isSubstr(String s,String target){
        int i=0,j=0;
        while (i<s.length()&&j<target.length()){
            if(s.charAt(i)==target.charAt(j)){
                j++;
            }
            i++;
        }
        //如果能遍历完target证明,是子字符串
        return j==target.length();
    }

}
