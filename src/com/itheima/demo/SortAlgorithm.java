package com.itheima.demo;

import java.util.Arrays;

public class SortAlgorithm {
    public static void main(String[] args) {
        int[] array={1,4,3,9,23,16};
//        bubbleSort(array);
        selectionSort(array);
        int binarySearch = binarySearch(array, 23);
        System.out.println(Arrays.toString(array));
        System.out.println(binarySearch);
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] array){
        if(array==null||array.length<=1){
            return;
        }
        int length = array.length;
        for (int i = 0; i < array.length; i++) {
            for(int j=0;j<length-i-1;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
    }
    /**
     * 选择排序
     */
    public static void selectionSort(int[] array){
        if(array==null||array.length<=1){
            return;
        }
        int length = array.length;
        for(int i=0;i<length-1;i++){
            //最小数的索引
            int minIndex=i;
            for(int j=i+1;j<length;j++){
                //找到最小的数
                if(array[minIndex]>array[j]){
                    minIndex=j;
                }
            }

            if(i!=minIndex){
                swap(array,minIndex,i);
            }
        }
    }

    /**
     * 根据索引交换位置
     * @param array
     * @param aIndex
     * @param bIndex
     */
    private static void swap(int[] array,int aIndex ,int bIndex){
        int temp=array[aIndex];
        array[aIndex]=array[bIndex];
        array[bIndex]=temp;
    }

    /**
     * 二分查找key值,但array要有序
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array,int key){
        int low=0;
        int high=array.length-1;
        int mid=0;
        if(key<array[low]||key>array[high]||low>high){
            return -1;
        }
        while (low<=high){
            mid=(low+high)>>1;
            if(key<array[mid]){
                high=mid-1;
            }else if (key>array[mid]){
                low=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
