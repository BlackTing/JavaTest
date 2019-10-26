package com.nny.Demo.DataStructureLearn;

/**
 * date 3.19.2019
 * writer liting
 * content 单链表存储的队列
 */
public class QueueLearn {
    private int length; //队列的实际容量大小
    private SingleListNode front;//队首节点
    private SingleListNode rear;//队尾节点

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public SingleListNode getFront() {
        return front;
    }

    public void setFront(SingleListNode front) {
        this.front = front;
    }

    public SingleListNode getRear() {
        return rear;
    }

    public void setRear(SingleListNode rear) {
        this.rear = rear;
    }

    //入队
    public void enqueue(Object o){
        SingleListNode node = new SingleListNode(o,null);
        if(length == 0){
            front = node;
            rear = node;
        }else{
            rear.setNextNode(node);
            rear = node;
        }
        length++;
    }
    //出队
    public void dequeue()throws QueueEmptyTestException{
        if(length<1){
            throw new QueueEmptyTestException("错误：队列为空");
        }
        front = front.getNextNode();
    }
    //输出队列的全部元素
    public void print(){
        SingleListNode node = front;
        while(node != null) {
            System.out.print(node.getData());
            node = node.getNextNode();
        }
    }
}


