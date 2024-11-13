package Lab9.graph_datastructure.Teacher;

import Lab9.graph_datastructure.Teacher.AdjacencyMatrix;

import java.io.*;

public class TestGraph 
{
    AdjacencyMatrix a;

    //Doc file 
    public TestGraph(String filename)
    {
        try
        {
            FileReader fr=new FileReader(filename);    
            BufferedReader br=new BufferedReader(fr); 
            String s = br.readLine();
            a = new AdjacencyMatrix(Integer.parseInt(s));
            
            String[] t;
            for(int i = 0; i<a.NUMBER_OF_VERTICES; ++i)
            {
                s = br.readLine();
                t = s.split(" ");
                for(int j = 0; j< t.length; ++j)
                    a.setEgde(i, j, Integer.parseInt(t[j]));;
            }
            br.close();
            fr.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
       
    }

    // cau a
    public int countEdges()
    {
        int count = 0;
        for(int i = 0; i<a.NUMBER_OF_VERTICES; ++i)
            for(int j = i; j<a.NUMBER_OF_VERTICES; ++j)
                if(a.adj[i][j] == 1)
                    ++count;
        return count;
    }

    // cau b
    public void neighbors ( int u)
    {
        for(int j = 0; j<a.NUMBER_OF_VERTICES; ++j)
            if (a.adj[u][j] == 1)
                System.out.print(j + ", ");
    }

    // cau c
    public boolean isExist(int u, int v)
    {
        if (a.adj[u][v] == 1)
            return true;
        return false;
    }
    public static void main(String[] arg)
    {
        TestGraph t = new TestGraph("graph.txt");
        System.out.println("Number of vertices: " + t.a.NUMBER_OF_VERTICES);
        System.out.println("Number of edges: " + t.countEdges());
        int u = 1;
        System.out.print("Neighbors of " + u + " is: ");

        t.neighbors(u);
        System.out.println();
        u = 1;
        int v = 2;
        System.out.println("Existence between " + u + " and " + v + ": " + t.isExist(u, v));
    }
}
