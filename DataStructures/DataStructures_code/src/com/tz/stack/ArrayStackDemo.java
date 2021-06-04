package com.tz.stack;

import java.util.Scanner;

/**
 * 实现栈的思路分析：
 * 1.使用数组模拟栈
 * 2.定义一个top表示栈顶，初始化为-1
 * 3.入栈：当有数据入栈时，top++,stack[top] = data;
 * 4.出栈：int value = stack[top];top--;return value;
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下 ArrayStack 是否正确
        //先创建一个 ArrayStack 对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }

    // 使用数组模拟栈
    static class ArrayStack {
        private int maxSize; // 栈的大小
        private int[] stack; // 数组，数组模拟栈，数据就放在该数组
        private int top = -1;// top 表示栈顶，初始化为-1

        //构造器
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        //判断栈满
        public boolean isFull() {
            return top == maxSize - 1;
        }

        //栈空
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * 入栈：当有数据入栈时，top++,stack[top] = data;
         *
         * @param value
         */
        public void push(int value) {
            // 1.首先判断栈是否为空
            if (isFull()) {
                System.out.println("stack is full....");
                return;
            }
            top++;
            stack[top] = value;
        }

        /**
         * 出栈：int value = stack[top];top--;return value;
         */
        public int pop() {
            // 1.首先判断栈是否为空
            if (isEmpty()) {
                throw new RuntimeException("stack is empty....");
            }
            int value = stack[top];
            top--;
            return value;
        }

        /**
         * 遍历栈数据
         */
        public void list() {
            if (isEmpty()) {
                System.out.println("stack is empty....");
                return;
            }
            //从栈顶开始显示数据
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n", i, stack[i]);
            }
        }
    }
}
