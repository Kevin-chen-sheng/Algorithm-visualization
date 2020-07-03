package com.itheima.demo;

public class LinkedListTest<T> {
    private class Node{
        public T t;
        public Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
        public Node(T t){
            this(t,null);
        }
        public Node(int i, Object next){
            this(null,null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "t=" + t +
                    '}';
        }
    }
    //虚拟头结点
    private Node dummyHead;
    int size;
    public LinkedListTest(){
        dummyHead=new Node(null,null);
        size=0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void add(int index,T t){
        if(index<0||index>size){
            throw new IllegalArgumentException("illegal");
        }
        Node prev=dummyHead;
        for (int i = 0; i < index; i++) {
            prev=prev.next;
        }
        Node node = new Node(t);
        node.next=prev.next;
        prev.next=node;
//        prev.next=new Node(t,prev.next);
        size++;
    }
    public void addFirst(T t){
        add(0,t);
    }
    //反转,用两个指针
    public void reverseNode(){
        Node pHead= dummyHead.next;
        if(pHead==null){
            return;
        }
        Node prev=null;
        while (pHead!=null){
            //保存next
            Node temp=pHead.next;
            //反转
            pHead.next=prev;
            //移动prev
            prev=pHead;
            //移动phead
            pHead=temp;

        }
        dummyHead.next=prev;
//        Node prev=dummyHead;
//        Node head = prev.next;
//        Node now;
//        Node newNode=null;
//        while (head!=null){
//            now=head;
//            head=head.next;
//            now.next=newNode;
//            newNode=now;
//            if(head==null){
////                head=now;
//                break;
//            }
//        }

    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node cur=dummyHead.next;
        while (cur!=null){
            builder.append(cur+"->");
            cur=cur.next;
        }
        builder.append("null");
        return builder.toString();
    }

    public static void main(String[] args) {

        LinkedListTest<Object> test = new LinkedListTest<>();
        for (int i = 0; i < 5; i++) {
            test.addFirst(i);
            System.out.println(test);
        }
        test.reverseNode();
        System.out.println(test);

    }
}
