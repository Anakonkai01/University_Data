package graph_datastructure.adjacency_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyList {
    private List<LinkedList<Integer>> adjList;
    private int vertices;

    public AdjacencyList(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new LinkedList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }

    public void printAdjList() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": head");
            for (int neighbor : adjList.get(i)) {
                System.out.print(" -> " + neighbor);
            }
            System.out.println();
        }
    }
}
