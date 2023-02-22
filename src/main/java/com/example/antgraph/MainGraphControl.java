package com.example.antgraph;
import java.io.*;
import java.util.*;


public class MainGraphControl {
    File fil;
    private int V;
    private LinkedList<Integer> adj[];


    MainGraphControl(int v)
    {
        fil = new File("src\\main\\java\\com\\example\\antgraph\\LocalDB\\GraphDB_Local.txt");

        try {

            if(fil.createNewFile()){
                System.out.println("FIle Initialize");
            }
        }catch (Exception e){}
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(w);
    }
    ArrayList<Integer> DFS(int s)
    {
        ArrayList<Integer> Ouptput = new ArrayList<Integer>();

        Vector<Boolean> visited = new Vector<Boolean>(V);
        for (int i = 0; i < V; i++)
            visited.add(false);
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while(stack.empty() == false)
        {
            s = stack.peek();
            stack.pop();


            if(visited.get(s) == false)
            {
                Ouptput.add(s);
                visited.set(s, true);
            }
            Iterator<Integer> itr = adj[s].iterator();

            while (itr.hasNext())
            {
                int v = itr.next();
                if(!visited.get(v))
                    stack.push(v);
            }

        }
        return  Ouptput;
    }


    ArrayList<Integer> BFS(int s)
    {

        ArrayList<Integer> Ouptput = new ArrayList<Integer>();

        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            Ouptput.add(s);
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        return Ouptput;
    }


void LoadGraph() {

    int f, s;
    try {

      Scanner filerea = new Scanner(fil);
      while (filerea.hasNext()) {
                f = filerea.nextInt();
                s = filerea.nextInt();
                addEdge(f,s);
     }
     } catch (Exception e){}

    }

    void Joint(int x, int y) {
        int f, s;
        try {

            Scanner filerea = new Scanner(fil);
            while (filerea.hasNext()) {
                f = filerea.nextInt();
                s = filerea.nextInt();
                if(f==x && s == y  || f==y && y == s ) return;
            }

        } catch (Exception e) {}


    try{
        FileWriter fr = new FileWriter(fil,true);
        fr.write(x+" "+y+"\n");
        fr.close();
    }catch (Exception e){}
    }


}


