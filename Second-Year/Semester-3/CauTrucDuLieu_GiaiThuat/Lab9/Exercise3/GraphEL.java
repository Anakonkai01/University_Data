package Exercise3;

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
        return "Edge: (" + source + " -> " + dest + ", weight: " + weight + ")";
    }
}

public class GraphEL {
    private List<IntegerTriple> edges;
    private int vertices;

    public GraphEL(String filename) throws IOException {
        edges = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        this.vertices = Integer.parseInt(br.readLine().trim()); // Số lượng đỉnh

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            int weight = Integer.parseInt(parts[0]);
            int source = Integer.parseInt(parts[1]);
            int dest = Integer.parseInt(parts[2]);
            edges.add(new IntegerTriple(weight, source, dest));
        }
        br.close();
    }

    public void printEdgeList() {
        for (IntegerTriple edge : edges) {
            System.out.println(edge);
        }
    }

    public int countVertices() {
        return vertices;
    }

    public int countEdges() {
        return edges.size();
    }

    public List<Integer> getNeighbors(int u) {
        List<Integer> neighbors = new ArrayList<>();
        for (IntegerTriple edge : edges) {
            if (edge.getSource() == u) {
                neighbors.add(edge.getDest());
            }
        }
        return neighbors;
    }

    public boolean hasEdge(int u, int v) {
        for (IntegerTriple edge : edges) {
            if (edge.getSource() == u && edge.getDest() == v) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        GraphEL graph = new GraphEL("graph_el.txt");
        graph.printEdgeList();
        System.out.println("Number of vertices: " + graph.countVertices());
        System.out.println("Number of edges: " + graph.countEdges());

        int u = 0;
        System.out.println("Neighbors of vertex " + u + ": " + graph.getNeighbors(u));

        int v = 1;
        System.out.println("Edge exists between " + u + " and " + v + ": " + graph.hasEdge(u, v));
    }
}
