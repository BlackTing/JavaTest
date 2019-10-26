package com.nny.Demo.DataStructureLearn;

/**
 * 3.20.2019
 * liting
 * 以单链表存储方式的栈类
 */
public class LinkStackLearn {
    private SingleListNode top; //将栈顶元素放在单链表的首部成为首结点
    private int length; //栈的实际容量

    public SingleListNode getTop() {
        return top;
    }

    public void setTop(SingleListNode top) {
        this.top = top;
    }
    //入栈
    public void push(Object o){
        SingleListNode node = new SingleListNode(o,null);
        node.setNextNode(top);
        top = node;
        length++;
    }
    //出栈
    public Object pop(){
        if(length != 0){
            SingleListNode node = top;
            top = top.getNextNode();
            return node;
        }else{
            return null;
        }
    }
    //取栈顶元素
    public Object peek(){
        if(top != null){
            return top.getData();
        }else{
            return null;
        }
    }
}
