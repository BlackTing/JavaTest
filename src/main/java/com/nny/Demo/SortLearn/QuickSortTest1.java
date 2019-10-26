package com.nny.Demo.SortLearn;

/**
 * date 2019.3.18
 * writer 李婷
 * content 快速排序练习,第二版
 */
public class QuickSortTest1 {
    public static void main(String[] args) {
        int[] a = {10,6,2,3,15,10,21,1,11,12};
        quickSort(a,0,a.length-1);
        print(a);
    }

    private static void quickSort(int[] a, int left, int right) {
        if(a != null && a.length > 1 && left < right){ //条件判断
            int flag = partition(a,left,right); //进行第一次分解
            quickSort(a,left,flag-1); //递归调用，操作左边序列
            quickSort(a,flag+1,right); //递归调用，操作右边序列
        }
    }

    private static int partition(int[] a, int left, int right) {
        int i = left; //定义局部变量
        int j = right+1;
        int temp = a[left]; //基准数
        //挖坑填数
        while(i<j){
            for(j=j-1;j>i;j--){ //关注for循环中的初值和末值 从右向左
                if(a[j]<temp){
                    a[i] = a[j];//填坑i，也是挖坑j
                    break;
                }
            }
            if(i<j) { //双重检验，不需要再执行for循环中i的初始化
                for (i=i+1;i<j;i++) {
                    if (a[i] > temp) {
                        a[j] = a[i]; //填坑j,也是挖坑i
                        break;
                    }
                }
            }
        } //跳出循环，分解结束，基准数的位置找到，即j
        a[j] = temp;
        return j;
    }

    private static void print(int[] a) {
       for(int i : a){
           System.out.print(i+" ");
       }
       System.out.println();
    }
}
