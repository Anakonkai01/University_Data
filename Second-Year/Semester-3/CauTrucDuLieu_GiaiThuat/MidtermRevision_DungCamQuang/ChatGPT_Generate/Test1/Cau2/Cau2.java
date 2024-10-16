import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Cau2 {

    public static void simulateQueue(String[] actions) {
        Queue<String> queue = new LinkedList<>();
        for (String str : actions) {
            String[] partStr = str.split("//s+");
            if (partStr[0].equals("enqueue")) {
                queue.offer(partStr[1]);
            } else {
                queue.poll();
            }
        }
        System.out.println(queue);
    }

}
