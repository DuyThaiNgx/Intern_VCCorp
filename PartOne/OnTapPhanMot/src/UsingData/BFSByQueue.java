package UsingData;

import java.util.LinkedList;
import java.util.Queue;

class Graph {
    private int V; // Số đỉnh
    private LinkedList<Integer> adjList[]; // Danh sách kề

    public Graph(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }
    // w là các đỉnh kề của đỉnh v
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    public void BFS(int startVertex) {
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            startVertex = queue.poll();
            System.out.print(startVertex + " ");

            for (Integer neighbor : adjList[startVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}

public class BFSByQueue {
    public static void main(String[] args) {
        Graph graph = new Graph(100);
        graph.addEdge(40, 5);
        graph.addEdge(40, 45);
        graph.addEdge(5, 35);
        graph.addEdge(35, 15);
        graph.addEdge(15, 13);
        graph.addEdge(15, 16);
        graph.addEdge(45, 56);
        graph.addEdge(56, 48);
        graph.addEdge(48, 47);
        graph.addEdge(48, 49);

        System.out.println("Duyệt đồ thị theo BFS từ đỉnh 40:");
        graph.BFS(40);
    }
}

