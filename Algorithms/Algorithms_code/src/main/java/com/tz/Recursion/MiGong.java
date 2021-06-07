package com.tz.Recursion;

/**
 * 使用递归解决迷宫问题
 * 最短路径：只要用四种不同策略进行走，然后计算map中2的元素个数进行比较即可
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用 1 表示墙
        // 上下全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板1
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        // 迷宫开始
        setWay(map, 1, 1);
        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 如下说明：
     * 1. map 表示地图
     * 2. i,j 表示从地图的哪个位置开始出发 (1,1)
     * 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
     * 4. 约定： 当 map[i][j] 为 0 表示该点没有走过 当为 1 表示墙 ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
     *
     * @param map 二维数组表示地图
     * @param i   起始位置
     * @param j   起始位置
     * @return 是否找到true/false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 终点已经找到返回true
            return true;
        } else { // 如果还没找到则会存在四种情况
            if (map[i][j] == 0) { // 当前节点还没走过
                // 先假定可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果 map[i][j] != 0 , 可能是 1， 2， 3
                return false;
            }
        }
    }

    /**
     * 修改找路的策略，改成 上->右->下->左
     *
     * @param map 二维数组表示地图
     * @param i   起始位置
     * @param j   起始位置
     * @return 是否找到true/false
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 通路已经找到 ok
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前这个点还没有走过
                //按照策略 上->右->下->左
                map[i][j] = 2; // 假定该点是可以走通.
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下
                    return true;
                } else if (setWay2(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果 map[i][j] != 0 , 可能是 1， 2， 3
                return false;
            }
        }
    }
}

