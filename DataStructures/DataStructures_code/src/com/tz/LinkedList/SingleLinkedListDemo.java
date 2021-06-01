package com.tz.LinkedList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用带 head 头的单向链表实现 –水浒英雄排行榜管理完成对英雄人物的增删改查操
 * 先创建一个head头节点
 * 后面添加的直接加至链表尾部
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        // 显示
        singleLinkedList.list();
        // 修改
        HeroNode newHeroNode = new HeroNode(4, "林冲~~", "豹子头~~");
        singleLinkedList.update(newHeroNode);
        // 修改后显示
        System.out.println("-----------修改后链表-----------");
        singleLinkedList.list();
        // 删除操作
        HeroNode delHeroNode = new HeroNode(3, "吴用", "智多星");
        singleLinkedList.del(delHeroNode);
        // 删除后显示
        System.out.println("-----------删除后链表-----------");
        singleLinkedList.list();
        // 测试有效节点个数
        System.out.println("单链表中有效节点=" + getLength(singleLinkedList.getHead()));
        // 测试查找单链表中的倒数第k个结点
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 1));
        // 测试单链表反转
        reverseList(singleLinkedList.getHead());
        System.out.println("-----------单链表反转后-----------");
        singleLinkedList.list();
    }

    // 以下是单链表各大厂的面试题

    /**
     * 计算单链表中有效节点个数（head节点不统计在内）
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head == null) { // 空单链表
            return 0;
        }
        // 定义辅助变量，指向头节点的下一个节点
        HeroNode cur = head.next;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next; // 后移遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点【新浪面试题】
     * 思路：1.首先遍历计算单链表总长度size
     * 2.得到size后从链表的第一个开始遍历 (size-index)个，就可以得到
     * 3.如果找到了，则返回该节点，否则返回 null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            System.out.println("当前单链表为空...");
            return null;
        }
        // 1.首先遍历计算单链表总长度size
        int size = getLength(head);
        // 2.定义辅助变量，循环
        HeroNode cur = head.next;// 指向头节点的下一个节点
        if (index < 0 || index > size) {
            System.out.println("k非法....");
            return null;
        } else {  //3 // 3 - 1 = 2
            for (int i = 0; i < size - index; i++) {
                // 指针后移
                cur = cur.next;
            }
        }
        return cur;
    }

    /**
     * 单链表的反转【腾讯面试题】
     * 思路：1.创建一个虚拟节点(reverseHead)用来冒充头节点
     * 2.循环遍历就旧链表，将每个有效节点取出放在虚拟节点的下一个（其实就是每遍历到一个有效节点就让它在虚拟节点的下一个已达到反转的效果）
     * 3.将旧链表头节点指向虚拟节点的下一个节点，将虚拟节点的next置空
     *
     * @param head
     */
    public static void reverseList(HeroNode head) {
        // 0.首先判断当前链表是否为空 || 当前链表只有一个节点，若为空则无需反转直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 1.创建一个虚拟节点用来冒充头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode cur = head.next;// 定义辅助变量来进行循环遍历
        HeroNode next = null;// 用来暂时存放遍历到的当前节点的下一个有效节点
        // TODO 暂未理解！！！
        while (cur != null) { // 还存在有效节点继续遍历
            next = cur.next; // 先把下一个节点暂存，因为是单链表当当前节点取出后无法找到下一节点
            cur.next = reverseHead.next;//将 cur 的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将 cur 连接到新的链表上
            cur = next;//让 cur 后移
        }
        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    //定义 SingleLinkedList 管理我们的英雄
    static class SingleLinkedList {
        //先初始化一个头节点, 头节点不要动, 不存放具体的数据
        private HeroNode head = new HeroNode(0, "", "");

        public HeroNode getHead() {
            return head;
        }

        /**
         * 添加节点到单向链表（不考虑编号顺序添加）
         * 思路：1.首先创建辅助变量进行遍历
         * 2.找到链表中最后一个节点（next == null）
         *
         * @param heroNode
         */
        public void add(HeroNode heroNode) {
            // 首先创建辅助变量并指向head
            HeroNode temp = head;
            // 循环遍历找到最后一个节点
            while (true) {
                if (temp.next == null) {
                    // 已找到最后一个节点
                    break;
                } else {
                    // temp后移
                    temp = temp.next;
                }
            }
            // 当退出循环时temp已经指向了链表中最后一个节点
            // 将temp的next指向新节点即可
            temp.next = heroNode;
        }

        /**
         * 按照编号顺序添加至链表中
         * 思路：1.首先需要找到新添加的节点应该插入的位置（创建辅助指针以及遍历）
         * 2.将新节点的next指向temp.next
         * 3.将temp.next指向新节点
         */
        public void addByOrder(HeroNode heroNode) {
            //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
            //因为单链表，因为我们找的 temp 是位于 添加位置的前一个节点，否则插入不了
            HeroNode temp = head;
            Boolean flag = false; // 当前编号是否在链表中已存在，默认为false不存在
            while (true) {
                if (temp.next == null) { //已经是链表最后
                    break;
                }
                if (temp.next.no > heroNode.no) { // 我们需要添加在temp的后一个位置
                    break;
                } else if (temp.next.no == heroNode.no) {// 当前添加的编号已经存在
                    flag = true;
                    break;
                }
                // 所有判断完后temp进行后移
                temp = temp.next;
            }
            // 当一遍循环走完，根据flag进行添加操作
            if (flag) {
                System.out.println("当前添加的编号英雄已经存在....." + heroNode.no);
            } else {
                // 位置找到进行插入
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        /**
         * 根据编号进行删除单链表节点操作：
         * 思路：1.判断单链表是否为空
         * 2.链表中是否有此编号英雄
         * 3.找到需要删除节点的前一个节点（因为是单向链表）
         * 4.将需要删除节点的前一个节点的next指向next.next
         * 5.没有被引用的节点对象会被GC
         */
        public void del(HeroNode delHeroNode) {
            HeroNode temp = head;
            boolean flag = false; // 标志是否找到待删除节点的
            while (true) {
                if (temp.next == null) {//判断单链表是否为空
                    break;
                }
                if (temp.next.no == delHeroNode.no) { //找到要删除的节点
                    flag = true;
                    break;
                }
                temp = temp.next;//指针后移
            }
            if (flag) {
                // 可以进行删除
                temp.next = temp.next.next;
            } else {
                System.out.println("要删除的节点不存在" + delHeroNode.no);
            }
        }

        // 遍历链表进行打印
        public void list() {
            // 首先判断链表是否为空
            if (head.next == null) {
                System.out.println("当前链表为空...");
                return;
            }
            HeroNode temp = head.next;
            while (true) {
                // 是否已经到链表最后节点
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                // 并且需要指针后移
                temp = temp.next;
            }
        }

        /**
         * 根据已有编号英雄进行修改
         * 思路：1.根据 no 编号来修改，即 no 编号不能改.
         * 2.根据 newHeroNode 的 no 来修改即可
         */
        public void update(HeroNode newHeroNode) {
            // 1.首先判断链表是否为空
            if (head.name == null) {
                System.out.println("链表为空.....");
                return;
            }
            // 2.找到需要修改的编号，进行修改
            boolean flag = false; //表示是否找到该节点
            HeroNode temp = head.next;
            while (true) {
                if (temp.no == newHeroNode.no) {
                    flag = true;
                    break;
                }
                // 指针后移，继续查找
                temp = temp.next;
            }
            // 3.根据flag进行判断是否可以进行修改
            if (flag) {
                temp.name = newHeroNode.name;
                ;
                temp.nickname = newHeroNode.nickname;
            } else {
                // 链表遍历结束还是没有找到
                System.out.println("没有找到当前需要的编号英雄....." + newHeroNode.no);
            }
        }
    }

    //定义HeroNode，每个HeroNode对象就是一个节点
    static class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next; //指向下一个节点

        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
}