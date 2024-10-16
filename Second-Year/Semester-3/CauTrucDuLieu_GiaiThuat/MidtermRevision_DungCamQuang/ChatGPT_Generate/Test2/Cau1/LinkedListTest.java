import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringLinkedListTest {

    @Test
    void testEmptyList() {
        StringLinkedList list = new StringLinkedList();
        assertTrue(list.isPalindrome(), "Danh sách rỗng là palindrome");
    }

    @Test
    void testSingleElement() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        assertTrue(list.isPalindrome(), "Danh sách có một phần tử là palindrome");
    }

    @Test
    void testTwoDifferentElements() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        assertFalse(list.isPalindrome(), "Danh sách có hai phần tử khác nhau không phải là palindrome");
    }

    @Test
    void testTwoSameElements() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("a");
        assertTrue(list.isPalindrome(), "Danh sách có hai phần tử giống nhau là palindrome");
    }

    @Test
    void testThreeElementsNotPalindrome() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        assertFalse(list.isPalindrome(), "Danh sách có ba phần tử không phải là palindrome");
    }

    @Test
    void testThreeElementsPalindrome() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        list.addLast("a");
        assertTrue(list.isPalindrome(), "Danh sách có ba phần tử là palindrome");
    }

    @Test
    void testMultipleElementsPalindrome() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("b");
        list.addLast("a");
        assertTrue(list.isPalindrome(), "Danh sách có nhiều phần tử là palindrome");
    }

    @Test
    void testMultipleElementsNotPalindrome() {
        StringLinkedList list = new StringLinkedList();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        assertFalse(list.isPalindrome(), "Danh sách có nhiều phần tử không phải là palindrome");
    }
}
