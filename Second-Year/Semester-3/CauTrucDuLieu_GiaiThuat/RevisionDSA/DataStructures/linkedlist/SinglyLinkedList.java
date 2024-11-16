package LinkedList;

public class SinglyLinkedList {
    private Node head;

    // addFirst, addLast, removeFirst, removeLast, removeNode, print, findNode,
    // isEmpty, clear, getSize, addBefore, addAfter, removeBefore, removeAfter
    public SinglyLinkedList() {
        this.head = null;
    }

    public void addFirst(int data) {
        if (head == null)
            head = new Node(data);
        else {
            // Node newNode = new Node(data);
            // newNode.setNext(head);
            // head = newNode;
            head = new Node(data, head); // duoc
        }
    }

    public void addAfter(int data, Node refNode) {
        Node dummyNode = head;
        while (dummyNode != null) {
            if (dummyNode.equals(refNode)) {
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

    public void addLast(int data) {
        if (head == null)
            head = new Node(data);
        else {
            Node dummyNode = head;
            while (dummyNode.getNext() != null) {
                dummyNode = dummyNode.getNext();
            }
            dummyNode.setNext(new Node(data));
        }
    }

    public void print() {
        Node dummy = head;
        while (dummy != null) {
            System.out.print(dummy.getData() + "  ");
            dummy = dummy.getNext();
        }
        System.out.println();
    }

    public Node removeFirst() {
        if (isEmpty()) {
            System.out.println("NOOO");
            return null;
        }

        Node tmp = head;
        head = head.getNext();
        return tmp;
    }

    public Node removeLast() {
        if (isEmpty()) {
            System.out.println("NOOO");
            return null;
        }
        if (head.getNext() == null) {
            head = null;
            return null;
        }
        Node dummyNode = head;
        while (dummyNode.getNext().getNext() != null)
            dummyNode = dummyNode.getNext();
        Node tmp = dummyNode;
        dummyNode.setNext(null);
        return tmp;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node removeCurrNode(Node delNode) {
        if (isEmpty())
            return null;

        if (head.equals(delNode)) {
            removeFirst();
            return delNode;
        }

        Node curr = head;
        Node prev = null;

        while (curr != null) {
            if (curr.equals(delNode)) {
                Node tmp = curr;
                prev.setNext(curr.getNext());
                return tmp;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    public Node getHead() {
        return this.head;
    }

    public void addBefore(Node node, int data) {
        if (isEmpty() || head.equals(node)) {
            addFirst(data);
            return;
        }

        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr.equals(node)) {
                Node newNode = new Node(data);
                newNode.setNext(curr);
                prev.setNext(newNode);
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }
    }

    public void reverse_Linked_List() {
        Node curr = head;
        Node next = null;
        Node prev = null;

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public Node reverse_Linked_List(Node head) {
        Node curr = head;
        Node next = null;
        Node prev = null;

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node mergeTwoLists(Node list1, Node list2) {
        Node dummyNode = new Node(0);
        Node returnNode = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.getData() < list2.getData()) {
                dummyNode.setNext(list1);
                dummyNode = dummyNode.getNext();
                list1 = list1.getNext();
            } else {
                dummyNode.setNext(list2);
                dummyNode = dummyNode.getNext();
                list2 = list2.getNext();
            }
        }

        if (list1 != null) {
            dummyNode.setNext(list1);
        }

        if (list2 != null) {
            dummyNode.setNext(list2);
        }

        return returnNode.getNext();
    }

    public void sortLinkedList() {
        Node curr = head;
        while (curr.getNext() != null) {
            Node min = curr;
            Node dummyNode = curr.getNext();

            while (dummyNode != null) {
                if (dummyNode.getData() < min.getData())
                    min = dummyNode;
                dummyNode = dummyNode.getNext();
            }

            // swap
            int temp = min.getData();
            min.setData(curr.getData());
            curr.setData(temp);

            curr = curr.getNext();
        }
    }

    public void reorderList() {
        // dung ki thuat fast and slow pointer
        Node slow = head;
        Node fast = head;

        // (fast == null) ? "chan": "le"
        // slow = middle of the list
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // reverse the list after the slow pointer

        Node list2 = reverse_Linked_List(slow.getNext()); // phan phia sau se dao nguoc
        slow.setNext(null); // ngat doi ra 2 phan
        Node list1 = head; // phan dau tien

        while (list2 != null) {
            Node next1 = list1.getNext(); // du cho cho thang list1
            Node next2 = list2.getNext(); // du cho cho thang list2

            list1.setNext(list2);
            list2.setNext(next1);

            list1 = next1;
            list2 = next2;
        }
    }

    public Node reOrderList(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        Node list2 = slow.getNext();
        list2 = reverse_Linked_List(list2);
        slow.setNext(null);
        Node list1 = head;

        while (list2 != null) {
            Node next1 = list1.getNext();
            Node next2 = list2.getNext();

            list1.setNext(list2);
            list2.setNext(next1);

            list1 = next1;
            list2 = next2;
        }

        return head;
    }

    public int remove(int data) {
        if (head == null) {
            return -1;
        }

        // Node prevGiuCho = null;
        Node giucho = null;
        Node ditim = head;
        int indexGIuCho = -1;
        int index = 0;
        // find , remove
        // if(head == null) {

        // }
        // if( head.getNext() == null){

        // }

        while (ditim != null) {
            if (ditim.getData() == data) {
                giucho = ditim;
                indexGIuCho = index;
            }
            index++;
            ditim = ditim.getNext();
        }

        // remove
        ditim = head;
        if (ditim == giucho) {
            head = head.getNext();
        } else if (giucho != null) {
            while (ditim.getNext() != giucho) {
                ditim = ditim.getNext();
            }
            ditim.setNext(giucho.getNext());
        }

        return indexGIuCho;
    }

    private int size = 0;

    public int[] stringLength() {
        int[] res = new int[size];
        Node curr = head;
        int i = 0;
        while (curr != null) {
            res[i] = curr.getData().length();
            curr = curr.getNext();
            i++;
        }
        return res;
    }

    // public int remove(int data){

    // }
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        // list.addFirst(1);
        // list.addLast(2);
        // list.addLast(3);
        // list.addLast(1); // 3
        // list.addLast(2);
        // list.addLast(3);

        System.out.println(list.remove(2));
        list.print();
    }

}
