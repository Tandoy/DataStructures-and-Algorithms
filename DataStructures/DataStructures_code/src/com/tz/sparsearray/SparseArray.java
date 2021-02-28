package com.tz.sparsearray;

import java.util.Arrays;

/**
 * 稀疏数组：节省存储空间，节约资源
 * 稀疏数组与二维数组的相互转换
 */
public class SparseArray {
    public static void main(String[] args) {
        // 1.创建二维数组
        // 棋盘初始大小为11*11 0：代表没有棋子 1：表示 黑子 2：表示蓝子
        int[][] chrees2 = new int[11][11];
        chrees2[1][2] = 1;
        chrees2[2][3] = 2;
        chrees2[4][5] = 9;
        // 输出原始二维数组
        for (int[] row : chrees2){
            for (int data : row){
                System.out.printf("\t" + data);
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
        /**
         * 二维数组转稀疏数组
         * 1.遍历二维数组获取有效棋子个数（即非零） sum
         * 2.根据sum创建稀疏数组 new int[sum+1][3] 稀疏数组
         * 3.将原始二维数组 [行,列,有效棋子个数]存到稀疏数组第一行
         * 4.将有效棋子存到稀疏数组中
         */
        // 1.遍历二维数组获取有效棋子个数（即非零） sum
        int sum = 0; // 稀疏数组初始化大小
        for(int[] row : chrees2){
            for (int data : row){
                if (data != 0){
                    sum++;
                }
            }
        }
        // 2.根据sum创建稀疏数组 new int[sum+1][3] 稀疏数组
        // 3.将原始二维数组 [行,列,有效棋子个数]存到稀疏数组第一行
        int[][] sparesArray = new int[sum + 1][3];
        sparesArray[0][0] = 11;
        sparesArray[0][1] = 11;
        sparesArray[0][2] = sum;
        // 4.将有效棋子存到稀疏数组中
        int count = 1; // 用于定义已遍历有效棋子个数,其实也就是稀疏数组从第二行开始存储原二维数组的有效棋子起始行号
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chrees2[i][j] != 0){
                    sparesArray[count][0] = i;
                    sparesArray[count][1] = j;
                    sparesArray[count][2] = chrees2[i][j];
                    count++;
                }
            }
        }
        for (int[] row : sparesArray){
            for (int data : row){
                System.out.printf("\t" + data);
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
        /**
         * 稀疏数组转二维数组
         * 1.通过稀疏数组第一行得到二维数组矩阵大小 new int[11][11]
         * 2.再通过其它行获取具体数据，并赋值给二维数组
         */
        // 1.通过稀疏数组第一行得到二维数组矩阵大小 new int[11][11]
        int[][] chrees3 = new int[sparesArray[0][0]][sparesArray[0][1]];
        // 2.再通过其它行获取具体数据，并赋值给二维数组
        for (int i = 1; i < sparesArray.length; i++) {
            chrees3[sparesArray[i][0]][sparesArray[i][1]] = sparesArray[i][2];
        }
        for (int[] row : chrees3){
            for (int data : row){
                System.out.printf("\t" + data);
            }
            System.out.println();
        }
    }
}
