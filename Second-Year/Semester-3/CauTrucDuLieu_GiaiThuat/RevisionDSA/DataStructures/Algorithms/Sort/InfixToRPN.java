import java.util.*;

public class InfixToRPN {

    // Method to return precedence of operators
    private static int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    // Method to convert infix to RPN (postfix)
    public static List<String> infixToRPN(String expression) {
        Stack<Character> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is a digit, append it to the current number
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                // If there's a number being built, add it to the result
                if (number.length() > 0) {
                    result.add(number.toString());
                    number = new StringBuilder();
                }

                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result.add(stack.pop().toString());
                    }
                    stack.pop(); // Remove '('
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                        result.add(stack.pop().toString());
                    }
                    stack.push(c);
                }
            }
        }

        // If there's a number left to add
        if (number.length() > 0) {
            result.add(number.toString());
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            result.add(stack.pop().toString());
        }

        return result;
    }

    public static void main(String[] args) {
        String expression = "(1+2)*3"; // Input expression

        List<String> rpn = infixToRPN(expression);
        System.out.println("Reverse Polish Notation (RPN): " + rpn);
    }
}
