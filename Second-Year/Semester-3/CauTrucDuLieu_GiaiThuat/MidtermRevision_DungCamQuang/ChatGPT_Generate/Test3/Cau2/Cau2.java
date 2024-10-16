package ChatGPT_Generate.Test3.Cau2;

import java.util.Stack;

public class Cau2 {
    public static boolean validateExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        String open = "({[";
        String operant = "+-*/";
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i)) || operant.contains(String.valueOf(expression.charAt(i)))
                    || expression.charAt(i) == ' ') {
                continue;
            }
            if (open.contains(String.valueOf(expression.charAt(i)))) {
                stack.push(expression.charAt(i));
            } else if (!stack.isEmpty() && expression.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (!stack.isEmpty() && expression.charAt(i) == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (!stack.isEmpty() && expression.charAt(i) == '}' && stack.peek() == '{') {
                stack.pop();
            } else {
                return false;
            } // check paranthesis

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "{(2 + 3])}";
        System.out.println(validateExpression(str));
    }
}
