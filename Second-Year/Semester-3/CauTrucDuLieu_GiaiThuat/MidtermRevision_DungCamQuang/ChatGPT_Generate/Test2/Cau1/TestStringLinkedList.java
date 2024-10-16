public class TestStringLinkedList {
    public static void main(String[] args) {
        testEmptyList();
        testSingleElement();
        testNoDuplicates();
        testDuplicatesAtStart();
        testDuplicatesAtEnd();
        testDuplicatesInMiddle();
        testAllDuplicates();
    }

    // 1. Test danh sách rỗng
    public static void testEmptyList() {
        StringLinkedList list = new StringLinkedList();
        System.out.println("Test 1: Danh sách rỗng");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: (danh sách rỗng)\n");
    }

    // 2. Test danh sách có một phần tử
    public static void testSingleElement() {
        StringLinkedList list = new StringLinkedList();
        list.addFirst("apple");
        System.out.println("Test 2: Danh sách có một phần tử");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple\n");
    }

    // 3. Test danh sách không có phần tử trùng lặp
    public static void testNoDuplicates() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("cherry");
        list.addLast("date");
        System.out.println("Test 3: Danh sách không có phần tử trùng lặp");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple -> banana -> cherry -> date\n");
    }

    // 4. Test danh sách có các phần tử trùng lặp ở đầu
    public static void testDuplicatesAtStart() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("apple");
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("cherry");
        System.out.println("Test 4: Danh sách có các phần tử trùng lặp ở đầu");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple -> banana -> cherry\n");
    }

    // 5. Test danh sách có các phần tử trùng lặp ở cuối
    public static void testDuplicatesAtEnd() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("cherry");
        list.addLast("cherry");
        System.out.println("Test 5: Danh sách có các phần tử trùng lặp ở cuối");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple -> banana -> cherry\n");
    }

    // 6. Test danh sách có các phần tử trùng lặp ở giữa
    public static void testDuplicatesInMiddle() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("apple");
        list.addLast("banana");
        list.addLast("banana");
        list.addLast("cherry");
        list.addLast("date");
        System.out.println("Test 6: Danh sách có các phần tử trùng lặp ở giữa");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple -> banana -> cherry -> date\n");
    }

    // 7. Test danh sách có tất cả các phần tử trùng lặp
    public static void testAllDuplicates() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("apple");
        list.addLast("apple");
        list.addLast("apple");
        list.addLast("apple");
        System.out.println("Test 7: Danh sách có tất cả các phần tử trùng lặp");
        System.out.print("Trước khi xóa: ");
        list.print();
        list.removeDuplicates();
        System.out.print("Sau khi xóa: ");
        list.print();
        System.out.println("Expected: apple\n");
    }
}
z