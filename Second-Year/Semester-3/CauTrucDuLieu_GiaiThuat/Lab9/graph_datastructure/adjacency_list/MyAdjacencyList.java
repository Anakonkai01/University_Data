package graph_datastructure.adjacency_list;

import java.util.ArrayList;
import java.util.List;

public class MyAdjacencyList {
    private List<List<Integer>> adjListUnweighted;
    private List<List<Edge>> adjListWeighted;
    private boolean isWeighted;

    private static class Edge{
        int destination;
        int weight;
        public  Edge(int destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }
    }

    public MyAdjacencyList(int numVertices){
        isWeighted = false;
        adjListUnweighted = new ArrayList<>();
        for(int i = 0; i < numVertices; i++){
            adjListUnweighted.add(new ArrayList<>());
        }
    }

    public MyAdjacencyList(int numVertices, boolean isWeighted){
        this.isWeighted = isWeighted;
        if(isWeighted == true){
            adjListWeighted = new ArrayList<>();
            for(int i = 0; i < numVertices; i++){
                adjListWeighted.add(new ArrayList<>());
            }
        }
    }


    public void addEdgeUnweightedUndirected(int source, int destination){
        if(!isWeighted){
            adjListUnweighted.get(source).add(destination);
            adjListUnweighted.get(destination).add(source);
        }
    }

}
