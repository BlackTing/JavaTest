package com.nny.Demo.DataStructureLearn;

/**
 * date 3.19.2019
 * writer liting
 * content 单链表组成节点类
 */
public class SingleListNode {
    private Object data;//节点的数据部分
    private SingleListNode nextNode; //本节点的后一个节点

    public SingleListNode(){

    }
    public SingleListNode(Object data,SingleListNode nextNode){
        this.data = data;
        this.nextNode = nextNode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SingleListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(SingleListNode nextNode) {
        this.nextNode = nextNode;
    }
}
