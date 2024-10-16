package ChatGPT_Generate.Test4.Cau1;

public class LinkedList {
    private Node head;
    private int size = 0;

    // cau 1
    public void insertAtMiddle(String text) {
        if (head == null) {
            head = new Node(text, head);
            size++;
            return;
        }

        Node curr = head;
        int index = 0;
        int middleIndex = (size % 2 == 0) ? size / 2 : size / 2 + 1;
        while (index < middleIndex - 1) {
            curr = curr.getNext();
            index++;
        }

        Node newNode = new Node(text, curr.getNext());
        curr.setNext(newNode);
        size++;
    }

    public Node removeLast() {
        if (head == null) {
            return null;
        }

        Node curr = head;
        while (curr.getNext().getNext() != null) {
            curr = curr.getNext();
        }
        Node lastNode = curr.getNext();
        curr.setNext(null);
        size--;
        return lastNode;
    }

    public void addFirst(Node curr) {
        curr.setNext(head);
        head = curr;
    }

    // cau 2
    public void rotateRight(int k) {
        //
        while (k > 0) {
            Node lastNode = removeLast();
            addFirst(lastNode);
            k--;
        }

    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public boolean isSorted() {
        if (head == null) {
            return true;
        }

        Node curr = head;
        Node next = curr.getNext();
        while (next != null) {
            if (next.getValue().compareTo(curr.getValue()) < 0) {
                return false;
            }
            curr = next;
            next = next.getNext();
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertAtMiddle("1");
        list.insertAtMiddle("2");
        // list.insertAtMiddle("3");
        // list.insertAtMiddle("4");
        // list.insertAtMiddle("5");
        list.rotateRight(2);
        list.print();
        System.out.println(list.isSorted());
    }
}
