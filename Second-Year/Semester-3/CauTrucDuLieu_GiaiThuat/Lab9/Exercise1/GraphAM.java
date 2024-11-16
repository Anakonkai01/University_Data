import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphAM {
    private int[][] adjMatrix;
    private int vertices;

    public GraphAM(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        this.vertices = Integer.parseInt(br.readLine().trim());
        adjMatrix = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            String[] line = br.readLine().trim().split(" ");
            for (int j = 0; j < vertices; j++) {
                adjMatrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        br.close();
    }

    public void printAdjMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int countVertices() {
        return vertices;
    }

    public int countEdges() {
        int edgeCount = 0;
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (adjMatrix[i][j] != 0) {
                    edgeCount++;
                }
            }
        }
        return edgeCount;
    }

    public List<Integer> getNeighbors(int u) {
        List<Integer> neighbors = new ArrayList<>();
        for (int v = 0; v < vertices; v++) {
            if (adjMatrix[u][v] != 0) {
                neighbors.add(v);
            }
        }
        return neighbors;
    }

    public boolean hasEdge(int u, int v) {
        return adjMatrix[u][v] != 0;
    }

    public static void main(String[] args) throws IOException {
        GraphAM graph = new GraphAM("graph.txt");
        graph.printAdjMatrix();
        System.out.println("Number of vertices: " + graph.countVertices());
        System.out.println("Number of edges: " + graph.countEdges());
        
        int u = 0;
        System.out.println("Neighbors of vertex " + u + ": " + graph.getNeighbors(u));

        int v = 1;
        System.out.println("Edge exists between " + u + " and " + v + ": " + graph.hasEdge(u, v));
    }
}
