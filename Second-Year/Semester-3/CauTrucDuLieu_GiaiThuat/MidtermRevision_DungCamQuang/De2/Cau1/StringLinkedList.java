
public class StringLinkedList implements LinkedListInterface {
    private Node head;
    private int size = 0;

    public StringLinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    // a
    public void add(String text) {
        // CODE HERE BOYYYY
        String tmp = text.toLowerCase();
        if (tmp.charAt(0) == 'u' || tmp.charAt(0) == 'e' || tmp.charAt(0) == 'o' || tmp.charAt(0) == 'a'
                || tmp.charAt(0) == 'i') {
            addFirst(text);
        } else {
            addLast(text);
        }
        size++;
    }

    private void addFirst(String text) {
        head = new Node(text, head);
    }

    private void addLast(String text) {
        if (head == null) {
            addFirst(text);
        } else {
            Node curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            Node newNode = new Node(text, null);
            curr.setNext(newNode);
        }
    }

    // b
    public int remove(String text) {
        // code here

        if (head == null) {
            return -1;
        }
        if (head.getNext() == null && head.getValue().equals(text)) {
            head = null;
            return 0;
        }

        int finder_index = 0;
        int spot_index = -1;
        Node finder = head;
        Node spot = null;
        Node prevFinder = null;
        Node prevSpot = null;

        while (finder != null) {
            if (finder.getValue().equals(text)) {
                spot_index = finder_index;
                prevSpot = prevFinder;
                spot = finder;
            }
            finder_index++;
            prevFinder = finder;
            finder = finder.getNext();
        }

        if (head.getValue().equals(text) && spot != null) {
            head = head.getNext();
        } else {
            prevSpot.setNext(spot.getNext());
        }
        return spot_index;
    }

    // c
    public int[] stringLength() {
        // code hrererererere
        int[] res = new int[size];

        Node curr = head;
        int i = 0;
        while (curr != null) {
            res[0] = curr.getValue().length();
            i++;
            curr = curr.getNext();
        }
        return res;
    }

}