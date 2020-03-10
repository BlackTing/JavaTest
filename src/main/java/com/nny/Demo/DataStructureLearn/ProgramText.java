package com.nny.Demo.DataStructureLearn;

import java.util.Scanner;

/**
 * 在线编程练习
 */
public class ProgramText {


    /**
     * 输入小岛的行数、列数、小岛的01组成，0是海，1是岛，求岛屿个数
     * @param args
     */
    public static void main0(String[] args) {

        int m;//行数

        int n;//列数

        int[][] a;//

        int[] b;//

        int c = 2;//

        int d = 0;

        Scanner in = new Scanner(System.in);

        m = in.nextInt();
        System.out.println(m);
        n = in.nextInt();

        a = new int[m][n];

        b = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        //第一行
        for(int j=0; j<n; j++){
            if(a[0][j] == 1){

                a[0][j] = c;
                b[c++] = 1;

                if(j < n-1) {

                    while (a[0][++j] != 0) {
                        a[0][j] = a[0][j-1];
                        if (j == n - 1) {
                            break;
                        }
                    }
                }
            }

        }

        for(int i=1; i<m; i++){
            for(int j=0; j<n; j++){
                if(a[i][j] == 1){
                    if(a[i-1][j] == 0){
                        a[i][j] = c;
                        b[c++] = 1;
                    }else{
                        a[i][j] = a[i-1][j];
                    }

                    if(j<n-1) {

                        while (a[i][++j] == 1) {

                            if (a[i - 1][j] == 0) {
                                a[i][j] = a[i][j-1];
                            } else {
                                int e = a[i - 1][j];

                                a[i][j] = e;

                                b[a[i][j - 1]] = 0;

                                int f = j;

                                while (f > 0) {
                                    if (a[i][--f] != 0) {
                                        a[i][f] = e;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            if (j == n - 1) {
                                break;
                            }
                        }
                    }
                }
            }

        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(a[i][j]+ " ");
            }
            System.out.println();
        }

        for(int i=0; i<c; i++){
            System.out.print(b[i]+" ");
            if(b[i] != 0){
                d++;
            }
        }

        System.out.println("\n"+d);


    }
}
