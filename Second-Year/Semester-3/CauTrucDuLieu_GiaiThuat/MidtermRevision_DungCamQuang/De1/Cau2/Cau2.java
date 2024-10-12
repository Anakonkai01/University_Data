import java.util.Stack;

public class Cau2 {

    public static String reverseVowels(String str) {
        // code hererererre
        Stack<Character> stack = new Stack<>();
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'u' || str.charAt(i) == 'e' || str.charAt(i) == 'o' || str.charAt(i) == 'a'
                    || str.charAt(i) == 'i') {
                stack.push(str.charAt(i));
            }
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'u' || str.charAt(i) == 'e' || str.charAt(i) == 'o' || str.charAt(i) == 'a'
                    || str.charAt(i) == 'i') {
                res += stack.pop();
            } else {
                res += str.charAt(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String test = "international";
        System.out.println(reverseVowels(test));
    }

}
