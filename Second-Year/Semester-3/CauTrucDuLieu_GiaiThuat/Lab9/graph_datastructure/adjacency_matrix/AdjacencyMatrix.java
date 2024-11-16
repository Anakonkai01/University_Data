package graph_datastructure.adjacency_matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrix {
    private int[][] adjMatrix;
    private int vertices;

    public AdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setEdge(int vertexSource, int vertexDestination, int weight) {
        adjMatrix[vertexSource][vertexDestination] = weight;
        adjMatrix[vertexDestination][vertexSource] = weight;
    }

    public void printAdjMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> convertToAL() {
        List<List<Integer>> adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new LinkedList<>());
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j] != 0) {
                    adjList.get(i).add(j);
                }
            }
        }
        return adjList;
    }

    public void printAdjList(List<List<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("Vertex " + i + ": head");
            for (int neighbor : adjList.get(i)) {
                System.out.print(" -> " + neighbor);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(4);

        graph.setEdge(0, 1, 1);
        graph.setEdge(0, 3, 1);
        graph.setEdge(1, 2, 1);
        graph.setEdge(2, 3, 1);

        System.out.println("Adjacency Matrix:");
        graph.printAdjMatrix();

        List<List<Integer>> adjList = graph.convertToAL();
        System.out.println("\nAdjacency List:");
        graph.printAdjList(adjList);
    }
}
