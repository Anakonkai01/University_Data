import java.util.Stack;

public class LuyenDe {
    public static String openWebsite(String[] webURL, int[] actions) {
        Stack<String> stack = new Stack<>();
        for (int i : actions) {
            if (i == -1 && !stack.isEmpty()) {
                stack.pop();
            } else if (i >= 0) {
                stack.push(webURL[i]);
            }
        }
        return stack.peek();
    }

    public static int productOfKey(int[] a, int k, int key) {
        if (k < 0)
            return 1;
        if (a[k] == key)
            return a[k] * productOfKey(a, k - 1, key);
        return productOfKey(a, k - 1, key);
    }

    public static void main(String[] args) {
        String[] str = { "tdtu.edu.vn", "google.com", "stdportal.edu.vn" };
        int[] actions = { -2 };

        int[] a = {};
        System.out.println(a.length);

        // System.out.println(openWebsite(str, actions)); // stdportal.edu.vn
    }
}