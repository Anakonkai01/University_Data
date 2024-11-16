package AVLTree;

class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1; // Khởi tạo chiều cao node là 1
    }
}

public class AVLTree {
    private Node root;

    // Tính chiều cao của một node
    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Tính hệ số cân bằng của một node
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Xoay phải
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Thực hiện xoay
        x.right = y;
        y.left = T2;

        // Cập nhật chiều cao
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Trả về root mới
    }

    // Xoay trái
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Thực hiện xoay
        y.left = x;
        x.right = T2;

        // Cập nhật chiều cao
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Trả về root mới
    }

    // Chèn một phần tử vào cây
    public Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // Trường hợp trùng lặp, không chèn

        // Cập nhật chiều cao của node tổ tiên
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Kiểm tra và cân bằng lại cây
        int balance = getBalance(node);

        // Trường hợp mất cân bằng:
        if (balance > 1 && key < node.left.key)
            return rotateRight(node); // Left Left
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node); // Right Right
        if (balance > 1 && key > node.left.key) { // Left Right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.key) { // Right Left
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Duyệt cây theo thứ tự trung thứ (In-order)
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void printInOrder() {
        inOrder(root);
    }

    public Node deleteMinOdd(Node node) {
        if (node == null)
            return null;

        // Tìm khóa lẻ nhỏ nhất trong cây con trái
        if (node.left != null) {
            node.left = deleteMinOdd(node.left);
            return node;
        }

        // Kiểm tra xem node hiện tại có phải là số lẻ không
        if (node.key % 2 != 0)
            return node.right; // Xóa node này

        // Nếu không phải số lẻ, tiếp tục tìm ở cây con phải
        node.right = deleteMinOdd(node.right);
        return node;
    }

    public int sumLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return node.key; // Node lá
        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    public int sumOddKeys(Node node) {
        if (node == null)
            return 0;

        int sum = (node.key % 2 != 0) ? node.key : 0; // Nếu là số lẻ thì cộng vào tổng
        sum += sumOddKeys(node.left);
        sum += sumOddKeys(node.right);

        return sum;
    }

    public class Result {
        int value; // Khóa lẻ đầu tiên
        int comparisons; // Số lần so sánh

        public Result(int value, int comparisons) {
            this.value = value;
            this.comparisons = comparisons;
        }
    }

    public Result findFirstOddLNR(Node node) {
        return findFirstOddLNRHelper(node, 0);
    }

    private Result findFirstOddLNRHelper(Node node, int comparisons) {
        if (node == null)
            return new Result(-1, comparisons);

        // Duyệt cây con trái
        Result leftResult = findFirstOddLNRHelper(node.left, comparisons);
        if (leftResult.value != -1)
            return leftResult;

        // Kiểm tra node hiện tại
        comparisons++;
        if (node.key % 2 != 0)
            return new Result(node.key, comparisons);

        // Duyệt cây con phải
        return findFirstOddLNRHelper(node.right, comparisons);
    }

}
