
class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLTree {
    Node root;

    // Calculate height of a node
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Get balance factor of a node
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Right rotation
    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation
    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a node
    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rotateRight(node);
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void insert(int key) {
        root = insert(root, key);
    }

    // 1
    Node deleteMinOdd(Node node) {
        if (node == null)
            return null;

        if (node.left != null) {
            node.left = deleteMinOdd(node.left);
            return node;
        }

        if (node.key % 2 != 0)
            return node.right;
        node.right = deleteMinOdd(node.right);
        return node;
    }

    // 2
    int sumLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return node.key;
        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    // 3
    int sumOddKeys(Node node) {
        if (node == null)
            return 0;
        int sum = (node.key % 2 != 0) ? node.key : 0;
        sum += sumOddKeys(node.left);
        sum += sumOddKeys(node.right);
        return sum;
    }

    // 4
    public int findFirstOddSteps(Node node) {
        int[] steps = { 0 };
        findFirstOddLNRHelper(node, steps);
        return steps[0];
    }

    private boolean findFirstOddLNRHelper(Node node, int[] steps) {
        if (node == null)
            return false;
        if (findFirstOddLNRHelper(node.left, steps))
            return true;

        steps[0]++;
        if (node.key % 2 != 0)
            return true;
        return findFirstOddLNRHelper(node.right, steps);
    }

    int countChildNodes(Node node) {
        if (node == null)
            return 0;
        int count = 0;
        if (node.left != null)
            count++;
        if (node.right != null)
            count++;
        return count;
    }

    // In-order traversal
    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    void printInOrder() {
        inOrder(root);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);

        System.out.println("In-order traversal:");
        tree.printInOrder();

        tree.root = tree.deleteMinOdd(tree.root);
        tree.printInOrder();

        System.out.println(tree.sumLeaves(tree.root));
        System.out.println(tree.sumOddKeys(tree.root));

        System.out.println(tree.countChildNodes(tree.root));
    }
}
