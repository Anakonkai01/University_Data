
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
        // System.out.println(list.addFirst("thaitt"));
        // System.out.println(list.addFirst("Khang"));
        System.out.println();

        // list.add("bbb");
        // list.add("ccc");
        // list.add("aaa");
        // list.add("aaa");
        // list.add("ddd");
        // list.add("bbb");
        // print(list.getHead());

        System.out.println(list.addAt("chao emi", 0));
        print(list.getHead());
    }
}
