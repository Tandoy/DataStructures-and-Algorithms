package com.tz.stack;

/**
 * 使用栈完成计算一个表达式结果
 * 思路分析：
 * 1.首先创建两个栈：一个存储数字，一个存储操作符
 * 2.创建一个辅助索引index用来遍历表达式
 * 3.如果index遍历到是一个数字则直接push到数栈
 * 4.如果index遍历到是操作符则需要分两种情况：
 * 4.1 如果操作符入栈前当前栈是空栈则直接入栈
 * 4.2 如果操作符入栈前当前栈不为空，则与栈顶中操作符进行比对，如果当前操作符的优先级小于或者等于栈中操作符，则从数栈中pop出两个数字并且从操作符栈中pop出一个操作符进行计算得到结果。
 * 并将计算结果再入数栈，然后将当前操作符入操作符栈；如果当前操作符的优先级大于栈中操作符则直接入栈
 * 5.当index遍历完毕，就顺序的从数栈pop出两个数字和从操作符栈中pop出一个操作符进行计算
 * 6.最后在数栈中只会有一个数字，即为表达式的最终计算结果
 * 验证：3+2*6-12 = 13
 */
public class Calculator {
    public static void main(String[] args) {
        // 0.首先创建一个表达式
        String expression = "30+2*6-2"; // 结果：40
        // 1.创建两个栈：数栈、一个符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        // 2.创建相关初始化变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到 char 保存到 ch
        String keepNum = ""; // 用于拼接多位数字符串
        // 3.开始循环遍历表达式
        while (true) {
            // 依次得到表达式中每一个字符
            ch = expression.substring(index,index + 1).charAt(0); //其实就是在截取每个字符
            // 判断ch依次进行相依处理
            if (operStack.isOper(ch)) { // 如果是操作符
                if (!operStack.isEmpty()) {
                    // 如果操作符入栈前当前栈不为空，则与栈顶中操作符进行比对，如果当前操作符的优先级小于或者等于栈中操作符，则从数栈中pop出两个数字并且从操作符栈中pop出一个操作符进行计算得到结果。
                    // 并将计算结果再入数栈,然后将当前操作符入操作符栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        // 如果当前操作符的优先级大于栈中操作符则直接入栈
                        operStack.push(ch);
                    }
                }else {
                    // 如果操作符入栈前当前栈是空栈则直接入栈
                    operStack.push(ch);
                }
            }else { // 如果是数字直接入数栈
                    // 此处存在问题：当表达式中存在多位数时会计算出错
//                    numStack.push(ch - 48); // '1' --> 1 ascii
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向 expression 的表达式的 index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                keepNum += ch;
                if (index == expression.length() - 1) {
                    // 如果index是最后一位则不需要后看表达式后一位直接入栈，否则会报空指针
                    numStack.push(Integer.valueOf(keepNum));
                }else {
                    // index不是最后一位，则需要每次都要往后看是不是还是数字
                    if (operStack.isOper(expression.substring(index + 1,index + 2).charAt(0))) {
                        numStack.push(Integer.valueOf(keepNum));
                        // keepNum入栈后注意需要将其清空！！！
                        keepNum = "";
                    }
                }
            }
            // index后移
            index++;
            // 判读是否到了最后
            if (index == expression.length()) {
                break;
            }
        }
        while (true) {
            //如果符号栈为空，则计算到最后的结果, 数栈中只有一个数字【结果】
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }

    // 数组模拟创建栈
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

        /**
         * 返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
         * 数字越大，则优先级就越高.
         *
         * @param oper
         * @return
         */
        public int priority(int oper) { // int 与 char 可直接进行比较
            if (oper == '*' || oper == '/') {
                return 1;
            } else if (oper == '+' || oper == '-') {
                return 0;
            } else {
                return -1; // 假定目前的表达式只有 +, - , * , /
            }
        }

        /**
         * 判断是不是一个运算符
         *
         * @param val
         * @return
         */
        public boolean isOper(char val) {
            return val == '+' || val == '-' || val == '*' || val == '/';
        }

        /**
         * 计算方法
         *
         * @param num1 从数栈中pop的第一个数
         * @param num2 从数栈中pop的第二个数
         * @param oper 从操作符栈中pop出的操作符
         * @return
         */
        public int cal(int num1, int num2, int oper) {
            int res = 0; // res 用于存放计算的结果
            switch (oper) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;// 注意顺序
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                    break;
                default:
                    break;
            }
            return res;
        }

        /**
         * 返回栈顶数据，注意不是出栈
         * @return
         */
        public int peek() {
            return stack[top];
        }
    }
}
