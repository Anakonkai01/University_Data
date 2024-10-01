package SortAlgorithms;

import Exercise1.SortAlgorithms;

import java.util.*;
public class TestSort {
    public static void main(String[] args) {
        int[] nums ={12, 11, 5, 1, 2, 4};
        Random rand = new Random();
        Stack<Integer> myStack = new Stack<>();
        for(int i = 10; i > 0; i--){
            int ran = rand.nextInt(100);
            myStack.push(ran);
        }




//        System.out.println(myStack);
        SortAlgorithms.insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
