package Lab8_Revision;

public class HeapSort { 
    public static void buildMaxHeap(int [] arr){
        int n = arr.length - 1;
        for(int i = n/2 - 1; i >= 0;i--){
            heapifyDown(arr, i, n);
        }
    
    }
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void heapifyDown(int[] arr, int i, int size){
        int largest  = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < size && arr[left] > arr[largest]){
            largest = left;
        }

        if(right < size && arr[right]  > arr[largest]){
            largest = right;
        }

        if(largest != i){
            swap(arr,i , largest);
            heapifyDown(arr, largest, size);
        }
    } 

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    public static void heapSort(int[] arr){
        int n = arr.length;
        buildMaxHeap(arr);
        for(int i = n - 1; i > 0; i--){
            swap(arr, 0, i);
            heapifyDown(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr);

        heapSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
