package com.nny.Demo.SortLearn;

/**
 * data 2019.3.18
 * writer 李婷
 * content 快速排序,第一版
 */
public class QuickSortTest {
    static int ci = 0;
    public static void main(String[] args) {
        int[] a = {6,4,3,2,7,9,1,8,5,10};//10个内容
        quickSort(a,0,a.length-1);
    }

    private static void quickSort(int[] a, int left, int right) {
        if(a != null && a.length > 1 && left < right){ //不加left < right，抛出StackOverflowError异常
            ci++;
            int flag = partition(a,left,right);
            System.out.println("递归调用第"+ci+"次,flag="+flag);
            print(a);
            quickSort(a,left,flag-1);
            quickSort(a,flag+1,right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int i = left; //向右移动的指针
        int j = right; //向左移动的指针
        int temp = a[left]; //基准值
        while(i<j){
            //从右向左
            for(;j>i;j--){ //9 8 7 6 5 4 3 2 1
                if(a[j]<temp){
                    a[i] = a[j];
                    break; //跳出一层循环
                }
            }
            //从左向右
            for(i=i+1;i<j;i++){
                if(a[i]>temp){
                    a[j] = a[i];
                    break;
                }
            }
        }
        a[j] = temp; //跳出循环，意味着找到了位置，即j
        return j;
    }

    private static void print(int[] a) {
        System.out.println("递归调用第"+ci+"次");
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
