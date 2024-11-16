package Lab7_Revision;

import static Lab6_Revision.MyBTS_Tree.MyBTS_Tree.getMin;

public class MyAVLTree {
    // insert
    public static Node insert(Node root,int key){
        // insert like BTS tree
        if(root == null){
            return new Node(key);
        }
        if(key < root.getData()){
            root.setLeft(insert(root.getLeft(),key));
        }
        else if(key > root.getData()){
            root.setRight(insert(root.getRight(),key));
        }
        else{
            return root; // duplicate value not allow
        }

        // update the height of every Node of the tree from the bottom to the top
        root.setHeight(1 + Math.max(getHeight(root.getLeft()),getHeight(root.getRight())));

        // check the balance
        int balanceFactor = getBalanceFactor(root);
        // balance the tree
        // left-left
        if(balanceFactor > 1 && key < root.getLeft().getData()){
            return rightRotate(root);
        }
        // right-right
        if(balanceFactor < -1 && key > root.getRight().getData()){
            return leftRotate(root);
        }
        // left-right
        if(balanceFactor > 1 && key > root.getLeft().getData()){
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        // right-left
        if(balanceFactor < -1 && key < root.getRight().getData()){
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root; // subtree or tree is balance
    }

    // left rotate
    public static Node leftRotate(Node x){
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // rotate
        y.setLeft(x);
        x.setRight(T2);

        // update height
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));
        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));

        // return new root
        return y;
    }
    // right rotate
    public static Node rightRotate(Node y){
        Node x = y.getLeft();
        Node T2 = x.getRight();
        // rotate
        x.setRight(y);
        y.setLeft(T2);
        // update the height
        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));
        // return the root
        return x;
    }
    // get height
    public static int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.getHeight();
    }
    // get balance factor
    public static int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    // delete node
    public static Node deleteNode(Node root, int key){
        // delete node like BST
        if(root == null){
            return root;
        }
        if(key < root.getData()){
            root.setLeft(deleteNode(root.getLeft(), key));
        }
        else if(key > root.getData()){
            root.setRight(deleteNode(root.getRight(), key));
        }
        else{
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            }
            if(root.getLeft() == null){
                return root.getRight();
            }
            if(root.getRight() == null){
                return root.getLeft();
            }

            Node successor = getMin(root.getRight());
            root.setData(successor.getData());
            root.setRight(deleteNode(root.getRight(), successor.getData()));
        }


        // update height of the tree
        root.setHeight(1 + Math.max(getHeight(root.getLeft()),getHeight(root.getRight())));

        // check the balance
        int balanceFactor = getBalanceFactor(root);
        // left-left
        if(balanceFactor > 1 && getBalanceFactor(root.getLeft()) >= 0){
            return rightRotate(root);
        }
        // right-right
        if(balanceFactor < -1 && getBalanceFactor(root.getRight()) <= 0){
            return leftRotate(root);
        }
        // left-right
        if(balanceFactor > 1 && getBalanceFactor((root.getLeft())) < 0){
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        // right-left
        if(balanceFactor < -1 && getBalanceFactor((root.getRight())) > 0){
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }

    public static Node getMin(Node root){
        Node curr = root;
        while(curr.getLeft() != null){
            curr = curr.getLeft();
        }
        return curr;
    }
    // print
    public static void LNR(Node root){
        if(root == null){
            return;
        }
        LNR(root.getLeft());
        System.out.println(root.getData());
        LNR(root.getRight());
    }
}
