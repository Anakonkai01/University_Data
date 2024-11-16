package Lab9.Exercise3;

import java.io.*;
import java.util.*;

class IntegerTriple {
    private Integer weight;
    private Integer source;
    private Integer dest;

    public IntegerTriple(Integer w, Integer s, Integer d) {
        weight = w;
        source = s;
        dest = d;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return weight + " " + source + " " + dest;
    }
}

public class EdgeList {
    private Vector<IntegerTriple> edges;
    private int numVertices; // Khai báo biến numVertices để lưu số lượng đỉnh

    public EdgeList(int vertices) {
        edges = new Vector<>();
        this.numVertices = vertices; // Gán số lượng đỉnh cho numVertices
    }

    public void addEdge(int w, int u, int v) {
        edges.add(new IntegerTriple(w, u, v));
    }

    // (a) Đọc đồ thị từ file
    public static EdgeList readGraphFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int vertices = Integer.parseInt(reader.readLine().trim()); // Số đỉnh
        EdgeList graph = new EdgeList(vertices);

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            int weight = Integer.parseInt(parts[0]);
            int u = Integer.parseInt(parts[1]);
            int v = Integer.parseInt(parts[2]);
            graph.addEdge(weight, u, v);
        }
        reader.close();
        return graph;
    }

    // (b) In danh sách cạnh (Edge List)
    public void printGraph() {
        for (IntegerTriple edge : edges) {
            System.out.println(edge);
        }
    }

    // (c) Đếm số đỉnh
    public int getNumVertices() {
        return numVertices;
    }

    // (d) Đếm số cạnh
    public int countEdges() {
        return edges.size();
    }

    // (e) Liệt kê các đỉnh kề của một đỉnh u
    public List<Integer> getNeighbors(int u) {
        List<Integer> neighbors = new ArrayList<>();
        for (IntegerTriple edge : edges) {
            if (edge.getSource().equals(u)) {
                neighbors.add(edge.getDest());
            } else if (edge.getDest().equals(u)) {
                neighbors.add(edge.getSource());
            }
        }
        return neighbors;
    }

    // (f) Kiểm tra sự tồn tại của cạnh giữa hai đỉnh
    public boolean hasEdge(int u, int v) {
        for (IntegerTriple edge : edges) {
            if ((edge.getSource().equals(u) && edge.getDest().equals(v)) ||
                    (edge.getSource().equals(v) && edge.getDest().equals(u))) {
                return true;
            }
        }
        return false;
    }
}
