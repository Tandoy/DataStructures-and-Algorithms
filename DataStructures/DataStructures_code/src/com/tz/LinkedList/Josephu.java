package com.tz.LinkedList;

/**
 * 使用单向环形链表解决约瑟夫问题
 * 1.约瑟夫问题描述：
 * 设编号为 1，2，… n的n个人围坐一圈，约定编号为 k（1<=k<=n）的人从1开始报数，数到m的那个人出列，它的下一位又从 1 开始报数，
 * 数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 * 2.构建单向环形链表思路：
 * 2.1 首先创建第一个节点，让first指向该节点，并形成环形
 * 2.2 后面每创建一个加入环形链表中即可
 * 3.遍历环形链表
 * 3.1 首先创建一个辅助指针curBoy，并指向first节点
 * 3.2 while循环，结束条件：curBoy.next = first
 */
public class Josephu {
    public static void main(String[] args) {
        // 测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        // 测试约瑟夫问题
        circleSingleLinkedList.countBoy(1,2,5);
    }

    static class CircleSingleLinkedList {
        // 创建一个 first 节点,当前没有编号
        private Boy first = null;

        /**
         * 构建一个单向的环形链表
         *
         * @param nums
         */
        public void addBoy(int nums) { // nums：代表单向环形链表共有多少个节点
            // 1.首先对nums进行校验
            if (nums < 1) {
                System.out.println("nums必须大于1....");
                return;
            }
            // 2.开始添加for循环
            Boy curBoy = null; // 辅助变量来循环创建
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i); // 需要添加的节点
                // 2.1 由于只有一个节点时需要自己成环，所以添加方式与后续节点不一致
                if (i == 1) {
                    first = boy;
                    first.setNext(first); // 若只有一个节点，让自己成环形链表
                    curBoy = first; // 辅助变量赋值
                } else { // 除了第一个节点外的添加方式
                    // 2.2 思路：首先让curBoy.next = boy,然后boy.next = first,最后将curBoy后移至最新创建的节点
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy = boy;
                }
            }
        }

        /**
         * 遍历当前的环形链表
         */
        public void showBoy() {
            // 判断链表是否为空
            if (first == null) {
                System.out.println("没有任何小孩~~");
                return;
            }
            // 创建辅助变量来进行循环遍历
            Boy curBoy = first;// 首先直接指向第一个节点
            while (true) {
                System.out.println("当前小孩.." + curBoy.getNo());
                if (curBoy.getNext() == first) { // 当前节点已经是最后一个
                    break;
                }
                curBoy = curBoy.getNext(); // curBoy指针后移
            }
        }

        /**
         * 约瑟夫问题：根据用户的输入，计算出小孩出圈的顺序
         * 思路分析：
         * 1.首先创建一个辅助指针helper指向单向链表最后一个节点
         * 2.报数前先让first和helper移动k-1次
         * 2.当报数时，让helper和first同时移动 m-1次
         * 3.这时first指向的节点就可以移除：first = first.getNext(); helper.setNext(first)
         * 4.若单向链表里有五个节点出圈顺序即位：2->4->1->5->3
         *
         * @param startNo  表示从第几个小孩开始数数 k
         * @param countNum 表示数几下 m
         * @param nums     表示最初有多少小孩在圈中 n
         */
        public void countBoy(int startNo, int countNum, int nums) {
            // 0.首先对用户输入参数进行校验
            if (first == null || startNo < 1 || startNo > nums) {
                System.out.println("输入参数有误，请重新输入....");
                return;
            }
            // 1.首先创建一个辅助指针helper指向单向链表最后一个节点
            Boy helper = first;
            while (true) {
                if (helper.getNext() == first) { // helper已经到达最后一个节点
                    break;
                }
                helper = helper.getNext(); // helper后移
            }
            // 2.报数前先让first和helper移动k-1次
            for (int j = 0; j < startNo - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 2.当报数时，让helper和first同时移动 m-1次
            // 3.这时first指向的节点就可以移除：first = first.getNext(); helper.setNext(first)
            while (true) {
                // 当单向链表中只有一个节点时结束循环
                if (helper == first) {
                    break;
                }
                // 将helper和first同时移动 m-1次
                for (int k = 0; k < countNum - 1; k++) {
                    first = first.getNext();
                    helper = helper.getNext();
                }
                // 打印要移除单向链表的节点
                System.out.printf("小孩%d 出圈\n", first.getNo());
                // 报数后移完成后此时first指向的就是要移除单向链表的节点
                first = first.getNext();
                helper.setNext(first);
            }
            // 打印最后留在单向链表中的节点
            System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
        }
    }

    static class Boy {
        private int no;// 编号
        private Boy next; // 指向下一个节点,默认 null

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }
}
