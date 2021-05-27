package com.tz.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列:
 * 对前面的数组模拟队列的优化，充分利用数组. 因此将数组看做是一个环形的。(通过 取模的方式来实现即可)
 * 分析说明：
 * 1) 尾索引的下一个为头索引时表示队列满，即将队列容量空出一个作为约定,这个在做判断队列满的
 * 时候需要注意 (rear + 1) % maxSize == front 满]
 * 2) rear == front [空]
 * 3) front：指向队列的第一个元素，初始化 front = 0
 * 4) rear：指向队列最后一个元素的后一个位置，初始化 rear = 0
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        // 进行测试
        ArrayQueue queue = new ArrayQueue(4);
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
        private int front; // 指向队列的第一个元素，初始化 front = 0
        private int rear; // 指向队列最后一个元素的后一个位置，初始化 rear = 0
        private int[] arr; // 该数据用于存放数据, 模拟队列

        public ArrayQueue(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
        }

        // 判断队列是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
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
            // 由于现在rear初始化为0，所以直接加入
            arr[rear] = n;
            // rear位置后移，但由于此时为环形队列，必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        // 从队列中取数据
        public int getQueue() {
            // 首先判断是否为空
            if (isEmpty()) {
                throw new RuntimeException("the queue is empty....");
            }
            // 1.首先用临时变量保存当前值
            // 2.front后移，考虑取模
            // 3.返回临时变量
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        // 显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("the queue is empty....");
                return;
            }
            // 此时遍历由于是环形队列，所以不能遍历数组长度，应该遍历数组中有效元素个数
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }
        // 计算环形队列中有效元素个数
        public int size(){
            return (rear + maxSize - front) % maxSize;
        }

        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            // 判断
            if (isEmpty()) {
                throw new RuntimeException("the queue is empty....");
            }
            return arr[front];
        }
    }
}