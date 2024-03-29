package com.tz.hashtab;

import java.util.Scanner;

/**
 * 哈希表
 * <p>
 * 其实就是元素为链表的数组，根据特定规则进行hash至不同的链表，链表里存的是实际数据
 * 这里使用一个google上机题来实现哈希表（缓存作用）
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("addByOrder:  顺序添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "addByOrder":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    System.out.println("输入名字");
                    name = scanner.next();
                    //创建 雇员
                    emp = new Emp(id, name);
                    hashTab.addByOrder(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray; //存数据的链表
    private int size; //表示有多少条链表，可以根据这个进行取模hash

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //必须根据size分别创建存数据的链表EmpLinkedList！！！
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id ,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);

    }

    //遍历所有的链表,遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //根据输入的id,删除雇员
    public void delete(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        //是否可以找到当前需要删除雇员
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp == null) {
            System.out.println("在哈希表中，没有找到该雇员~");
            return;
        }
        //到指定的链表删除雇员数据
        empLinkedListArray[empLinkedListNO].del(id);
    }

    //添加顺序雇员
    public void addByOrder(Emp emp) {
        //根据员工的id ,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].addByOrder(emp);

    }

    //散列函数, 使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }


}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //next 默认为 null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList ,表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp,因此这个链表的head 是直接指向第一个Emp
    private Emp head; //默认null

    //添加雇员到链表
    //说明
    //1. 假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) { //不用考虑id存在重复
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        //退出时直接将emp 加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) { //说明链表为空
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp, 如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//以后
        }

        return curEmp;
    }

    //根据id删除雇员
    //没有被引用的节点对象会被GC
    public void del(int no) {
        // 由于此时head存有雇员元素，采用单指针无法删除，如果头节点没有存数据只是虚节点也可采用单指针
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        if (head.id == no) {//头节点就是要删除的雇员元素
            head = null;
            return;
        }
        Emp curr = head.next; // 头节点的下一节点
        Emp prev = head; // 就是头节点
        while (curr != null && curr.id != no) {
            // 双指针全部后移,一直查找
            prev = curr;
            curr = curr.next;
        }
        if (curr == null && prev.id == no) {
            prev = null;//最后一个节点是要删除的雇员
            return;
        }
        //要删除的节点在链表中间
        prev.next = curr.next;
    }

    //即使输入雇员id乱序，存入哈希表也按照顺序插入
    public void addByOrder(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用两个辅助的指针，帮助定位到目标位置
        Emp curr = head.next; // 头节点的下一节点
        Emp prev = head; // 就是头节点
        while (curr != null && emp.id > curr.id) { //不用考虑id存在重复
            // 双指针全部后移,一直查找
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) { //已到最后直接添加到链表最后节点
            prev.next = emp;
            return;
        }
        //要插入雇员在链表中间
        prev.next = emp;
        emp.next = curr;
    }
}
