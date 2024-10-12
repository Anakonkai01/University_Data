
public class Cau3 {
    public static int productOfDivideBy3Nums(int[] a, int k) {
        // codeeeee ehrehrerehrhehrhe
        if (k < 0) {
            return 1;
        }
        if (a[k] % 3 == 0) {
            return a[k] * productOfDivideBy3Nums(a, k - 1);
        }
        return productOfDivideBy3Nums(a, k - 1);
    }

    public static void main(String[] args) {
        int[] a = {};
        System.out.println(productOfDivideBy3Nums(a, a.length - 1));
    }
}
