package Lab8.Exercise2;

public class MinHeap {
    public int[] heapArray;
    public int MAX_SIZE;
    public int currentSize;

    public MinHeap(int size) {
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
        while (current > 0 && heapArray[current] < heapArray[getParentIndex(current)]) {
            swap(current, getParentIndex(current));
            current = getParentIndex(current);
        }
        currentSize++;
        return true;

    }

    public int extractMin() {
        if (currentSize <= 0) {
            System.out.println("Heap is empty");
            return Integer.MAX_VALUE;
        }

        int max = heapArray[0];
        heapArray[0] = heapArray[currentSize - 1];
        currentSize--;
        minHeapify(0);
        return max;
    }

    public void minHeapify(int i) {
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);
        int smallest = i;

        if (left < currentSize && heapArray[left] < heapArray[smallest]) {
            smallest = left;
        }
        if (right < currentSize && heapArray[right] < heapArray[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    
    }

    public void shiftUp(int i){
        while(i > 1 && heapArray[i] < heapArray[getParentIndex(i)]){
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }
    public void shiftDown(int i){
        minHeapify(i);
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