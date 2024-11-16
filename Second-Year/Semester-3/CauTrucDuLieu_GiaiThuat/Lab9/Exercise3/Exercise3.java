package Lab9.Exercise3;

import java.io.IOException;
import java.util.List;

public class Exercise3 {
    public static void main(String[] args) {
        try {
            // (a) Đọc đồ thị từ file
            EdgeList graph = EdgeList.readGraphFromFile("graph.txt");

            // (b) In danh sách cạnh
            System.out.println("Edge List:");
            graph.printGraph();

            // (c) Đếm số đỉnh
            System.out.println("Number of vertices: " + graph.getNumVertices());

            // (d) Đếm số cạnh
            System.out.println("Number of edges: " + graph.countEdges());

            // (e) Liệt kê các đỉnh kề của một đỉnh (ví dụ: đỉnh 2)
            int u = 2;
            List<Integer> neighbors = graph.getNeighbors(u);
            System.out.print("Neighbors of vertex " + u + ": ");
            for (int neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();

            // (f) Kiểm tra sự tồn tại của cạnh (ví dụ: cạnh giữa 2 và 3)
            int v = 3;
            System.out.println("Edge exists between " + u + " and " + v + ": " + graph.hasEdge(u, v));
        } catch (IOException e) {
            System.out.println("Error reading graph from file: " + e.getMessage());
        }
    }
}
