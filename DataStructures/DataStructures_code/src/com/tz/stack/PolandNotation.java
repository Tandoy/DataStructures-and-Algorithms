package com.tz.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
 * 支持小括号和多位数整数
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * 1．从左至右扫描，将 3 和 4 压入堆栈；
 * 2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
 * 3．将 5 入栈；
 * 4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
 * 5．将 6 入栈；
 * 6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 1.定义一个逆波兰表达式 (30+4)×5-6 => 30 4 + 5 × 6 -  结果：164
        String suffixExpression = "30 4 + 5 * 6 -";
        // 2.将suffixExpression存入一个集合中
        List<String> stringList = getListString(suffixExpression);
        System.out.println(stringList);
        int res = calculate(stringList);
        System.out.println("计算的结果是=" + res);
    }

    public static List<String> getListString(String suffixExpression) {
        // 首先按空格截取
        String[] split = suffixExpression.split(" ");
        // 循环放入集合中
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls) {
        // 创建给栈, 只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        // 遍历 ls
        for (String item : ls) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop 出两个数，并运算， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把 res 入栈
                stack.push("" + res);
            }
        }
        //最后留在 stack 中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}