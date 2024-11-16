package Exercise2;

import java.io.*;
import java.util.*;

public class GraphAL {
    private ArrayList<LinkedList<Integer>> adjList;
    private int vertices;

    public GraphAL(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        this.vertices = Integer.parseInt(br.readLine().trim());
        adjList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjList.add(new LinkedList<>());
        }

        String line;
        int vertex = 0;
        while ((line = br.readLine()) != null) {
            String[] neighbors = line.trim().split(" ");
            for (String neighbor : neighbors) {
                adjList.get(vertex).add(Integer.parseInt(neighbor));
            }
            vertex++;
        }
        br.close();
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

    public int countVertices() {
        return vertices;
    }

    public int countEdges() {
        int edgeCount = 0;
        for (LinkedList<Integer> neighbors : adjList) {
            edgeCount += neighbors.size();
        }
        return edgeCount / 2;
    }

    public List<Integer> getNeighbors(int u) {
        return new ArrayList<>(adjList.get(u));
    }

    public boolean hasEdge(int u, int v) {
        return adjList.get(u).contains(v);
    }

    public static void main(String[] args) throws IOException {
        GraphAL graph = new GraphAL("graph.txt");
        graph.printAdjList();
        System.out.println("Number of vertices: " + graph.countVertices());
        System.out.println("Number of edges: " + graph.countEdges());

        int u = 0;
        System.out.println("Neighbors of vertex " + u + ": " + graph.getNeighbors(u));

        int v = 1;
        System.out.println("Edge exists between " + u + " and " + v + ": " + graph.hasEdge(u, v));
    }
}
