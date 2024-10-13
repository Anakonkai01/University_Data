import java.util.Stack;

public class Cau2 {

    public static String openWebsite(String[] webURL, int[] actions) {
        // code hererererre
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < webURL.length; i++) {
            if (actions[i] < 0 && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(webURL[i]);
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] web = { "tdtu", "stdportal", "google" };
        int[] actions = { 1, 2, 0, -1, 0, -1, -1 };
        System.out.println(openWebsite(web, actions));
    }
}
