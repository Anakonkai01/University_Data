package Recursion;

public class TailRecursion {
    // 1
    public static int factorial(int n, int acc) {
        if (n <= 0)
            return acc;
        else
            return factorial(n - 1, acc * n);
    }

    // 2
    public static int sumN(int n, int acc) {
        if (n <= 0)
            return acc;
        return sumN(n - 1, acc + n);
    }

    // 3
    public static int countNumber(int n, int acc) {
        if (n <= 0)
            return acc;
        return countNumber(n / 10, acc + 1);
    }

    // 4
    public static int sumElement(int n, int acc) {
        if (n <= 0)
            return acc;
        return sumElement(n / 10, acc + n % 10);
    }

    // 5
    public static int fibonnaci(int n, int a, int b) {
        if (n == 0)
            return a;
        if (n == 1)
            return b;
        return fibonnaci(n - 1, a) + fibonnaci(n - 2, a + b);
    }

    public static void main(String[] args) {
        int n = 12345;
        System.out.println(sumElement(n, 0));
    }
}
