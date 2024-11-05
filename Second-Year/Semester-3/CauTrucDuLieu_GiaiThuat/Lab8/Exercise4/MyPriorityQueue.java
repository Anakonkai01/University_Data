package Lab8.Exercise4;

import java.util.HashMap;

import Lab8.Exercise1.MaxHeap;

public class MyPriorityQueue {
    private MaxHeap myHeap;
    private HashMap<String, Integer> map;

    public MyPriorityQueue(int size) {
        this.myHeap = new MaxHeap(size);
        this.map = new HashMap<>();
    }

    public void enQueue(String name, int priority) {
        myHeap.insert(priority);
        
        map.put(name, priority);
    }

    public String deQueue() {
        int maxPriority = myHeap.extractMax();
        
        String maxName = null;
        for (String name : map.keySet()) {
            if (map.get(name) == maxPriority) {
                maxName = name;
                map.remove(name); 
                break;
            }
        }

        return maxName;
    }
}
