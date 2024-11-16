
package graph_datastructure.adjacency_matrix;

import java.util.Arrays;

public class MyAdjacencyMatrix {
    private int[][] adjMaxtrix;
    private final int numVertices;
    private boolean isWeighted;
    private static final int INF = Integer.MAX_VALUE;

    // constructor for non-weighted
    public MyAdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.adjMaxtrix = new int[numVertices][numVertices];
        this.isWeighted = false;

        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjMaxtrix[i], 0);
        }
    }

    // constructor for weighted
    public MyAdjacencyMatrix(int numVertices, boolean isWeighted) {
        this.numVertices = numVertices;
        this.isWeighted = isWeighted;
        adjMaxtrix = new int[numVertices][numVertices];

        if (isWeighted) {
            for (int i = 0; i < numVertices; i++) {
                Arrays.fill(adjMaxtrix[i], INF);
            }
        }
    }

    public void addEdgeUndirected(int source, int destination) {
        if (!isWeighted) {
            adjMaxtrix[source][destination] = 1;
            adjMaxtrix[destination][source] = 1;
        }
    }

    public void addEdgeDirected(int source, int destination) {
        if (!isWeighted)
            adjMaxtrix[source][destination] = 1;
    }

    public void addEdgeWeightedUndirected(int source, int destination, int weight) {
        if (isWeighted) {
            adjMaxtrix[source][destination] = weight;
            adjMaxtrix[destination][source] = weight;
        }
    }

    public void addEdgeWeightedDirected(int source, int destination, int weight) {
        if (isWeighted) {
            adjMaxtrix[source][destination] = weight;
        }
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (isWeighted && adjMaxtrix[i][j] == INF) {
                    System.out.print("INF");
                } else {
                    System.out.print(adjMaxtrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MyAdjacencyMatrix unweightedGraph = new MyAdjacencyMatrix(4);

        // Thêm các cạnh không trọng số
        unweightedGraph.addEdgeUndirected(0, 1);
        unweightedGraph.addEdgeUndirected(1, 2);
        unweightedGraph.addEdgeDirected(2, 3);

        // In ma trận kề
        System.out.println("Đồ thị không trọng số:");
        unweightedGraph.printGraph();
    }

}