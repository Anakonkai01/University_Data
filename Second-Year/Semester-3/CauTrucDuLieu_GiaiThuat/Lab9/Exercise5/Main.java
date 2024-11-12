public class Main {
    public static void main(String[] args) {
        AdjacencyMatrix graphAM = new AdjacencyMatrix(4);

        graphAM.setEdge(0, 1, 1);
        graphAM.setEdge(0, 3, 1);
        graphAM.setEdge(1, 2, 1);
        graphAM.setEdge(2, 3, 1);

        System.out.println("Adjacency Matrix:");
        graphAM.printAdjMatrix();

        graphAM.BFS(0);

        graphAM.DFS(0);

        graphAM.DFS_nonRecursive(0);

        int u = 0, v = 3;
        System.out.println("Is vertex " + v + " reachable from vertex " + u + ": " + graphAM.isReachable(u, v));
    }
}

