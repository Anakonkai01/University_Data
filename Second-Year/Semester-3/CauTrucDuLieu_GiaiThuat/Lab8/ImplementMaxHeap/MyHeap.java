package Lab8.ImplementMaxHeap;

public class MyHeap {
    public int[] heapArray;
    public int size;
    public int current_size_heap;

    public MyHeap(int size) {
        this.size = size;
        this.heapArray = new int[size];
        this.current_size_heap = 0;
    }

    void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int parent(int key) {
        return (key - 1) / 2;
    }

    public int left(int key) {
        return key * 2 + 1;
    }

    public int right(int key) {
        return key * 2 + 2;
    }

    public boolean insertKey(int val) {

        if (current_size_heap == size) {
            return false;
        }

        int i = current_size_heap;
        heapArray[i] = val;
        current_size_heap++;

        while (i != 0 && heapArray[i] < heapArray[parent(i)]) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
        return true;
    }

    // minHeapify
    public void minHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;
        if (left < current_size_heap && heapArray[smallest] > heapArray[left]) {
            smallest = left;
        }
        if (right < current_size_heap && heapArray[smallest] > heapArray[right]) {
            smallest = right;
        }
        if (smallest != i) {
            swap(heapArray, smallest, i);
            minHeapify(smallest);
        }
    }

    // decrease key
    public void decreaseKey(int i, int newVal) {
        heapArray[i] = newVal;
        while (i != 0 && heapArray[i] < heapArray[parent(i)]) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
    }

    // increase key
    public void increaseKey(int i, int newVal) {
        heapArray[i] = newVal;
        minHeapify(i);
    }

    // getmin
    public int getMin() {
        return heapArray[0];
    }

    // extract min
    public int extractMin() {
        if (current_size_heap <= 0) {
            return Integer.MAX_VALUE;
        }

        if (current_size_heap == 1) {
            current_size_heap--;
            return heapArray[0];
        }

        int rootValue = heapArray[0];
        heapArray[0] = heapArray[current_size_heap - 1];
        current_size_heap--;
        minHeapify(0);

        return rootValue;
    }

    // deleteKey
    public void deleteKey(int i) {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    // change value at key
    public void changeValueAtKey(int i, int newVal) {
        if (i >= current_size_heap || i < 0 || heapArray[i] == newVal) {
            return;
        }
        if (heapArray[i] > newVal) {
            decreaseKey(i, newVal);
        } else {
            increaseKey(i, newVal);
        }
    }

}

class MinHeapTest {
    public static void main(String[] args) {
        MyHeap h = new MyHeap(11);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        System.out.print(h.extractMin() + " ");
        System.out.print(h.getMin() + " ");

        h.decreaseKey(2, 1);
        System.out.print(h.getMin());
    }
}