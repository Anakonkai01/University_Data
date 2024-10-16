package ChatGPT_Generate.Test3.Cau1;

public class LinkedList {
    private Node head;

    public Node getHead() {
        return head;
    }

    // cau 1
    public void insertSortedList(String text) {
        if (head == null) {
            head = new Node(text, head);
            return;
        }

        int text_length = text.length();

        Node curr = head;
        Node prev = null;

        while (curr != null) {
            int curr_length = curr.getValue().length();
            if (curr_length > text_length) {
                if (prev == null) {
                    // addfirst
                    head = new Node(text, head);
                } else {
                    Node newNode = new Node(text, prev.getNext()); // add middle
                    prev.setNext(newNode);
                }
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }
        // add last
        Node newNode = new Node(text, prev.getNext()); // add middle
        prev.setNext(newNode);
    }

    boolean check_prefix(String str1, String str2) {
        if (str2.length() > str1.length())
            return false;
        for (int i = 0; i < str2.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // cau 2
    public void removeByPrefix(String prefix) {
        if (head == null) {
            return;
        }

        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (check_prefix(curr.getValue(), prefix)) {
                if (prev == null) { // neu ma` no la deu tien 1 12 123 1234
                    head = head.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }
            } else {
                prev = curr; //
            }
            curr = curr.getNext();
        }

    }

    // cau 3
    public int countOccurrences(String text, Node head) {
        if (head == null) {
            return 0;
        }

        if (head.getValue().equals(text)) {
            return 1 + countOccurrences(text, head.getNext());
        }
        return countOccurrences(text, head.getNext());
    }

    public static void main(String[] args) {// -
        LinkedList list = new LinkedList();// -
        list.insertSortedList("Nguyen Tran Hoang Nhan");// -
        list.insertSortedList("Thanh Nhut Khang");// -
        list.insertSortedList("Nguyen Thanh");// -
        list.print();
        System.out.println();
        // list.removeByPrefix("Nguyen");// -
        list.print();//

        System.out.println(list.countOccurrences("Nguyen Thanh", list.getHead()));
    }

    public void print() {
        Node curr = head;// +
        while (curr != null) {// +
            System.out.println(curr.getValue());// +
            curr = curr.getNext();// +
        } // +
    }

}
