
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
        // System.out.println(list.addFirst("nhan"));
        // System.out.println(list.addFirst("thai"));
        // System.out.println(list.addFirst("Khang"));
        // System.out.println();
        // System.out.println(list.updateString("Vu", 0));
        // print(list.getHead());

        // list.add("bbb");
        list.add("aaa");
        // list.add("ccc");
        // list.add("aaa");
        // list.add("aaa");
        // list.add("ddd");
        // list.add("bbb");
        // print(list.getHead());
        System.out.println(list.countKey("aab", 0, 0));
    }
}
