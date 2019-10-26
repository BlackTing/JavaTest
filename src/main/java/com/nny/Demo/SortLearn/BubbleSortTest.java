package com.nny.Demo.SortLearn;

/**
 * date 2019.3.19
 * writer liting
 * content 冒泡排序
 */
public class BubbleSortTest {
    public static void main(String[] args){
        int[] a = {62,9,10,21,56,31,29,98,50,10};
        bubbleSort(a);
        print(a);
    }
    public static void bubbleSort(int[] a){
        boolean flag;
        for(int i=1;i<a.length;i++){ //第i趟排序
            flag = false;
            for(int j=0;j<a.length-i;j++){ //一趟排序增加一个有序数
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){ //一趟排序中没有交换位置，证明有序了，跳出循环
                break;
            }
        }
    }
    private static void print(int[] a) {
        for(int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
