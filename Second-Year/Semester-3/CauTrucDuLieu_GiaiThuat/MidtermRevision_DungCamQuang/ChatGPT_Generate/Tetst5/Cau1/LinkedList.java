package ChatGPT_Generate.Tetst5.Cau1;

public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    public void removeKthfromEnd(int k) {
        if (head == null) {
            return;
        }

        Node right = head;
        while (k > 0) {
            right = right.getNext();
            k--;
        }

        Node prev = null;
        Node left = head;
        while (right != null) {
            prev = left;
            left = left.getNext();
            right = right.getNext();
        }
        prev.setNext(left.getNext());
    }

    public void addFirst(String text) {
        head = new Node(text, head);
    }

    public String findMiddle() {
        Node slow = head;
        Node fast = slow.getNext();
        // fast == null la le
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        System.out.println(fast == null);
        return slow.getValue();
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + "->");
            curr = curr.getNext();
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");

        list.print();
        // list.removeKthfromEnd(3);
        System.out.println(list.findMiddle());
        list.print();
    }
}
