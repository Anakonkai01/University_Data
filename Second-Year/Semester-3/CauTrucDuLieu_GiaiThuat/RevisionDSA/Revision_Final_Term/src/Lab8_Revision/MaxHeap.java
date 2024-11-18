package Lab8_Revision;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize];
    }

    // insert
    public void insert(int data) {
        if (size == maxSize) {
            System.out.println("Heap is full");
            return;
        }

        heap[size] = data;
        heapifyUp(size - 1);
        size++;
    }

    // get parent
    public int getParent(int i) {
        return (i - 1) / 2;
    }

    // get left child
    public int getLeftChild(int i) {
        return 2 * i + 1;
    }

    // get right child
    public int getRightChild(int i) {
        return 2 * i + 2;
    }

    // get max
    public int getMax() {
        return heap[0];
    }

    // heapify up
    public void heapifyUp(int i) {
        while (i > 0 && heap[i] > heap[getParent(i)]) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    // swap
    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // heapify down
    public void heapifyDown(int i) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int largest = i;

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    // decrease key
    public void decreaseKey(int i, int new_val) {
        heap[i] = new_val;
        heapifyUp(i);
    }

    // increase key
    public void increaseKey(int i, int new_val) {
        heap[i] = new_val;
        heapifyDown(i);
    }

    // delete max
    public void deleteMax() {
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
    }

    // extract max
    public int extractMax() {
        if (size <= 0) {
            return Integer.MAX_VALUE;
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    // build max heap
    public void buildMaxheap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // display heap
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // search
    public int search(int data) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == data) {
                return i;
            }
        }
        return -1;
    }

    // delete
    public void delete(int data) {
        int index = search(data);
        if (index == -1) {
            System.out.println("Data not found");
            return;
        }

        heap[index] = heap[size - 1];
        size--;
        heapifyDown(index);
    }

    // change value
    public void changeValue(int data, int newValue) {
        int index = search(data);
        if (index == -1) {
            System.out.println("Data not found");
            return;
        }

        heap[index] = newValue;
        if (newValue > data) {
            heapifyDown(index);
        } else {
            heapifyUp(index);
        }
    }
}
