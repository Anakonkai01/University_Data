package Exercise;

public class Exercises {
    public staticNode deleteMinOdd(Node node) {
    if (node == null) return null;

    // Tìm khóa lẻ nhỏ nhất trong cây con trái
    if (node.left != null) {
        node.left = deleteMinOdd(node.left);
        return node;
    }

    // Kiểm tra xem node hiện tại có phải là số lẻ không
    if (node.key % 2 != 0) return node.right;  // Xóa node này

    // Nếu không phải số lẻ, tiếp tục tìm ở cây con phải
    node.right = deleteMinOdd(node.right);
    return node;
}

    
}
