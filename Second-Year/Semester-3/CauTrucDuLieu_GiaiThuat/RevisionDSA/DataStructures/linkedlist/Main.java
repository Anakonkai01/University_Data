
package LinkedList;

import java.util.Random;

public class Main {
    public static void print(Node head) {
        Node dummy = head;
        while (dummy != null) {
            System.out.print(dummy.getData() + "  ");
            dummy = dummy.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList list1 = new SinglyLinkedList();
        // Random random = new Random();
        // int i = 0;

        list1.addLast(2);
        list1.addLast(4);
        list1.addLast(6);
        list1.addLast(8);
        Node head = list1.reOrderList(list1.getHead());
        print(head);
    }
}
