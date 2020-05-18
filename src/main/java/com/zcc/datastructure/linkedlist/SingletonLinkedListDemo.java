package com.zcc.datastructure.linkedlist;

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
        System.out.println("删除后：");
        singletonLinkedList.list();
    }
}

class SingletonLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
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
