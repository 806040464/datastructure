package com.zcc.datastructure.linkedlist;


import java.awt.geom.CubicCurve2D;
import java.util.Stack;

public class SingletonLinkedListDemo {

    public static void main(String[] args) {
        SingletonLinkedList singletonLinkedList = new SingletonLinkedList();
//        singletonLinkedList.add(new HeroNode(1, "宋江", "及时雨"));
//        singletonLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
//        singletonLinkedList.add(new HeroNode(3, "吴用", "智多星"));
//        singletonLinkedList.add(new HeroNode(4, "林冲", "豹子头"));

        singletonLinkedList.addBySort(new HeroNode(1, "宋江", "及时雨"));
        singletonLinkedList.addBySort(new HeroNode(4, "林冲", "豹子头"));
        singletonLinkedList.addBySort(new HeroNode(3, "吴用", "智多星"));
        singletonLinkedList.addBySort(new HeroNode(2, "卢俊义", "玉麒麟"));

        singletonLinkedList.list();

        singletonLinkedList.update(new HeroNode(2, "xiao卢", "xiao玉麒麟"));
        System.out.println("修改后：");
        singletonLinkedList.list();

        singletonLinkedList.del(new HeroNode(2, "xiao卢", "xiao玉麒麟"));
        singletonLinkedList.del(new HeroNode(4, "林冲", "豹子头"));
        System.out.println("删除后：");
        singletonLinkedList.list();

        int length = singletonLinkedList.getLength(singletonLinkedList.getHead());
        System.out.println("有效节点个数：" + length);

        HeroNode indexNode = singletonLinkedList.findLastIndexNode(singletonLinkedList.getHead(), 2);
        System.out.println("倒数第 " + 2 + " 个节点：" + indexNode);

        System.out.println("反转后：");
        singletonLinkedList.reverseNode(singletonLinkedList.getHead());
        singletonLinkedList.list();


        System.out.println("逆序打印，不改变结构：");
        singletonLinkedList.reversePrint(singletonLinkedList.getHead());
        System.out.println("原先的单链表：");
        singletonLinkedList.list();

        SingletonLinkedList singletonLinkedList1 = new SingletonLinkedList();
        singletonLinkedList1.add(new HeroNode(1, "宋江", "及时雨"));
        singletonLinkedList1.add(new HeroNode(2, "卢俊义", "玉麒麟"));
        singletonLinkedList1.add(new HeroNode(6, "吴用", "智多星"));
        singletonLinkedList1.add(new HeroNode(8, "林冲", "豹子头"));
        SingletonLinkedList singletonLinkedList2 = new SingletonLinkedList();
        singletonLinkedList2.add(new HeroNode(3, "宋江", "及时雨"));
        singletonLinkedList2.add(new HeroNode(4, "卢俊义", "玉麒麟"));
        singletonLinkedList2.add(new HeroNode(5, "吴用", "智多星"));
        singletonLinkedList2.add(new HeroNode(7, "林冲", "豹子头"));
        System.out.println("合并单链表：");
        HeroNode heroNode = singletonLinkedList.mergeNodeRecurse(singletonLinkedList1.getHead(), singletonLinkedList2.getHead());
        HeroNode cur = heroNode;
        while (null != cur) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

class SingletonLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /*
     * 合并两个有序单链表，合并之后的单链表依然有序
     * 方式1：递归
     * 方式2：while循环
     */
    public HeroNode mergeNodeRecurse(HeroNode node1, HeroNode node2) {
        if (null == node1.next || null == node2.next) {
            return null == node1.next ? node2.next : node1.next;
        }
        HeroNode result;
        if (node1.next.no <= node2.next.no) {
            result = node1.next;
            result.next = mergeNodeRecurse(node1.next, node2);
        } else {
            result = node2.next;
            result.next = mergeNodeRecurse(node1, node2.next);
        }
        return result;
    }

    /*
     * 逆序打印单链表
     * 方式1：先将单链表进行反转，再进行遍历即可
     *      缺点：会破坏原来的单链表结构，不建议
     * 方式2：利用栈，先进后出的特点实现逆序打印
     *
     */
    public void reversePrint(HeroNode head) {
        if (null == head.next) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (null != cur) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            HeroNode node = stack.pop();
            System.out.println(node);
        }
    }

    public void reverseNode(HeroNode head) {
        if (null == head.next || null == head.next.next) {
            return;
        }
        //辅助指针（变量）来遍历链表
        HeroNode cur = head.next;
        //暂时保存当前节点的下一节点，防止cur改变引用后找不到原链表的数据
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (null != cur) {
            //暂时保存当前节点的下一节点，防止cur改变引用后找不到原链表的数据
            next = cur.next;
            //将cur节点头插法插入reverseHead
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            //cur后移
            cur = next;
        }
        //将原先头结点指向反转后的链表
        head.next = reverseHead.next;
    }

    /*
     * 查找单链表中的倒数第K个节点【新浪面试题】
     * 思路：
     * 1.接收head节点，和index
     * 2.index表示倒数第几个节点
     * 3.先遍历链表，得到链表的总长度
     * 4.得到链表后，我们从链表头遍历（size-length）个，可以得到
     * 注意：此方法默认的辅助节点为head.next，所以获取头结点的下一节点并未走for循环，而是直接输出的辅助节点
     */
    public HeroNode findLastIndexNode(HeroNode head, int index) {
        if (null == head.next) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public int getLength(HeroNode head) {
        if (null == head.next) {
            return 0;
        }
        int len = 0;
        HeroNode cur = head.next;
        while (null != cur) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (null == temp.next) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void update(HeroNode heroNode) {
        HeroNode temp = head.next;
        while (true) {
            if (temp.no == heroNode.no) {
                break;
            }
            temp = temp.next;
        }
        temp.name = heroNode.name;
        temp.nickName = heroNode.nickName;
    }

    public void del(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (null == temp.next) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除节点不存在！");
        }
    }

    public void addBySort(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (null == temp.next) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("编号已存在：%d" + temp.no);
        } else {
            //先将插入节点的next置为temp节点的next，再讲temp节点的next置为插入节点
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void list() {
        if (null == head.next) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        while (true) {
            if (null == temp) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
