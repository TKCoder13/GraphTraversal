import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

public class GraphTraversal {
    
    // -- graph as an adjacency list
    ArrayList adjacencyList[];
    // -- graph as an adjacency matrix data structure
    // public int[][] adjacencyMatrix = {
    //     {0, 1, 0, 0, 0, 0, 0, 0, 0},
    //     {1, 0, 1, 0, 0, 0, 0, 1, 0},
    //     {0, 1, 0, 0, 0, 0, 0, 1, 0},
    //     {0, 0, 0, 0, 0, 0, 0, 1, 1},
    //     {0, 0, 0, 0, 0, 1, 1, 0, 0},
    //     {0, 0, 0, 0, 1, 0, 0, 0, 0},
    //     {0, 0, 0, 0, 1, 0, 0, 1, 0},
    //     {0, 1, 1, 1, 0, 0, 1, 0, 0},
    //     {0, 0, 0, 1, 0, 0, 0, 0, 0}
    // };

    public int[][] a_adjacencyMatrix = {
       /*1  2  3  4  5  6  7 */
        {0, 1, 0, 1, 0, 0, 0},/*1*/
        {1, 0, 0, 0, 1, 0, 1},/*2*/
        {0, 0, 0, 1, 0, 1, 1},/*3*/
        {1, 0, 1, 0, 1, 0, 0},/*4*/
        {0, 1, 0, 1, 0, 1, 1},/*5*/
        {0, 0, 1, 0, 1, 0, 0},/*6*/
        {0, 1, 1, 0, 1, 0, 0} /*7*/
    };

    public int[][] b_adjacencyMatrix = {
       /*1  2  3  4  5  6  7 */
        {0, 0, 0, 1, 1, 0, 0},/*1*/
        {0, 0, 1, 1, 0, 0, 0},/*2*/
        {0, 1, 0, 1, 0, 0, 0},/*3*/
        {1, 1, 1, 0, 0, 0, 1},/*4*/
        {1, 0, 0, 0, 0, 1, 0},/*5*/
        {0, 0, 0, 0, 1, 0, 1},/*6*/
        {0, 0, 0, 1, 0, 1, 0} /*7*/
    };

    public int[][] c_adjacencyMatrix = { // -- directional
        /*1  2  3  4  5  6  7 */
         {0, 1, 0, 1, 1, 0, 0},/*1*/
         {0, 0, 1, 1, 0, 0, 1},/*2*/
         {0, 0, 0, 1, 0, 0, 0},/*3*/
         {0, 0, 0, 0, 0, 1, 1},/*4*/
         {0, 0, 0, 1, 0, 0, 0},/*5*/
         {0, 0, 0, 0, 1, 0, 0},/*6*/
         {0, 0, 0, 0, 0, 1, 0} /*7*/
     };

    public int[][] d_adjacencyMatrix = { // -- directional
        /*1  2  3  4  5  6  7 */
         {0, 1, 0, 1, 1, 0, 0},/*1*/
         {0, 0, 1, 0, 0, 0, 0},/*2*/
         {0, 0, 0, 0, 0, 0, 1},/*3*/
         {0, 1, 1, 0, 0, 1, 1},/*4*/
         {0, 0, 0, 1, 0, 0, 0},/*5*/
         {0, 0, 0, 0, 0, 0, 1},/*6*/
         {0, 0, 0, 0, 0, 0, 0} /*7*/
     };

    public ArrayList[] matrix2List(int[][] matrix) {

        ArrayList[] list = new ArrayList[matrix.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }

        for (int v = 0; v < matrix.length; v++) {
            for (int w = 0; w < matrix[v].length; w++) {
                if (matrix[v][w] == 1) {
                    list[v].add(w);
                }
            }
        }
        for (int v = 0; v < matrix.length; v++) {
            Collections.sort(list[v]);
        }
        return list;
    }

    // -- keep track of nodes that have been visited
    public int[] marks = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    Queue<Integer> queue = new LinkedList<Integer>();

    // -- visitation is just printing the node being visited
    public void visit(int v) {
        System.out.println("Visit: " + v);
    }

    // -- mark node v as visited
    public void mark(int v) {
        marks[v] = 1;
    }

    // -- this is for an adjacency matrix, if you have an adj list, pass that in instead
    // -- perform the Depth First Traversal
    public void DepthFirstTraversal(int[][] graph, int v) {
        // -- visit node v
        visit(v);
        // -- visit node v as visited
        mark(v);
        // -- for all edges vw in graph
        for (int w = 0; w < graph[v].length; w++) {
        // -- this processes largest node first
        // -- for (int w = graph[v].length - 1; w >= 0; w--) {} 

            // check if an edge exists at (v,w)
            if (graph[v][w] == 1) {
                // -- if w is not marked
                if (marks[w] == 0) {
                    // -- recursive call from node w
                    DepthFirstTraversal(graph, w);
                }
            }
        }
    }

    // -- perform the Depth First Traversal using an adjList
    public void DepthFirstTraversal(ArrayList[] graph, int v) {
        // -- visit node v
        visit(v);
        // -- visit node v as visited
        mark(v);
        // -- for all edges vw in graph
        for (int i = 0; i < graph[v].size(); i++) {
            int w = (int) graph[v].get(i);
        // -- this is for greatest to least
        // -- for (int w = graph[v].size - 1; w >= 0; w--) {} 
            // -- if w is not marked
            if (marks[w] == 0) {
                // -- recursive call from node w
                DepthFirstTraversal(graph, w);
            }
        }
    }

    public void enqueue(int v) {
        queue.add(v);
    }

    public void dequeue() {
        queue.poll();
    }
    public void BreadthFirstTraversal(int[][] graph, int v) {
        visit(v);
        mark(v);
        enqueue(v);
        while (queue.peek() != null) {
            dequeue();
            for (int w = 0; w < graph.length; w++) {
                if (marks[w] == 0) {
                    visit(w);
                    mark(w);
                    enqueue(w);
                }
            }
        }
    }

    public void BreadthFirstTraversal(ArrayList[] graph, int v) {
        visit(v);
        mark(v);
        enqueue(v);
        while (queue.peek() != null) {
            dequeue();
            for (int w = 0; w < graph[v].size(); w++) {
                if (marks[w] == 0) {
                    visit(w);
                    mark(w);
                    enqueue(w);
                }
            }
        }
    }
    public static void main(String args[]) {
        GraphTraversal traversal = new GraphTraversal();
        traversal.adjacencyList = traversal.matrix2List(traversal.a_adjacencyMatrix);
        traversal.BreadthFirstTraversal(traversal.adjacencyList, 0);
    }
}