package RevisionDSA.DataStructures.Algorithms;

public class MyFuckingInfixToRPN {

  public static int getPrecedences(char c){
    switch(c){
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

  public static List<String> infixToRPN(String expresion){
    Stack<Character> stack = new Stack<>();
    List<String> result = new ArrayList<>();

  }
  public static void main(String[] args){

    

  }
}
