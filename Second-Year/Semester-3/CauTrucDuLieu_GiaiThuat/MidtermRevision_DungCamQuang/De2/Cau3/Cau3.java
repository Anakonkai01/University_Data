
public class Cau3 {
    public static int productOfKeys(int[] a, int k, int key) {
        // code here
        if (k < 0) {
            return 1;
        }
        if (a[k] == key)
            return key * productOfKeys(a, k - 1, key);
        return productOfKeys(a, k - 1, key);
    }

    public static void main(String[] args) {
        int[] a = {};
        System.out.println(productOfKeys(a, a.length - 1, 3));
    }
}
