
package stack;

import linkedlist.*;

public class MyStack {

    private SinglyLinkedList list;
    private int size;

    public MyStack() {
        list = new SinglyLinkedList();
        size = 0;
    }

    public void push(int data) {
        list.addFirst(data);
        size++;
    }

    public int peek() {
        if (list.isEmpty()) {
            System.out.println("Het con me no roi pop cc");
            return -1;
        }
        return list.getHead().getData();
    }

    public int pop() {
        if (list.isEmpty()) {
            System.out.println("Het con me no roi pop cc");
            return -1;
        }
        size--;
        return list.removeFirst().getData();
    }

    public void clear() {
        size = 0;
        list = new SinglyLinkedList();
    }

    public int size() {
        return size;
    }

    public void print() {
        list.print();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}