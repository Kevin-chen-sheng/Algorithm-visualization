package com.itheima.demo.leetcodedemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyExam {
    public static void main(String[] args) {
//        int[] arr={2,4,76,19,57,4,79,45};
//        int i = rob2(arr);
//        int i = climbStairs(4);
        int i = zuHeUniquePaths1(3, 1);
        System.out.println(i);
    }

    /**
     * 跳台阶
     * @param n
     * @return
     */
    public static int climbStairs(int n){
        if(n<=2){
            return n;
        }
        int pre1=1,pre2=2;
        for (int i = 2; i < n; i++) {
            int cur=pre1+pre2;
            pre1=pre2;
            pre2=cur;
        }
        return pre2;
    }

    /**
     * 强盗问题,不能取邻近的,但最终要取到最终最大值
     * dp[i]=max(dp[i-2]+nums[i],dp[i-1])
     * @param nums
     * @return
     */
    public static int rob(int[] nums){
        int pre2=0,pre1=0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2=pre1;
            pre1=cur;
        }
        return pre1;
    }

    /**
     * 在环形数组抢劫[1,2,4,1],首尾相接,邻近也不能拿
     * 拆成两个数组
     * @param nums
     * @return
     */
    public static int rob2(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }
        int n=nums.length;
        if(n==1){
            return nums[0];
        }
        return Math.max(rob2(nums,0,n-2),rob2(nums,1,n-1));
    }
    private static int rob2(int[] nums,int first,int last){
        int pre2=0,pre1=0;
        for (int i = first; i <= last; i++) {
            int cur = Math.max(nums[i] + pre2, pre1);
            pre2=pre1;
            pre1=cur;
        }
        return pre1;
    }

    /**
     * 从矩阵的左上角到右下角,求最小路径和,1+2+2+1
     * 每次只能向下和向右
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid){
        if(grid.length==0||grid[0].length==0){
            return 0;
        }
        int row=grid.length;
        int col=grid[0].length;
        int[] dp=new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(j==0){
                    //处在第一列,只能向下
                    dp[j]=dp[j];
                }else if(i==0){
                    //处在第一行,只能向右
                    dp[j]=dp[j-1];
                }else {
                    dp[j]=Math.min(dp[j-1],dp[j]);
                }
                dp[j]+=grid[i][j];
            }

        }
        return dp[col-1];
    }

    /**
     *从矩阵左上角到右下角的路径总数,只能向右或向下
     * @param row
     * @param col
     * @return
     */
    public static int uniquePaths(int row,int col){
        //还是用列做个数组
        int[] dp=new int[col];
        Arrays.fill(dp,1);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[j]=dp[j]+dp[j-1];
            }
        }
        return dp[col-1];
    }

    /**
     * 组合问题求从s中取d个的情况
     * @param row
     * @param col
     * @return
     */
    public static int zuHeUniquePaths(int row,int col){
        int s=row+col-2;
        int d=row-1;
        long res=1;
        for (int i = 1; i <= d; i++) {
            res=res*(s-d+i)/i;
        }
        return (int) res;
    }
    public static int zuHeUniquePaths1(int s,int d){
        long res=1;
        for (int i = 1; i <= d; i++) {
            res=res*(s-d+i)/i;
        }
        return (int) res;
    }

    /**
     * 求nums数组(i,j)范围的和
     * @param nums
     * @param i
     * @param j
     * @return
     */
    public static int sumRange(int[] nums,int i,int j){
        //错位一个
        int[] sums=new int[nums.length+1];
        for (int k = 1; k <= nums.length; k++) {
            sums[k]=sums[k-1]+nums[k-1];
        }
        return sums[j+1]-sums[i];
    }

    /**
     * dp[i]是以arr[i]结尾的等差递增子区间的个数
     * 注意:等差肯定至少三个
     * @param arr
     * @return
     */
    public static int numberOfArithmeticsSlices(int[] arr){
        if(arr==null||arr.length<3){
            return 0;
        }
        int n=arr.length;
        int[] dp=new int[n];
        for (int i = 2; i < n; i++) {
            if(arr[i]-arr[i-1]==arr[i-1]-arr[i-2]){
                dp[i]=dp[i-1]+1;
            }
        }
        int sum=0;
        for (int i : dp) {
            sum+=i;
        }
        return sum;
    }

    /**
     * 求分割整数的最大乘积,n=10=(3+3+4),return,36
     * @param n
     * @return
     */
    public static int integerBreak(int n){
        int[] dp=new int[n+1];
        dp[1]=1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i-1; j++) {
                dp[i]=Math.max(dp[i],Math.max(j*dp[i-j],j*(i-j)));
            }
        }
        //最后一个
        return dp[n];
    }

    /**
     * 制造平方数
     * @param n
     * @return
     */
    private List<Integer> generateSquareList(int n){
        List<Integer> squareList = new ArrayList<>();
        int diff=3;
        int square=1;
        while (square<=n){
            squareList.add(square);
            square+=diff;
            diff+=2;
        }
        return squareList;
    }

    /**
     * 分割整数构成字母字符串,12就是AB,或者L
     * 2中情况
     * @param s
     * @return
     */
    public static int numDecodings(String s){
        if(s==null||s.length()==0){
            return 0;
        }
        int n=s.length();
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=(s.charAt(0)=='0')?0:1;
        for (int i = 2; i <= n; i++) {
            //一个字符
            int one=Integer.valueOf(s.substring(i-1,i));
            if(one!=0){
                dp[i]+=dp[i-1];
            }
            if(s.charAt(i-2)=='0'){
                continue;
            }
            //两个字符
            int two=Integer.valueOf(s.substring(i-2,i));
            if(two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[n];
    }

    /**
     * 求最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums){
        int n=nums.length;
        int[] dp=new int[n];
        for (int i = 0; i < n; i++) {
            int max=1;
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    max=Math.max(max,dp[j]+1);
                }
            }
            dp[i]=max;
        }
        //找出最大的值
        int ret=0;
        for (int i : dp) {
            ret=Math.max(ret,i);
        }
        return ret;
    }


}
