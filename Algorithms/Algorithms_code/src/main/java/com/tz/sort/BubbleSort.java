package com.tz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）, 依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就像水底下的气泡一样逐渐向上冒。
 * 优化：
 * 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。
 * 排序过程：
 * (1) 一共进行 数组的大小-1 次大的循环
 * (2) 每一趟排序的次数在逐渐的减少
 * (3) 如果我们发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序。这个就是优化
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        // 测试冒泡排序
        bubbleSort(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
        //测试一下冒泡排序的速度 O(n^2),测试80000个数据
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        // 统计冒泡排序在万级数据量下所需时间
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data);
        System.out.println("排序前的时间是=" + date1Str);
        bubbleSort2(arr2);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void bubbleSort(int[] arr) {
        // 冒泡排序时间复杂度：O(n^2)
        int temp = 0; // 中间变量用来元素位置交换
        boolean flag = false; // 用来标识一趟排序过程中是否发生过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 如果元素逆序则进行位置交换
                    flag = true;// 发生交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "躺排序后结果");
            System.out.println(Arrays.toString(arr));
            if (flag == false) { // 如果没有发生过交换则可以提前停止循环
                break;
            } else {
                flag = false;
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        // 冒泡排序时间复杂度：O(n^2)
        int temp = 0; // 中间变量用来元素位置交换
        boolean flag = false; // 用来标识一趟排序过程中是否发生过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 如果元素逆序则进行位置交换
                    flag = true;// 发生交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) { // 如果没有发生过交换则可以提前停止循环
                break;
            } else {
                flag = false;
            }
        }
    }
}
