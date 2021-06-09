package com.tz.Recursion;

/**
 * 8皇后问题：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行 、同一列或同一斜线上，问有多少种摆法(92)。
 * 思路分析：
 * 1) 第一个皇后先放第一行第一列
 * 2) 第二个皇后放在第二行第一列、然后判断是否 OK， 如果不 OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3) 继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 * 说明：
 * 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] =
 * {0 , 4, 7, 5, 2, 6, 1, 3} //对应 arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1
 * 行的第 val+1 列
 */
public class Queue8 {
    // 皇后个数
    int max = 8;
    // 定义数组用来存储结果数据 arr[8] = {0,4,7,5,2,6,1,3};应用类型在递归栈中共享
    int[] array = new int[8];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0); // 放置第一个皇后
        System.out.printf("一共有%d 解法", count);
        System.out.println();
        System.out.printf("一共判断冲突的次数%d 次", judgeCount); // 1.5w
    }

    /**
     * 从第几行开始放置皇后；规定是0即为放置第一个皇后
     * check是每一次递归时，进入到 check 中都有 for(int i = 0; i < max; i++)，因此会有回溯
     *
     * @param n
     */
    private void check(int n) {
        if (n == max) { //n = 8,其实8个皇后就既然放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置 n个皇后到i列时，是否冲突
            if (judge(n)) { // 不冲突
                //接着放 n+1 个皇后,即开始递归
                check(n + 1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后，放置在本行的 后移的一个位置
        }
    }

    /**
     * 查看第n个皇后摆放时，就去查看第n个皇后与前面的n-1个皇后是否攻击：任意两个皇后都不能处于同一行 、同一列或同一斜线上
     *
     * @param n ：当前摆放的皇后
     * @return 是否冲突
     */
    private Boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // arr[8] = {0,4,7,5,2,6,1,3}：第一个皇后在第一行第一列，第二个皇后在第二行第五列......
            // 1.array[i] == array[n]：这个其实就是在判断当前皇后与其他皇后是否在同一列
            // 2.Math.abs(n - i) == Math.abs(array[n] - array[i])：这个其实就是在是否在同一斜线
            // 例如当前是第二个皇后放置在第二行第二列,第一个皇后放置在第一行第一列：n = 1;array[1] = 1
            // i = 0;array[0] = 0
            // Math.abs(1 - 0) == Math.abs(array[1] - array[0]))
            // 3.由于n++所以无需判断当前皇后与其他皇后是否在同一行
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 将所有解法打印出来
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.printf(array[i] + " ");
        }
        System.out.println();
    }
}
