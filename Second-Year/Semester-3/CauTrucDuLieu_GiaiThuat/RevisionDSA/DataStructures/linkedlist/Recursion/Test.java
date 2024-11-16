package DataStructures.LinkedList.Recursion;

import java.util.Arrays;

public class Test {
    public static void insertionSort(int nums[], int i) {
        if (i >= nums.length) {
            return;
        }

        System.out.println(Arrays.toString(nums));
        int j = innerLoop(nums, i - 1, nums[i]);
        System.out.println(j);
        nums[j + 1] = nums[i];
        insertionSort(nums, i + 1);
    }

    static int innerLoop(int[] nums, int j, int key) {

        if (j >= 0 && nums[j] > key) {
            nums[j + 1] = nums[j];
            return innerLoop(nums, j - 1, key);
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = { 12, 24, 1, 2, 4, 5, 8 };
        insertionSort(nums, 1);
        System.out.println(Arrays.toString(nums));
    }
}