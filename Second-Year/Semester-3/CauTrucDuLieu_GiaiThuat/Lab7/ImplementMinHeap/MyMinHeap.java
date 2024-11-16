package Lab7.ImplementMinHeap;

public class MyMinHeap {
    public int max_size;
    public int current_size;
    public int[] heapArray;

    public MyMinHeap(int max_size) {
        this.max_size = max_size;
        current_size = 0;
        heapArray = new int[max_size];
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    public int getLeftIndex(int i){
        return 2*i + 1;
    }
    public int getRightIndex(int i){
        return 2*i + 2;
    }

    public void swap( int i, int j){
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public void minHeapify(int i){
        int left = getLeftIndex(i);
        int right = getRightIndex(i);
        int smallest = i;

        if(left < max_size && heapArray[left] > heapArray[smallest]){
            smallest = left;
        }
        if(right < max_size && heapArray[right] > heapArray[smallest]){
            smallest = right;
        }

        if(smallest != i){
            swap(smallest,i);
            minHeapify(smallest);
        }
    }

    public void decrease(int i, int newVal){
        heapArray[i] = newVal;
        while(i != 0 && heapArray[i] < heapArray[getParentIndex(i)]){
            swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public void increase(int i, int newVal){
        heapArray[i] = newVal;
        minHeapify(i);
    }


    public boolean insert(int value){
        if(current_size == max_size){
            System.out.println("max size reached");
            return false;
        }

        current_size++;
        heapArray[current_size - 1] = value;
    }
}
