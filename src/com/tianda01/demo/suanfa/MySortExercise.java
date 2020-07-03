package com.itheima.demo.suanfa;

import java.util.*;

public class MySortExercise {
    public static void main(String[] args) {
        int[] arr = {5,2,7,9,1};
        int kthLargest01 = findKthLargest01(arr, 3);
        System.out.println(kthLargest01);
    }

    /**
     * 查找倒数第k个元素
     * @param nums
     * @param k
     * @return
     */
    //排序
    public static int findKthLargest(int[] nums,int k){
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    //堆
    public static int findKthLargest01(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();//小顶堆
        for (int val : nums) {
            queue.add(val);
            if(queue.size()>k){
                queue.poll();
            }
        }
        return queue.peek();
    }
    //快速选择.....待定

    /**
     * 找出现频率最高的k个元素
     * given[1,1,2,3,2,1] and k=2,return [1,2]
     * 桶(是一个集合数组),每个桶存储出现频率相同的数,桶的下标表示数出现的频率
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums,int k){
        HashMap<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num,frequencyForNum.getOrDefault(num,0)+1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        //这里的key是原数组的内容
        for (Integer key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if(buckets[frequency]==null){
                buckets[frequency]=new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length-1; i >=0&&topK.size()<k ; i--) {
            if(buckets[i]==null){
                continue;
            }
            if(buckets[i].size()<=(k-topK.size())){
                topK.addAll(buckets[i]);
            }else {
                topK.addAll(buckets[i].subList(0,k-topK.size()));
            }
        }
        return topK;
    }

    /**
     * 按照字符出现的次数堆字符串排序
     * 也是用桶
     * input:tree
     * output:eetr
     * @param s
     * @return
     */
    public static String frequencySort(String s){
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyForNum.put(c,frequencyForNum.getOrDefault(c,0)+1);
        }
        //桶,因为从1开始存,不从0,所以这里要加1
        List<Character>[] frequencyBucket = new ArrayList[s.length()+1];
        for (Character c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if(frequencyBucket[f]==null){
                frequencyBucket[f]=new ArrayList<>();
            }
            frequencyBucket[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        //i从大到小,因为i就是相应的频率或者个数
        for(int i=frequencyBucket.length-1;i>=0;i--){
            if(frequencyBucket[i]==null){
                continue;
            }
            for (Character c : frequencyBucket[i]) {
                //因为这个集合里面总共有i个
                for(int j=0;j<i;j++){
                    str.append(c);
                }
            }
        }
        return str.toString();
    }

    /**
     * 按颜色进行排序
     * input:[2,0,1,2]
     * output:[0,1,2,2]
     * @param nums
     */
    public static void sortColors(int[] nums){
        int zero=-1,one=0,two=nums.length;
        while (one<two){
            if(nums[one]==0){
                swap(nums,++zero,one++);
            }else if(nums[one]==2){
                swap(nums,--two,one);
            }else {
                ++one;
            }
        }
    }
    private static void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }
}
