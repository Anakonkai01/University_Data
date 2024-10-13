
public class Test {
    public static void print(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getValue() + ' ');
            curr = curr.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StringLinkedList list = new StringLinkedList();
        list.add("Abc");
        list.add("acb");
        list.add("bce");
        list.add("Icon");
        print(list.getHead());

        System.out.println(list.remove("Abc"));
        print(list.getHead());
    }
}
