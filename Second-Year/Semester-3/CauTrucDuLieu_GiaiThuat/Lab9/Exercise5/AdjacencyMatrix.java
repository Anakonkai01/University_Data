import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyMatrix {
    private int[][] adjMatrix;
    private int vertices;

    public AdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
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

    public void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[v][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal (Recursive): ");
        DFS_recursive(start, visited);
        System.out.println();
    }

    private void DFS_recursive(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[v][i] != 0 && !visited[i]) {
                DFS_recursive(i, visited);
            }
        }
    }

    public void DFS_nonRecursive(int start) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        System.out.print("DFS Traversal (Non-Recursive): ");
        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited[v]) {
                System.out.print(v + " ");
                visited[v] = true;
            }

            for (int i = vertices - 1; i >= 0; i--) {
                if (adjMatrix[v][i] != 0 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
        System.out.println();
    }

    public boolean isReachable(int u, int v) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[u] = true;
        queue.add(u);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            if (vertex == v) {
                return true;
            }

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[vertex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return false;
    }
}
