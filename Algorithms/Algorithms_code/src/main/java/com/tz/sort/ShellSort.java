package com.tz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序是是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort1(arr1);
        System.out.println(Arrays.toString(arr1));

        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        shellSort1(arr); //调用插入排序算法
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    // 希尔排序-->交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arr.length; i++) { // 组数
                for (int j = i - grap; j >= 0; j -= grap) { // grap为步长
                    if (arr[j] > arr[j + grap]) {
                        temp = arr[j];
                        arr[j] = arr[j + grap];
                        arr[j + grap] = temp;
                    }
                }
            }
        }
/*
  // 希尔排序第1轮
        // 因为共有10个数据所以第一轮分为10/2 5组
        int temp = 0;
        for (int i = 5; i < arr.length; i++) { // 组数
            for (int j = i - 5; j >= 0; j -= 5) { // j -= 5为步长
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮后数组："+Arrays.toString(arr));

        // 希尔排序第2轮
        // 因为共有10个数据所以第一轮分为5/2 2组
        for (int i = 2; i < arr.length; i++) { // 组数
            for (int j = i - 2; j >= 0; j -= 2) { // j -= 5为步长
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮后数组："+Arrays.toString(arr));

        // 希尔排序第3轮
        // 因为共有10个数据所以第3轮分为2/2 1组
        for (int i = 1; i < arr.length; i++) { // 组数
            for (int j = i - 1; j >= 0; j -= 1) { // j -= 5为步长
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮后数组："+Arrays.toString(arr));
 */
    }

    // 希尔排序-->移位法
    public static void shellSort1(int[] arr) {
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            // 从第 grap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = grap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - grap]) {
                    while (j - grap >= 0 && temp < arr[j - grap]) {
                        //移动
                        arr[j] = arr[j - grap];
                        j -= grap;
                    }
                    //当退出 while 后，就给 temp 找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
