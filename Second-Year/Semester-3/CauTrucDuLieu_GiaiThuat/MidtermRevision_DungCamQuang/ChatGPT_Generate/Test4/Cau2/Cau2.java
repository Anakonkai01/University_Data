package ChatGPT_Generate.Test4.Cau2;

import java.util.Stack;

public class Cau2 {
    public static String reverseWords(String sentence) {
        Stack<String> stack = new Stack<>();
        String[] arr = sentence.split("//s+");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }
}
