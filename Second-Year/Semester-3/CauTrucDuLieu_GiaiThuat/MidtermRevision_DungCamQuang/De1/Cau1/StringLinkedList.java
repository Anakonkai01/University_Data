
public class StringLinkedList implements LinkedListInterface {
    private Node head;

    public StringLinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    // a
    public boolean addFirst(String text) {
        // CODE HERE BOYYYY
        Node curr = head;
        while (curr != null) {
            if (curr.getValue().equals(text)) {
                return false;
            }
            curr = curr.getNext();
        }

        head = new Node(text, head);
        return true;
    }

    // b
    public boolean updateString(String text, int pos) {
        // CODE HERERRERER

        if (head == null) {
            return false;
        }
        if (head.getNext() == null || pos == 0) {
            head = new Node(text, null);
            return true;
        }

        int index = 0;
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            if (index == pos) {
                Node newNode = new Node(text, null);
                newNode.setNext(curr.getNext());
                prev.setNext(newNode);
                return true;
            }
            prev = curr;
            curr = curr.getNext();
            index++;
        }
        return false;
    }

    // c
    public int countKey(String key, int begin, int end) {
        // code hrererererere
        if (head == null) {
            return -1;
        }

        int i = 0;
        int count = 0;
        Node curr = head;
        while (curr != null) {
            if (i >= begin && i <= end && curr.getValue().equals(key)) {
                count++;
            }
            curr = curr.getNext();
            i++;
        }
        return count;
    }

    // test for problem c
    public void add(String text) {
        head = new Node(text, head);
    }

}