import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

abstract public class SinglyLinkedList {
    private Node head;
    // addFirst, addLast, removeFirst, removeLast, removeNode, print, findNode
    public SinglyLinkedList(){
        this.head = null;
    }

    public void addFirst(int data){
        if(head == null) head = new Node(data);
        else{
            // Node newNode = new Node(data);
            // newNode.setNext(head);
            // head = newNode;
            head = new Node(data, head); // duoc
        }
    }

    public void addAfter(int data, Node refNode){
        Node dummyNode = head;
        while(dummyNode != null){
            if(dummyNode.equals(refNode)){
                Node newNode = new Node(data);
                newNode.setNext(dummyNode.getNext());
                dummyNode.setNext(newNode);
                System.out.println("okeee");
                return;
            }
            dummyNode = dummyNode.getNext();
        }
        System.out.println("NOOOOOOOOOo");
        return;
    }

    public void addLast(int data){
        if(head == null) head = new Node(data);
        else{
            Node dummyNode = head;
            while(dummyNode.getNext() != null){
                dummyNode = dummyNode.getNext();
            }
            dummyNode.setNext(new Node(data));
        }
    }

    public void print(){
        Node dummy = head;
        while(dummy != null){
            System.out.print(dummy.getData()+ "  ");
            dummy = dummy.getNext();
        }
        System.out.println();
    }
        
    public Node removeFirst(){
        if(isEmpty()){
            System.out.println("NOOO");
            return null;
        }

        Node tmp = head;
        head = head.getNext();
        return tmp;
    }

    public Node removeLast(){
        if(isEmpty()){
            System.out.println("NOOO");
            return null;
        }
        if(head.getNext() == null) {
            head = null;
            return null;
        }
        Node dummyNode = head;
        while(dummyNode.getNext().getNext() != null) dummyNode = dummyNode.getNext();
        Node tmp = dummyNode;
        dummyNode.setNext(null);
        return tmp;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Node removeCurrNode(Node delNode){
        if(isEmpty()) return null; 

        if(head.equals(delNode)){
            removeFirst();
            return delNode;
        }

        Node curr = head;
        Node prev = null;

        while(curr != null){
            if(curr.equals(delNode)){
                Node tmp = curr;
                prev.setNext(curr.getNext());
                return tmp;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    public Node getHead(){
        return this.head;
    }

    public void addBefore(Node node,int data){
        if(isEmpty() || head.equals(node)){
            addFirst(data);
            return;
        }

        Node curr = head;
        Node prev = null;
        while(curr != null){
            if(curr.equals(node)){
                Node newNode = new Node(data);
                newNode.setNext(curr);
                prev.setNext(newNode);
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }
    }
    

    abstract public Node reverseLinkedList();
    
    public static void main(String[] args) {
        SinglyLinkedList list = new ExtendMethod();


        list.addFirst(1);
        // list.addFirst(2);
        // list.addFirst(3);
        list.removeLast();
        list.print();


        
    }
}
