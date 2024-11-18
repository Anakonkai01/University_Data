package Lab8_Revision;

public class MinHeap {
    private int[] heap;
    private int size;
    private int MAX_SIZE;

    // constructor  
    public MinHeap(int MAX_SIZE){
        this.heap = new int[MAX_SIZE];
        this.MAX_SIZE = MAX_SIZE;
        this.size = 0;
    }

    // get parent
    public int getParent(int i){
        return (i-1)/2;
    }
    
    // get left child
    public int getLeftChild(int i){
        return 2*i + 1;
    }

    // get right child
    public int getRightChild(int i){
        return 2*i + 2;
    }

    // swap
    public void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // heapifyDown
    public void heapifyDown(int i){
        int smallest = i;
        int left = getLeftChild(i);
        int right = getRightChild(i);

        if(left < size && heap[left] < heap[smallest]){
            smallest = left;
        }

        if(right < size && heap[right] < heap[smallest]){
            smallest = right;
        }

        if(smallest != i){
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // heapifyUp
    public void heapifyUp(int i){
        while(i > 0 && heap[i] < heap[getParent(i)]){
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    // insert
    public void insert(int data){
        if(size >= MAX_SIZE){
            System.out.println("Heap overflow");
            return;
        }

        heap[size] = data;
        heapifyUp(size);
        size++;
    }

    // extractMax 
    public int extractMin(){
        if(size <= 0){
            System.out.println("heap is empty");
            return Integer.MIN_VALUE; 
        }

        if(size == 1){
            size--;
            return heap[0];
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
         
        return min;
    }

    // delete
    public void delete(int data){
        int index = seachIndex(data);

        if(index == -1)
            return ;else{
            heapifyUp(index);
        }

        changeValue(index, Integer.MIN_VALUE);
        extractMin();
    }

    // change value at Index
    public void changeValue(int index, int data){
        heap[index] = data;

        if(data > heap[getParent(index)]){
            heapifyDown(index);
        }
        else{
            heapifyUp(index);
        }
    }


    // search index
    public int seachIndex(int data){
        for(int i = 0; i < size; i++){
            if(heap[i] == data)
                return i;
        }
        return -1;
    }

    public void printHeap() {

        for (int i = 0; i < size; i++) {

            System.out.print(heap[i] + " ");

        }

        System.out.println();

    }

    public int getMin(){
        return heap[0];
    }
}
