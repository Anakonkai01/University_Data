package Lab8_Revision;


public class Main {
    public static void main(String[] args) {
        // Khởi tạo MinHeap với kích thước tối đa là 10
        MinHeap minHeap = new MinHeap(10);

        // Chèn các phần tử vào heap
        System.out.println("Inserting elements into the heap...");
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        // In heap sau khi chèn
        System.out.println("Heap after insertions:");
        minHeap.printHeap();

        // Trích xuất phần tử nhỏ nhất
        System.out.println("\nExtracting the minimum element...");
        int min = minHeap.extractMin();
        System.out.println("Extracted Min: " + min);

        // In heap sau khi trích xuất
        System.out.println("Heap after extracting minimum:");
        minHeap.printHeap();

        // Xóa một phần tử
        System.out.println("\nDeleting element 8 from the heap...");
        minHeap.delete(8);

        // In heap sau khi xóa
        System.out.println("Heap after deletion:");
        minHeap.printHeap();

        // Thay đổi giá trị tại một chỉ số
        System.out.println("\nChanging value at index 1 to 2...");
        minHeap.changeValue(1, 2);

        // In heap sau khi thay đổi
        System.out.println("Heap after changing value:");
        minHeap.printHeap();

        // Tìm kiếm một phần tử
        System.out.println("\nSearching for element 2 in the heap...");
        int index = minHeap.seachIndex(2);
        if (index != -1) {
            System.out.println("Element 2 found at index: " + index);
        } else {
            System.out.println("Element 2 not found in the heap.");
        }
    }
}
