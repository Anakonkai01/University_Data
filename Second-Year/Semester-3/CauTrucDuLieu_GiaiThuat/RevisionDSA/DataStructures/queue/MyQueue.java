// package Queue;


package queue;
import linkedlist.*;


public class MyQueue {
    // push()
    // peek()
    // pop()
    // clear()
    // size()
    // print()
    // isEmpty()
    
    private SinglyLinkedList list;
    private int size;

    public MyQueue(){
        list = new SinglyLinkedList();
        size = 0;
    }

    public void enQueue(int data){
        list.addLast(data);    
        size++;
    }

    public int deQueue(){
        if(isEmpty()){
            System.out.println("het me no roi");
            return -1;
        }
        size--;
        return list.removeFirst().getData(); 
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("het me no roi");
            return -1;
        }
        return list.getHead().getData();
    }

    public int size(){
        return size;
    }

    public void print(){
        list.print();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        size = 0;
        list = new SinglyLinkedList();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        queue.print();

        queue.deQueue();
        queue.print();

        System.out.println(queue.peek());

        System.out.println(queue.size());
        
        queue.clear();
        queue.enQueue(122);
        queue.print();
    }
}
