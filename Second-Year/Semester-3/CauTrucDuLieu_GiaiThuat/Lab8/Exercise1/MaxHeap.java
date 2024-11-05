package Lab8.Exercise1;

public class MaxHeap {
    public int[] heapArray;
    public int MAX_SIZE;
    public int currentSize;

    public MaxHeap(int size) {
        this.MAX_SIZE = size;
        this.currentSize = 0;
        this.heapArray = new int[MAX_SIZE];
    }

    public int getParentIndex(int i) {
        return (i - 1)/2;
    }

    public int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    public int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    public void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public boolean insert(int val) {
        if (currentSize >= MAX_SIZE) {
            System.out.println("Heap is full. Cannot insert " + val);
            return false;
        }

        heapArray[currentSize] = val;
        int current = currentSize;
        while (current > 0 && heapArray[current] > heapArray[getParentIndex(current)]) {
            swap(current, getParentIndex(current));
            current = getParentIndex(current);
        }
        currentSize++;
        return true;

    }

    public int extractMax() {
        if (currentSize <= 0) {
            System.out.println("Heap is empty");
            return Integer.MIN_VALUE;
        }

        int max = heapArray[0];
        heapArray[0] = heapArray[currentSize - 1];
        currentSize--;
        maxHeapify(0);
        return max;
    }

    public void maxHeapify(int i) {
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);
        int largest = i;

        if (left < currentSize && heapArray[left] > heapArray[largest]) {
            largest = left;
        }
        if (right < currentSize && heapArray[right] > heapArray[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    
    }

    public void shiftUp(int i){
        while(i > 1 && heapArray[i] > heapArray[getParentIndex(i)]){
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }
    public void shiftDown(int i){
        maxHeapify(i);
    }

    public void printHeap() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
    }

    public void heapSort(){
        for(int i = currentSize/2 -1; i >=0 ; i--){
            shiftDown(i);
        }
        for(int i = currentSize - 1; i > 0; i--){
            swap(0, i);
            shiftDown(i);
        }
    }
}