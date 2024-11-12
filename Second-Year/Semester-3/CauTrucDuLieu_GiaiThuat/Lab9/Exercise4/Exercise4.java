package Exercise4;

import graph_datastructure.adjacency_list.AdjacencyList;
import graph_datastructure.adjacency_matrix.AdjacencyMatrix;

public class Exercise4 {
    public static AdjacencyList convert(AdjacencyMatrix am) {
        int vertices = am.getVertices();
        AdjacencyList adjList = new AdjacencyList(vertices);

        int[][] matrix = am.getAdjMatrix();
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrix[i][j] != 0) { // Nếu có cạnh từ i đến j
                    adjList.addEdge(i, j);
                }
            }
        }
        return adjList;
    }

    public static void main(String[] args) {
        AdjacencyMatrix graphAM = new AdjacencyMatrix(4);

        // Thiết lập cạnh cho ma trận kề
        graphAM.setEdge(0, 1, 1);
        graphAM.setEdge(0, 3, 1);
        graphAM.setEdge(1, 2, 1);
        graphAM.setEdge(2, 3, 1);

        System.out.println("Adjacency Matrix:");
        graphAM.printAdjMatrix();

        // Sử dụng phương thức convert() để chuyển đổi từ AM sang AL
        AdjacencyList graphAL = Exercise4.convert(graphAM);

        System.out.println("\nAdjacency List:");
        graphAL.printAdjList();
    }
}
