package SortAlgorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

public class NewSort {

    // de + max
    static int[] selectionSort_deMAX(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int max = i;
            System.out.println(Arrays.toString(nums));
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] > nums[max]) {
                    int temp = nums[j];
                    nums[j] = nums[max];
                    nums[max] = temp;
                }
            }
        }
        return nums;
    }


    // de + min
    static int[] selectionSort_deMIN(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int min = i;
            System.out.println(Arrays.toString(nums));

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[min]) {
                    int temp = nums[j];
                    nums[j] = nums[min];
                    nums[min] = temp;
                }
            }
        }
        return nums;
    }

    //acen + min
    static int[] selectionSort_acMIN(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.println(Arrays.toString(nums));

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return nums;
    }

    // ac + max
    static int[] selectionSort_acMAX(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            System.out.println(Arrays.toString(nums));

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return nums;
    }


    // bubble sort + up


    // ac + down
    static int[] bubbleSort1(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            System.out.println(Arrays.toString(nums));
            for (int j = nums.length - 1; j > i; --j)
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
        }
        return nums;
    }

    // ac + up
    static int[] bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.println(Arrays.toString(nums));
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    static int[] bubbleSort3(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            System.out.println(Arrays.toString(nums));
            for (int j = nums.length - 1; j > 0; j--) {
                if (nums[j] > nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
        return nums;
    }

    static int[] bubbleSort4(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            System.out.println(Arrays.toString(nums));
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }


    static int[] insertionSort1(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int x = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > x){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = x;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    static int[] insertionSort2(int[] nums){
        for(int i = nums.length - 2; i >= 0; i--){
            int x = nums[i];
            int j = i + 1;
            while (j <= nums.length - 1 && nums[j] < x){
                nums[j-1] = nums[j];
                j++;
            }
            nums[j-1] = x;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    static int[] insertionSort3(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int x = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] < x){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = x;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    static int[] insertionSort4(int[] nums){
        for(int i = nums.length - 2; i >= 0; i--){
            int x = nums[i];
            int j = i + 1;
            while (j <= nums.length - 1 && nums[j] > x){
                nums[j-1] = nums[j];
                j++;
            }
            nums[j-1] = x;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = {12, 11, 15, 8, 1, 4};
        nums = insertionSort4(nums);
        System.out.println(Arrays.toString(nums));
    }
}
