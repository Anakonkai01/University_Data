package Recursion;

import java.util.*;

public class Test {

  public static void insertionSort(int[] nums, int i) {
    if (i >= nums.length) {
      return;
    }

    int key = nums[i];
    int j = innerLoop(nums, i - 1, key);
    System.out.println(key);
    nums[j + 1] = key;
    insertionSort(nums, i + 1);
  }

  public static int innerLoop(int[] nums, int j, int key) {
    if (j >= 0 && nums[j] > key) {
      nums[j + 1] = nums[j];
      // j--;
      return innerLoop(nums, j - 1, key);
    }
    return j;
  }

  public static void insertQueue(Queue<Integer> queue, Integer e) {
    if (queue.isEmpty() || e <= queue.peek()) {
      queue.offer(e);
      return;
    } else {
      int temp = queue.poll();
      insertQueue(queue, e);
      queue.offer(temp);
    }
  }

  // public static void sortQueue(Queue<Integer> queue) {
  // if (queue.isEmpty())
  // return;
  // int element = queue.poll();
  // sortQueue(queue);
  // insertQueue(queue, element);
  // }

  // Hàm sắp xếp queue bằng đệ quy
  // public static void sortQueue(Queue<Integer> queue) {
  // // Nếu queue rỗng thì dừng đệ quy
  // if (queue.isEmpty()) {
  // return;
  // }

  // // Lấy phần tử đầu tiên ra khỏi queue
  // int front = queue.poll();

  // // Đệ quy sắp xếp phần còn lại của queue
  // sortQueue(queue);

  // // Chèn phần tử vừa lấy ra vào vị trí đúng trong queue đã sắp xếp
  // insertInSortedOrder(queue, front);
  // }

  // // Hàm phụ để chèn một phần tử vào queue đã sắp xếp

  public static void sortQueue(Queue<Integer> queue) {
    // Base case: nếu queue có 0 hoặc 1 phần tử thì không cần sắp xếp
    if (queue.size() <= 1) {
      return;
    }

    // Lấy phần tử đầu tiên ra
    int front = queue.poll();

    // Sắp xếp phần còn lại của queue
    sortQueue(queue);

    // Chèn phần tử front vào vị trí đúng
    insertInSortedOrder(queue, front);
  }

  public static void sortQueueUsingSelectionSort(Queue<Integer> queue) {
    int n = queue.size();

    for (int i = 0; i < n - 1; i++) {
      // Tìm phần tử nhỏ nhất trong các phần tử chưa sắp xếp
      int minIndex = i;
      int minValue = Integer.MAX_VALUE;

      // Duyệt qua queue để tìm phần tử nhỏ nhất
      for (int j = 0; j < n; j++) {
        int current = queue.poll();
        if (j >= i && current < minValue) {
          minValue = current;
          minIndex = j;
        }
        queue.add(current);
      }

      // Đưa các phần tử về đúng vị trí
      for (int j = 0; j < n; j++) {
        int current = queue.poll();
        if (j == minIndex) {
          // Lưu phần tử nhỏ nhất
          queue.add(current);
        } else if (j == i) {
          // Thêm phần tử nhỏ nhất vào vị trí i
          queue.add(minValue);
        } else {
          queue.add(current);
        }
      }
    }
  }

  public static void insertInSortedOrder(Queue<Integer> queue, int element) {
    // Tìm vị trí đúng để chèn element
    int size = queue.size();

    // Nếu queue rỗng hoặc element là nhỏ nhất, chèn luôn
    if (size == 0 || element <= queue.peek()) {
      queue.add(element);
      return;
    }

    // Lấy phần tử đầu ra và lưu lại
    int current = queue.poll();

    // Tiếp tục tìm vị trí chèn cho element
    insertInSortedOrder(queue, element);

    // Sau khi chèn element, thêm lại current vào đầu queue
    queue.add(current);
  }

  public static void main(String[] args) {
    int[] nums = { 12, 11, 4, 55, 23, 13, 4, 5, 1, 2 };
    // Arrays.sort(nums);

    // System.out.println(Arrays.toString(nums));
    // insertionSort(nums, 1);
    // System.out.println(Arrays.toString(nums));

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(88);
    queue.offer(85);
    queue.offer(45);
    queue.offer(82);
    queue.offer(69);

    System.out.println(queue);
    sortQueueUsingSelectionSort(queue);
    System.out.println(queue);

  }
}
