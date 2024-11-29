package Lab6_Revision.MyBTS_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MyBTS_Tree {

    // insert new node
    public static Node insert(Node node, int data){
        if(node == null){
            return new Node(data);
        }

        if(node.getData() < data){
            node.setRight(insert(node.getRight(), data));
        }
        else if(node.getData() > data){
            node.setLeft(insert(node.getLeft(), data));
        }
        return node;
    }


    // pre-order NLR
    public static void preOrder_NLR(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.getData() + " ");
        preOrder_NLR(node.getLeft());
        preOrder_NLR(node.getRight());
    }

    // in-order LNR
    public static void inOrder_LNR(Node node){
        if(node == null){
            return;
        }
        inOrder_LNR(node.getLeft());
        System.out.print(node.getData() + " ");
        inOrder_LNR(node.getRight());
    }

    // post-order LRN
    public static void postOrder_LNR(Node node){
        if(node == null){
            return;
        }
        postOrder_LNR(node.getLeft());
        postOrder_LNR(node.getRight());
        System.out.print(node.getData() + " ");
    }

    // search data
    public static Node search(Node node, int data){
        if(node == null){
            return null;
        }
        if(node.getData() == data){
            return node;
        }

        if(node.getData() < data){
            return search(node.getLeft(), data);
        }
        else if(node.getData() > data){
            return search(node.getRight(), data);
        }
        return null;
    }

    // find min
    public static Node getMin(Node node){
        if(node.getLeft() == null){
            return node;
        }
        return getMin(node.getLeft());
    }

    // find max
    public static int getMax(Node node){
        if(node.getRight() == null){
            return node.getData();
        }
        return getMax(node.getRight());
    }


    // create tree by passing a string
    public static Node createTree(Node root,String str){
        String [] keys = str.split(" ");
        for(String s : keys){
            MyBTS_Tree.insert(root, (Integer.parseInt(s)));
        }
        return root;
    }

    // contains
    public static boolean contains(Node node, int data){
        if(node == null){
            return false;
        }
        if(node.getData() == data){
            return true;
        }
        if(node.getData() < data){
            return contains(node.getLeft(), data);
        }
        else if(node.getData() > data){
            return contains(node.getRight(), data);
        }
        return false;
    }

    // deleteMax
    public static Node deleteMax(Node node){
        if(node.getRight()== null){
            return node.getLeft();
        }
        return deleteMax(node.getRight());
    }
    // deleteMin
    public static Node deleteMin(Node node){
        if(node.getLeft()== null){
            return node.getRight();
        }
        return deleteMin(node.getLeft());
    }

    
    // deleteNode
    public static Node deleteNode(Node node, int data){
        if(node == null){
            return null;
        }
        // tim node
        if(data < node.getData()){
            node.setLeft(deleteNode(node.getLeft(), data));
        }
        else if(data > node.getData()){
            node.setRight(deleteNode(node.getRight(), data));
        }
        else{
            if(node.getLeft() == null && node.getRight() == null){
                return null;
            }
            if(node.getLeft() == null){
                return node.getRight();
            }
            if(node.getRight() == null){
                return node.getLeft();
            }

            Node successor = MyBTS_Tree.getMin(node.getRight());
            node.setData(successor.getData());
            node.setRight(deleteNode(node.getRight(), successor.getData()));
        }
        return node;
    }

    // find successor
    public static Node findSuccessor(Node node, int data){
        Node current = search(node, data);
        if(current == null){
            return null;
        }
        if(current.getRight() != null){
            return getMin(current.getRight());
        }
        Node successor = null;
        Node ancestor = node;
        while(ancestor != current){
            if(current.getData() < ancestor.getData()){
                successor = ancestor;
                ancestor = ancestor.getLeft();
            }
            else{
                ancestor = ancestor.getRight();
            }
        }
        return successor;
    }

    // delete predecessor
    // delete successor


    // find height of the tree
    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        int left = 1 + height(node.getLeft());
        int right = 1 + height(node.getRight());

        return Math.max(left, right);
    }
    // sum key in tree
    public static int sum(Node node) {
        if(node == null){
            return 0;
        }
        return node.getData() + sum(node.getLeft()) + sum(node.getRight());
    }
    // sum leave
    public static int sumLeaves(Node node) {
        if(node == null){
            return 0;
        }
        if(node.getLeft() == null && node.getRight() == null){
            return node.getData();
        }
        return sumLeaves(node.getLeft()) + sumLeaves(node.getRight());
    }
    // sum even
    public static int sumEven(Node node) {
        if(node == null){
            return 0;
        }
        if(node.getData() % 2 == 0){
            return node.getData() + sumEven(node.getLeft()) + sumEven(node.getRight());
        }
        return sumEven(node.getLeft()) + sumEven(node.getRight());
    }
    // count leaves
    public static int countLeaves(Node node){
        if(node == null){
            return 0;
        }
        if(node.getLeft() == null && node.getRight() == null){
            return 1 + countLeaves(node.getLeft()) + countLeaves(node.getRight());
        }
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }
    // bfs
    public static void bfs_print(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            node = queue.poll();
            System.out.print(node.getData() + " ");
            if(node.getLeft() != null){
                queue.add(node.getLeft());
            }
            if(node.getRight() != null){
                queue.add(node.getRight());
            }
        }
    }

    // check validBTSTree
//    public static boolean isValidBTS(Node node, Integer min, Integer max){
//        if(node == null){
//            return true;
//        }
//        if(node.getLeft())
//    }

}
