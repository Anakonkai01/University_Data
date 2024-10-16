package ChatGPT_Generate.Test2.Cau2;

import java.util.Stack;

public class Cau2 {
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (char c : expression.toCharArray()) {

            switch (c) {
                case '+':
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                    break;
                case '-':
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                case '*':
                    stack.push(stack.pop() * stack.pop());
                    break;
                case '/':
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;
                default:
                    stack.push(Integer.parseInt(String.valueOf(c)));
                    break;
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        String str = "231*+9-";
        System.out.println(evaluatePostfix(str));
    }
}
