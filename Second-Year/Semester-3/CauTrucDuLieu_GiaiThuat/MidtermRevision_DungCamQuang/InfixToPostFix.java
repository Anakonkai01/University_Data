import java.util.Stack;

public class InfixToPostFix {

    public static int getPrecendence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static String infixToPostFix(String expression) {
        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                res.append(expression.charAt(i));
            } else if (expression.charAt(i) == '(') {
                stack.push(expression.charAt(i));
            } else if (expression.charAt(i) == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    res.append(stack.pop());
                }
                stack.pop(); // xoa thang (
            } else {
                while (!stack.isEmpty() && getPrecendence(expression.charAt(i)) < getPrecendence(stack.peek())) {
                    res.append(stack.pop());
                }
                stack.push(expression.charAt(i));
            }

        }

        // pop toan bo toan tu trong stack vao res
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }

    public static void main(String[] args) {
        String str = "(1+2)*3";
        System.out.println(infixToPostFix(str));
    }
}
