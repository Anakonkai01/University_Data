package Lab6_Revision.MyBTS_Tree;

public class MyBTS_Tree {

    // insert new node
    public Node insert(Node node, int data){
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
    public void preOrder_NLR(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.getData() + " ");
        preOrder_NLR(node.getLeft());
        preOrder_NLR(node.getRight());
    }

    // in-order LNR
    public void inOrder_LNR(Node node){
        if(node == null){
            return;
        }
        inOrder_NLR(node.getLeft());
        System.out.print(node.getData() + " ");
        inOrder_NLR(node.getRight());
    }

    // post-order LRN
    public void postOrder_LNR(Node node){
        if(node == null){
            return;
        }
        postOrder_LNR(node.getLeft());
        postOrder_LNR(node.getRight());
        System.out.print(node.getData() + " ");
    }

    // search data
    public Node search(Node node, int data){
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
    public int getMin(Node node){
        if(node.getLeft() == null){
            return node.getData();
        }
        return getMin(node.getLeft());
    }

    // find max
    public int getMax(Node node){
        if(node.getRight() == null){
            return node.getData();
        }
        return getMax(node.getRight());
    }


    // create tree by passing a string
    public static Node createTree(String str){
        return  null;
    }

    // contains
    // deleteMax
    // deleteMin
    // deleteNode
    // delete predecessor
    // delete successor
    // find height of the tree
    // sum key in tree
    // sum leave
    // sum even
    // count leaves
    // sum even leave
    // bfs


}
