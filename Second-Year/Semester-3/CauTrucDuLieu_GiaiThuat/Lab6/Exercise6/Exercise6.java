package Lab6.Exercise6;

import Lab6.implementBST.BinarySearchTree;
import Lab6.implementBST.TreeNode;

import static Lab6.Exercise2.Main.createTree;

public class Exercise6 {
    public static TreeNode delete_pre(TreeNode root, Integer key) {
        if (root == null)
            return root;

        // find the subtree

        if (root.getVal() < key) {
            root.setRight(delete_pre(root.getRight(), key));
        } else if (root.getVal() > key) {
            root.setLeft(delete_pre(root.getLeft(), key));
        } else {
            // when node is 0 child or 1 child left
            if (root.getLeft() == null) {
                return root.getRight();
            }

            // node is 1 child right
            if (root.getRight() == null) {
                return root.getLeft();
            }

            // have both children
            TreeNode predecessor = getPrecessor(root);
            root.setVal(predecessor.getVal()); // find the predecessor
            root.setLeft(delete_pre(root.getLeft(), predecessor.getVal())); // delete predecessor node
        }
        return root;
    }

    public static TreeNode getPrecessor(TreeNode curr) {
        curr = curr.getLeft();
        while (curr != null && curr.getRight() != null) {
            curr = curr.getRight();
        }
        return curr;
    }

    public static void main(String[] args) {
        TreeNode root = createTree("1 2 3 4 5 6 7");
        root = delete_pre(root, 1);
        BinarySearchTree.print_levelOrder(root);
    }
}
