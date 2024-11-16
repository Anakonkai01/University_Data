package Lab8_Revision;

public class MaxHeap {
    private final int[] heap;
    private int size;
    private final int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    public int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    // insert
    public void insert(int value) {
        if(size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = value;

        // heapify up
        heapify_up(size);
        // increase size
        size++;
    }

    public void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void heapify_up(int i){
        while(i > 0 && heap[i] > heap[getParentIndex(i)]) {
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public void heapify_down(int i){
        int leftChildIndex = getLeftChildIndex(i);
        int rightChildIndex = getRightChildIndex(i);
        int largest = i;

        if(leftChildIndex < size && heap[leftChildIndex] > heap[largest]) {
            largest = leftChildIndex;
        }

        if(rightChildIndex < size && heap[rightChildIndex] > heap[largest]) {
            largest = rightChildIndex;
        }

        if(largest != i) {
            swap(i, largest);
            heapify_down(largest);
        }
    }


    public int extractMax() {
        if(size <= 0){
            throw new IllegalArgumentException("Heap is empty");
        }
        // take the max value in the root of max heap
        int max = heap[0];
        // replace root by the last child
        heap[0] = heap[size - 1];
        size--;
        // heapify-down the root
        heapify_down(0);
        return max;
    }

    public int getMax() {
        return heap[0];
    }



    // delete
    public void delete(int i){
        if(i < 0 || i >= size){
            throw new IllegalArgumentException("Heap is empty");
        }

        if(i == size - 1){
            size--;
            return;
        }

        heap[i] = heap[size - 1];
        size--;

        if(i > 0 && heap[i] > heap[getParentIndex(i)]){
            heapify_up(i);
        }
        else{
            heapify_down(i);
        }
    }
    // change value
}
