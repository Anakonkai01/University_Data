
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
        if (pos == 0) {
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

    void addAfter(String text, Node curr) {
        Node newNode = new Node(text, null);
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
    }

    void addBefore(String text, Node prev) {
        Node newNode = new Node(text, null);
        newNode.setNext(prev.getNext());
        prev.setNext(newNode);
    }

    boolean checkVowel(char data) {
        String vowels = "ueoaiUEOAI";
        if (vowels.indexOf(data) != -1) {
            return true;
        }
        return false;
    }

    public boolean addAt(String text, int pos) {
        if (head == null) {
            return false;
        }
        if (pos == 0) {
            if (checkVowel(head.getValue().charAt(head.getValue().length() - 1))) {
                head.setNext(new Node(text, null));
            } else {
                head = new Node(text, head);
            }
            return true;
        }

        int i = 0;
        Node curr = head;
        Node prev = null; // sai cho addBefore
        while (curr != null) {
            if (i == pos) {
                if (checkVowel(curr.getValue().charAt(curr.getValue().length() - 1))) {
                    addAfter(text, curr);
                } else {
                    addBefore(text, prev);
                }
                return true;
            }
            prev = curr;
            curr = curr.getNext();
            i++;
        }
        return false;
    }

}
