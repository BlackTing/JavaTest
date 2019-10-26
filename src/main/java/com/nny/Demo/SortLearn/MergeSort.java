package com.nny.Demo.SortLearn;

/**
 * date 2019.5.22
 * writer liting
 * content 通过阅读博客，实现合并排序方法
 */
public class MergeSort {
    public static void main(String[] args){
        int[] a = {62,9,10,21,56,31,29,98,50,10};
        mergeSort(a);
        print(a);
    }
    public static void mergeSort(int[] a){

    }
    private static void print(int[] a) {
        for(int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
