package Lab8_Revision;

public class PriorityQueue {
    private MinHeap minheap; 

    public PriorityQueue(int capacity){
        this.minheap = new MinHeap(capacity);
    }

    public void enQueue(int priorityNums){
        minheap.insert(priorityNums);
    }

    public int deQueue(){
        return minheap.extractMin();
    }

    public int peek(){
        return minheap.getMin();
    }

    public void printQueue(){
        minheap.printHeap();
    }
}
