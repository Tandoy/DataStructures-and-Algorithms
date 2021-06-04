package com.tz.LinkedList;

/**
 * 分析 双向链表的遍历，添加，修改，删除的操作思路===》代码实现
 * 1)遍历和单链表一样，只是可以向前，也可以向后查找
 * 2)添加(默认添加到双向链表的最后)
 * (1)先找到双向链表的最后这个节点
 * (2)temp.next = newHeroNode
 * (3)newHeroNode.pre = temp;
 * 3)修改思路和原来的单向链表一样.
 * 4)删除
 * (1)因为是双向链表，因此，我们可以实现自我删除某个节点
 * (2)直接找到要删除的这个节点，比如 temp
 * (3)temp.pre.next = temp.next
 * (4)temp.next.pre = temp.pre;
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 添加
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero3);
        // 显示
        doubleLinkedList.list();
        // 修改
        HeroNode newHeroNode = new HeroNode(4, "林冲~~", "豹子头~~");
        doubleLinkedList.update(newHeroNode);
        // 修改后显示
        System.out.println("-----------修改后链表-----------");
        doubleLinkedList.list();
        // 删除操作
        HeroNode delHeroNode = new HeroNode(3, "吴用", "智多星");
        doubleLinkedList.del(delHeroNode);
        // 删除后显示
        System.out.println("-----------删除后链表-----------");
        doubleLinkedList.list();
    }

    // 创建一个双向链表的类
    static class DoubleLinkedList {
        //先初始化一个头节点, 头节点不要动, 不存放具体的数据
        private HeroNode head = new HeroNode(0, "", "");

        // 获取双向链表头节点
        public HeroNode getHead() {
            return head;
        }

        // 遍历双向链表进行打印
        public void list() {
            // 首先判断链表是否为空
            if (head.next == null) {
                System.out.println("当前双向链表为空...");
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
         * 添加(默认添加到双向链表的最后)
         * 思路：1.首先创建辅助变量进行遍历
         * 2.找到链表中最后一个节点（next == null）
         * 3.temp.next = newHeroNode
         * 4.newHeroNode.pre = temp;
         *
         * @param newHeroNode
         */
        public void add(HeroNode newHeroNode) {
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
            // 将temp的next指向新节点并且将新节点的pre指向temp即可
            temp.next = newHeroNode;
            newHeroNode.pre = temp;
        }

        /**
         * 按照编号顺序添加至链表中
         * 思路：1.首先需要找到新添加的节点应该插入的位置（创建辅助指针以及遍历）
         * 2.temp.pre.next = heroNode;
         * 3.heroNode.next = temp;
         */
        public void addByOrder(HeroNode heroNode) {
            //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
            HeroNode temp = head;
            boolean flag = false; // 这个是用来标志是否可以添加
            boolean flag1 = false; // 这个用来标识遍历到的节点是不是最后一个节点，如果遍历到了这里就说明要添加的节点需添加在最后面，添加到链表最后面与插入在链表中间方式是不同的
            while (true) {
                if (temp.no > heroNode.no) {
                    break;
                }// 注意：这个判断一定要写在第三个判断前面，因为当添加节点的大小和最后一个节点恰好相等时就会引起死循环
                if (temp.no == heroNode.no) {
                    flag = true;
                    break;
                }
                if (temp.next == null) {
                    flag1 = true;
                    break;
                }
                temp = temp.next; // 指针后移
            }
            if (flag) {
                System.out.println(heroNode.no + "这个编号已经存在");
            } else if (flag1) { // 添加编号英雄在双向链表最后面
                temp.next = heroNode;
                heroNode.pre = temp;
            } else { // 添加在双向链表中间部分
                heroNode.next = temp;
                temp.pre.next = heroNode;
                heroNode.pre = temp.pre;
                temp.pre = heroNode;
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
                System.out.println("双向链表为空.....");
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
                temp.nickname = newHeroNode.nickname;
            } else {
                // 链表遍历结束还是没有找到
                System.out.println("没有找到当前需要的编号英雄....." + newHeroNode.no);
            }
        }

        /**
         * 根据编号进行删除单链表节点操作：
         * 思路：1.判断单链表是否为空
         * 2.链表中是否有此编号英雄
         * 3.找到需要删除节点（因为是双向链表）
         * 4.temp.pre.next = temp.next
         * 5.temp.next.pre = temp.pre
         */
        public void del(HeroNode delHeroNode) {
            HeroNode temp = head.next;
            boolean flag = false; // 标志是否找到待删除节点的
            while (true) {
                if (temp == null) {// 已经找到最后一个节点的next
                    break;
                }
                if (temp.no == delHeroNode.no) { //找到要删除的节点
                    flag = true;
                    break;
                }
                temp = temp.next;//指针后移
            }
            if (flag) {
                // 进行删除
                temp.pre.next = temp.next;
                // 加这里判断条件是因为：
                // 当要删除的节点是最后一个节点时，temp.next = null,temp.next.pre会存在空指针异常
                // 所以删除最后一个节点时只需要将其上一节点的next置为null即可
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.println("要删除的节点不存在" + delHeroNode.no);
            }
        }
    }

    //定义HeroNode，每个HeroNode对象就是一个节点
    static class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next; //指向下一个节点
        public HeroNode pre; //指向前一个节点

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
