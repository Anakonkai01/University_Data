package ChatGPT_Generate.Test4.Cau1;

public class Node {
    private String value;
    private Node next;

    public Node(String data, Node next) {
        this.value = data;
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
