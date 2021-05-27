package com.tz.queue;

import java.util.Scanner;

/**
 * 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 maxSize 是该队列的最大容量。
 * 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后端的下标，
 * front 会随着数据输出而改变，而 rear 则是随着数据输入而改变
 * 当我们将数据存入队列时称为”addQueue”，addQueue 的处理需要有两个步骤：思路分析
 * 1) 将尾指针往后移：rear+1 , 当 front == rear 【空】
 * 2) 若尾指针 rear 小于队列的最大下标 maxSize-1，则将数据存入 rear 所指的数组元素中，否则无法存入数据。
 * rear == maxSize - 1[队列满]
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 进行测试
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
    // 使用数组模拟队列
    static class ArrayQueue {
        private int maxSize; // 队列最大容量
        private int front; // 队列头部
        private int rear; // 队列尾部
        private int[] arr; // 该数据用于存放数据, 模拟队列

        public ArrayQueue(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1; // 指向队列头部，分析出 front 是指向队列头的前一个位置.
            rear = -1; // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        }

        // 判断队列是否满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return front == rear;
        }

        // 向队列中添加数据
        public void addQueue(int n) {
            // 首先判断当前队列是否已满
            if (isFull()) {
                System.out.println("queue is full.....");
            }
            rear++; // 让 rear 后移
            arr[rear] = n;
//            arr[++rear] = n;
        }

        // 从队列中取数据
        public int getQueue() {
            // 首先判断是否为空
            if (isEmpty()) {
                throw new RuntimeException("the queue is empty....");
            }
            front++; // front后移一个位置
            return arr[front];
        }

        // 显示队列的所有数据
        public void showQueue() {

            if (isEmpty()) {
                System.out.println("the queue is empty....");
                return;
            }
            // 遍历
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }

        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            // 判断
            if (isEmpty()) {
                throw new RuntimeException("the queue is empty....");
            }
            return arr[front + 1];
        }
    }
}


