import java.util.Arrays;
import java.util.Random;

public class Dequy {
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static String reverseString(String str, String acc) {
        if (str.isEmpty()) {
            return acc;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static boolean binarySearch(int[] nums, int l, int r, int target) {
        if (l > r) {
            return false;
        }
        int m = (l + r) / 2;
        if (nums[m] == target)
            return true;
        else if (target < nums[m]) {
            return binarySearch(nums, l, m - 1, target);
        } else {
            return binarySearch(nums, m + 1, r, target);
        }
    }

    // selection sort recursion
    public static void selectionSort(int[] nums, int i) {
        if (i >= nums.length) {
            return;
        }

        // min swap i
        int min = i;
        min = findMin(nums, i, min);
        // swap
        int temp = nums[min];
        nums[min] = nums[i];
        nums[i] = temp;

        selectionSort(nums, i + 1);
    }

    public static int findMin(int[] nums, int j, int min) {
        if (j >= nums.length) {
            return min;
        }
        if (nums[j] < nums[min]) {
            min = j;
        }
        return findMin(nums, j + 1, min);
    }

    public static void insertionSort() {

    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = random.nextInt(100);
        }
        selectionSort(nums, 0);
        System.out.println(Arrays.toString(nums));
    }
}
