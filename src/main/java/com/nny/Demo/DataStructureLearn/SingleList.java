package com.nny.Demo.DataStructureLearn;

public class SingleList {
    private Object o;
    private SingleListNode head;

    //链表就地倒置
//    public void reverse(){
//        SingleListNode p = head.getNextNode();
//        while(q != null) {
//            SingleListNode q = p.getNextNode();
//            head.setNextNode(p.getNextNode());
//            p.setNextNode(head);
//            head = p;
//            p = q;
//            q = q.getNextNode();
//        }
//    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public SingleListNode getHead() {
        return head;
    }

    public void setHead(SingleListNode head) {
        this.head = head;
    }
}
