package ChatGPT_Generate.Test4.Cau3;

public class Cau3 {
    public static int countWays(int n) {
        // dynamic program
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        return countWays(n - 1) + countWays(n - 2);
    }
}

// k =1
// k = 6
// a b c d

// d a b c
// c d a b
// k == 4
// d a b c
// c d a b
// b c d a
// a b c d
