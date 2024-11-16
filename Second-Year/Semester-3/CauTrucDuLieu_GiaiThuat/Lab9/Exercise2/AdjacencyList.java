package Lab9.Exercise2;

import java.util.*;
import java.io.*;

public class AdjacencyList {
    private int V; // Số đỉnh
    private ArrayList<LinkedList<Integer>> adj;

    // Constructor để khởi tạo đồ thị
    public AdjacencyList(int v) {
        V = v;
        adj = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < V; ++i)
            adj.add(new LinkedList<Integer>());
    }

    // Phương thức thêm cạnh
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // Vì đây là đồ thị vô hướng
    }

    // Đọc đồ thị từ file và tạo danh sách kề
    public static AdjacencyList readGraphFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int vertices = Integer.parseInt(reader.readLine().trim());
        AdjacencyList graph = new AdjacencyList(vertices);

        String line;
        int vertex = 0;
        while ((line = reader.readLine()) != null) {
            String[] neighbors = line.trim().split(" ");
            for (int i = 1; i < neighbors.length; i++) {
                int neighbor = Integer.parseInt(neighbors[i]);
                graph.addEdge(vertex, neighbor);
            }
            vertex++;
        }
        reader.close();
        return graph;
    }

    // In danh sách kề của đồ thị
    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print("Vertex " + i + ": ");
            System.out.print("head");
            for (Integer v : adj.get(i)) {
                System.out.print(" -> " + v);
            }
            System.out.println();
        }
    }


    // b
    public int getNumVertices() {
        return V;
    }

    // c
    public int countEdges() {
        int count = 0;
        for (List<Integer> neighbors : adj) {
            count += neighbors.size();
        }
        return count / 2;
    }


    // d
    public void neighbors(int u) {
        System.out.print("Neighbors of vertex " + u + ": ");
        for (int neighbor : adj.get(u)) {
            System.out.print(neighbor + ", ");
        }
        System.out.println();
    }


    // e
    public boolean hasEdge(int u, int v) {
        if (u >= 0 && u < V && v >= 0 && v < V) {
            return adj.get(u).contains(v);
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            // (a) Đọc đồ thị từ file
            AdjacencyList graph = AdjacencyList.readGraphFromFile("Exercise2/graph.txt");

            // (b) In danh sách kề
            System.out.println("Adjacency List:");
            graph.printGraph();

            // (c) Đếm số đỉnh
            System.out.println("Number of vertices: " + graph.V);

            // (d) Đếm số cạnh
            System.out.println("Number of edges: " + graph.countEdges());

            // (e) Liệt kê các đỉnh kề của một đỉnh (ví dụ: đỉnh 2)
            int u = 2;
//            System.out.println("Neighbors of vertex " + u + ": " + graph.getNeighbors(u));

            // (f) Kiểm tra sự tồn tại của cạnh (ví dụ: cạnh giữa 2 và 4)
            int v = 4;
            System.out.println("Edge exists between " + u + " and " + v + ": " + graph.hasEdge(u, v));
        } catch (IOException e) {
            System.out.println("Error reading graph from file: " + e.getMessage());
        }
    }


}
