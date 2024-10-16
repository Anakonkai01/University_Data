import java.util.Arrays;

public class StringLinkedList {
    private Node head;
    private int size;

    public StringLinkedList() {
        this.head = null;
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    int strCompare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int len = Math.min(len1, len2);

        for (int i = 0; i < len; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }

        return len1 - len2;
    }

    public void addFirst(String text) {
        head = new Node(text, head);
    }

    public void addSorted(String text) {
        size++;
        if (head == null) {
            head = new Node(text, head);
            return;
        }

        Node curr = head;
        Node prev = null;
        while (curr != null) {

            if (curr.getValue().compareTo(text) > 0) {
                if (prev == null) {
                    head = new Node(text, head);
                    return;
                }
                Node newNode = new Node(text, prev.getNext());
                prev.setNext(newNode);
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }

        Node newNode = new Node(text, prev.getNext());
        prev.setNext(newNode);
    }

    public void addLast(String text) {
        if (head == null) {
            addFirst(text);
            return;
        }
        Node curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node(text, null));
    }

    // cau 2
    public void removeDuplicates() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node curr = head;
        while (curr != null && curr.getNext() != null) {
            Node next = curr.getNext();
            Node prev = curr;
            while (next != null) {
                if (curr.getValue().equals(next.getValue())) {
                    prev.setNext(next.getNext());
                } else { // cap nhap khi ko con bi trung
                    prev = next;
                }
                next = next.getNext();
            }
            curr = curr.getNext();
        }

    }

    // cau 3

    public boolean isPalindrome() {
        if (head == null || head.getNext() == null) {
            return true;
        }
        Node h1 = head;
        Node h2 = copy_reverseLinkedList(head);
        print(h1);
        print(h2);
        while (h1 != null) {
            if (!(h1.getValue().equals(h2.getValue()))) {
                return false;
            }
            h1 = h1.getNext();
            h2 = h2.getNext();
        }
        return true;
    }

    public Node copy_reverseLinkedList(Node head) {
        StringLinkedList list = new StringLinkedList();
        Node curr = head;
        while (curr != null) {
            list.addFirst(curr.getValue());
            curr = curr.getNext();
        }
        return list.getHead();
    }

    // public boolean isPalindrome(){
    // Node slow = head;
    // Node fast = head;
    // // fast null thi le
    // while(fast != null && fast.getNext() != null){
    // slow = slow.getNext();
    // fast = fast.getNext().getNext();
    // }

    // }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public void print(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("b");
        list.addLast("a");

        System.out.println(list.isPalindrome());
    }

}
