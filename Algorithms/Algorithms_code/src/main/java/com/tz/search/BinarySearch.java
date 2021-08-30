package com.tz.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：
 * 前提：针对有序数组
 * 思路：
 * 1.首先确定数组中间下标
 * 2.目标数字与下标对应元素进行比对
 * 2.1 如果大于则向右递归
 * 2.2 如果小于则向左递归
 * 3.递归的关键就在于什么时候结束
 * 3.1 当左指针>右指针时，表示没有找到目标元素也要结束递归
 * 3.2 找到元素结束递归
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 18);
        System.out.println("resIndex=" + resIndex);

        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1234};
        List<Integer> resIndexList = binarySearch2(arr2, 0, arr2.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    /**
     * @param arr     数组
     * @param left    左指针
     * @param right   有指针
     * @param findVal 目标数字
     * @return 找到返回对应元素下标，没有找到则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) { //当左指针>右指针时结束递归,表示没有找到
            return -1;
        }
        int mid = (left + right) / 2; //中间指针
        int midVal = arr[mid];
        if (findVal > midVal) { //表示目标数字在当前中间指针右边，向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//表示目标数字在当前中间指针左边，向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {// 找到
            return mid;
        }
    }

    /**
     * 思考：{1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     * 如何返回所有满足条件的下标
     * 思路分析：
     * 1.在找到mid索引值，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3.向mid索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4.将Arraylist返回
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) { //当左指针>右指针时结束递归,表示没有找到
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2; //中间指针
        int midVal = arr[mid];
        if (findVal > midVal) { //表示目标数字在当前中间指针右边，向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//表示目标数字在当前中间指针左边，向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {// 找到
            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //
            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }
            return resIndexlist;
        }
    }
}
