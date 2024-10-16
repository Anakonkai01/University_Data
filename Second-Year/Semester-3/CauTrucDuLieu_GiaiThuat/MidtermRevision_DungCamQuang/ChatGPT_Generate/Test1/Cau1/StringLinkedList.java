
public class StringLinkedList implements LinkedListInterface {
    private Node head;

    public StringLinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    boolean addFirst(String text) {
        head = new Node(text, head);
        return true;
    }

    boolean addLast(String text) {
        if (head == null) {
            addFirst(text);
            return true;
        }
        Node curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node(text, null));
        return true;
    }

    boolean checkVowels(String str) {
        String vowels = "ueoaiUEOAI";
        if (vowels.indexOf(str.charAt(str.length() - 1)) != -1) {
            return true;
        }
        return false;
    }

    // a
    public boolean add(String text) {
        if (checkVowels(text)) {
            addFirst(text);
        } else {
            addLast(text);
        }
        return true;
    }

    // b
    public boolean remove(int pos) {
        if (head == null) {
            return false;
        }
        if (pos == 0) {
            head = head.getNext();
            return true;
        }

        Node curr = head;
        Node prev = null;
        int index = 0;
        while (curr != null) {
            if (index == pos) {
                prev.setNext(curr.getNext());
                return true;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return false;
    }

    // c
    public int length() {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    public static void main(String[] args) {
        // generate test for me

    }
}
