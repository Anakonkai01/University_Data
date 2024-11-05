package Lab8.Exercise4;

public class Exercise4 {
    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue(100);
        pq.enQueue("Alex",1);
        pq.enQueue("Bob",2);
        pq.enQueue("David",6);
        pq.enQueue("Susan", 1);
        System.out.println(pq.deQueue());
        pq.enQueue("Mike", 5);
        pq.enQueue("Kevin", 4);
        System.out.println(pq.deQueue());
        System.out.println(pq.deQueue());
        pq.enQueue("Helen", 0);
        pq.enQueue("Paul", 8);
        pq.enQueue("Iris", 7);
        System.out.println(pq.deQueue());
    }
}
