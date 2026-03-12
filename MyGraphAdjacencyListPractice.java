import java.util.*;

public class MyGraphAdjacencyListPractice {

    // over all time complexity of union find in O(α(n)) nearly equal to O(4) = O(1)  constant
    static class DisjointSetUnion {
        int[] parent;
        int[] rank;

        DisjointSetUnion(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < parent.length; i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x){
            if(x == parent[x]) return x;
            //path compression
            return parent[x] = find(parent[x]);
        }

        public void union(int a, int b){
            int parA = find(a);
            int parB = find(b);

            //if parA == parB -> cycle detection 

            if(parA != parB) {
                //union by rank
                if(rank[parA] == rank[parB]){
                    parent[parB] = parA;
                    rank[parA]++;
                } else if (rank[parA] > rank[parB]){
                    parent[parB] = parA;
                } else {
                    parent[parA] = parB;
                }
            }
        }
    }

    static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p){
            return this.dist - p.dist;
        }
    }

    static class Edge implements Comparable<Edge>{
        int source;
        int destination;
        int weight; 

        Edge(int s, int d, int w){
            this.destination = d;
            this.source = s;
            this.weight = w;
        }

        Edge(int s, int d){
            this.destination = d;
            this.source = s;
            this.weight = 1;
        }

        @Override
        public int compareTo(Edge p){
            return this.weight - p.weight;
        }
    }
    
    static class MyAdjacencyListGraph{
        int vertices;
        List<Edge>[] adj;

        @SuppressWarnings("unchecked")
        MyAdjacencyListGraph(int vertices){
            this.vertices = vertices;
            adj = new List[vertices];

            for(int i = 0; i < vertices; i++){
                adj[i] = new ArrayList<>();
            }
        }
    }

     //Time Complexity = O(V+E) (maximum of vertices, edges) -> for both BFS and DFS
    //for a matrix list - Time Compexity = O(V^2)
    //BFS for adjacency list graph
    public static void breathFirstSearch(MyAdjacencyListGraph graph){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.vertices];

        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){

            int node = queue.poll();
            System.out.print(node + " ");

            for(Edge e: graph.adj[node]){

                int nbr = e.destination;

                if(!visited[nbr]){
                    visited[nbr] = true;
                    queue.add(nbr);
                }
            }
        }
    }
    //DFS for adjacency list graph 
    public static void depthFirstSearch(MyAdjacencyListGraph graph, int i, boolean[] visited){
        //base case
        if(i >= graph.vertices) { // kinda useless satement because unlike trees we have a count of total vertices so we will technically never go out of bounds.
            return;
        }

        System.out.print(i + " ");
        visited[i] = true;

        List<Edge> l = graph.adj[i];
        for(Edge e : l){
            int nbr = e.destination;
            if(!visited[nbr]){
                depthFirstSearch(graph, nbr, visited);
            }
        }
    }
    //DFS for unconnected graphs
    public static void fullDepthFirstSearch(MyAdjacencyListGraph graph){
        boolean[] visited = new boolean[graph.adj.length];
        for(int i = 0; i < graph.adj.length; i++){
            if(!visited[i]){
                depthFirstSearch(graph, i, visited);
            }
        }
        return;
    }
    //BFS also works in the same way

    //has Path
    public static boolean hasPath(MyAdjacencyListGraph graph, int source, int destination, boolean[] visited){
        //base case
        if(source >= graph.adj.length){ // kinda useless satement because unlike trees we have a count of total vertices so we will technically never go out of bounds.
            return false;
        }
        if(source == destination){
            return true;
        }

        List<Edge> l = graph.adj[source];
        visited[source] = true;
        for(Edge e: l){
            int nbr = e.destination;
            if(!visited[nbr] && hasPath(graph, nbr, destination, visited)){
                return true;
            }
        }
        return false;
    }
    
    //cycle detection in undirected graphs using DFS
    public static boolean helperIsCycleDFS(MyAdjacencyListGraph graph, int i, int prev, boolean[] visited){
        visited[i] = true;

        for(Edge e : graph.adj[i]){
            int nbr = e.destination;
            if(!visited[nbr]){ //continue normal DFS if not visited
                if(helperIsCycleDFS(graph, nbr, i, visited)){
                    return true;
                }
            } else if(nbr != prev){ //retrun true if a cycle is found - extra check in undirected graph because there are two edges recodrs for each edge
                return true;
            }
        }
        return false; // return false if we reached the end without any problem
    }
    public static boolean isCycle(int V, MyAdjacencyListGraph graph) {
        // Code here
        boolean[] visited = new boolean[graph.adj.length];
        for (int i = 0; i < graph.adj.length; i++){
            if(helperIsCycleDFS(graph, i, -1, visited)){
                return true;
            }
        }
        return false;
    }
    //cycle detection in undirected graphs using BFS
    public static boolean helperIsCycleBFS(MyAdjacencyListGraph graph, int i, boolean[] visited ){

        int[] parent = new int[graph.adj.length];
        Arrays.fill(parent, -1);

        Queue<Integer> q  = new ArrayDeque<>();
        q.add(i);
        visited[i] = true;
        while(!q.isEmpty()) {
            int curr = q.poll()  ;
            
            for(Edge e: graph.adj[curr]){
                int nbr = e.destination;
                if(!visited[nbr]){
                    visited[nbr] = true;
                    parent[nbr] = curr;
                    q.add(nbr);
                } else if(nbr != parent[curr]){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCycleBFS(MyAdjacencyListGraph graph){
        boolean[] visited = new boolean[graph.adj.length];
        for(int i = 0; i < graph.adj.length; i++){
            if(!visited[i]){
                if(helperIsCycleBFS(graph, i, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    //isBipartit graph - u, v set and all the edges have their start and end point NOT in the same set
    public static boolean isBipartiteColor(MyAdjacencyListGraph graph){
        int[] colors = new int[graph.adj.length]; // 0 - no color
        
        for(int i = 0; i < graph.adj.length;i++){
            if(colors[i] == 0){
                Queue<Integer> pq = new ArrayDeque<>();
                pq.add(i);
                colors[i] = 1; //blue

                while (!pq.isEmpty()) {
                    int curr = pq.poll();
                    int currColor = colors[curr];

                    List<Edge> l = graph.adj[curr];
                    for(Edge e: l){
                        int nbr = e.destination;
                        if(colors[nbr] == 0) {
                            if(currColor == 1){
                             colors[nbr] = 2; // orange
                            } else {
                                colors[nbr] = 1; //blue
                            }
                            pq.add(nbr);
                        } else if(colors[nbr] == currColor){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static boolean isBipartiteLength(MyAdjacencyListGraph graph){
        int[] lengths = new int[graph.adj.length];
        for(int i = 0; i < graph.adj.length;i++){
            if(lengths[i] == 0){
                Queue<Integer> pq = new ArrayDeque<>();
                pq.add(i);
                lengths[i] = 1;

                while (!pq.isEmpty()) {
                    int curr = pq.poll();
                    
                    List<Edge> l = graph.adj[curr];
                    for(Edge e: l){
                        int nbr = e.destination;
                        if(lengths[nbr] == 0) {
                            lengths[nbr] = lengths[curr]+1;
                            pq.add(nbr);
                        } else if((lengths[curr] - lengths[nbr])%2 == 0){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //cycle detection in directed graph
    //use dfs
    public static boolean helperIsCycleDirected(MyAdjacencyListGraph graph, int start, boolean[] visited, boolean[] stack){
        visited[start] = true;
        stack[start] = true;
        for(Edge e: graph.adj[start]) {
            int nbr = e.destination;
            
            if(!visited[nbr]){
                if(helperIsCycleDirected(graph, nbr, visited, stack)){
                    return true;
                }
            }
            else if(stack[nbr]){
                return true;
            }
        }
        stack[start] = false;
        return false;
    }
    public static boolean isCycleDirected(MyAdjacencyListGraph graph){
        boolean[] visited = new boolean[graph.adj.length];
        boolean[] stack = new boolean[graph.adj.length];
        for(int i = 0; i < graph.adj.length; i++){
            if(!visited[i] && helperIsCycleDirected(graph, i, visited, stack)){
                return true;
            }
        }
        return false;
    }

    //Topological sorting with DFS
    public static void helperTopologicalSortingDFS(MyAdjacencyListGraph graph, int start, boolean[] visited, Deque<Integer> stack){
        visited[start] = true;
        int nbr;
        for(Edge e: graph.adj[start]){
            nbr = e.destination;
            if(!visited[nbr]){
                helperTopologicalSortingDFS(graph, nbr, visited, stack);
            }
        }
        stack.addFirst(start);
    }
    public static void topologicalSortingDFS(MyAdjacencyListGraph graph){
        int n = graph.adj.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                helperTopologicalSortingDFS(graph, i, visited, stack);
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.poll() + " ");
        }
    }
    //Topological sorting with BFS
    //here we don't even need a visited array - it is clean and effecient as it is
    public static int[] inDegree(MyAdjacencyListGraph graph){
        int[] arr = new int[graph.adj.length];
        for(List<Edge> l : graph.adj){
            for(Edge e: l){
                arr[e.destination]++;
            }
        }
        return arr;
    }
    public static void topologicalSortingBFS(MyAdjacencyListGraph graph){
        int[] indeg = inDegree(graph);
        Queue<Integer> que = new ArrayDeque<>();
        int count = 0;
        for(int i = 0; i < indeg.length; i++){
            if(indeg[i]==0){
                que.add(i);
            }
        }
        while(!que.isEmpty()){
            int curr = que.poll();
            System.out.println(curr);
            count++;
            for(Edge e: graph.adj[curr]){
                int nbr =e.destination;
                indeg[nbr]--;
                if(indeg[nbr] == 0){
                    que.add(nbr);
                }
            }
        }
        if(count!=graph.adj.length){
            System.out.println("cycle detected");
        }
    }
    
    //all paths from source to destination
    public static void allPathsHelper(MyAdjacencyListGraph graph, int source, int destination, boolean[] visited, List<Integer> path, List<List<Integer>> paths){
        visited[source] = true;
        path.add(source);

        if(source == destination){
            paths.add(new ArrayList<>(path));
        } else {
            for(Edge e : graph.adj[source]){
                int nbr = e.destination;
                if(!visited[nbr]){
                    allPathsHelper(graph, nbr, destination, visited, path, paths);
                }
            }
        }

        visited[source] = false;
        path.remove(path.size()-1);
    }
    public static List<List<Integer>> allPaths(MyAdjacencyListGraph graph, int source, int destination){
        List<List<Integer>> paths = new ArrayList<>();
        boolean[] visited = new boolean[graph.adj.length];
        List<Integer> path = new ArrayList<>();

        allPathsHelper(graph, source, destination, visited, path, paths);
        return paths;
    }
    
    //Dijkstra's algorithm | TC- O(E log(V)) - very effecient
    public static void dijkstra(MyAdjacencyListGraph graph, int source){
        int[] distance = new int[graph.adj.length];
        for(int i = 0; i < distance.length; i++){
            if(i!=source){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        //this is optimization no 2
        boolean[] visited = new boolean[graph.adj.length];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));

        //loop
        while(!pq.isEmpty()){
            Pair curr = pq.poll();

            //this is optimixation no 1
            if (curr.dist > distance[curr.node]) {
                continue;
            }
            //we can use either 1st or 2nd optimization. optimization no 1 is preffered. no point in using both of them

            // 2nd optimization
            if(!visited[curr.node]){
               visited[curr.node] = true; 

                for(Edge e: graph.adj[curr.node]){
                    int nbr = e.destination;
                    int wt = e.weight;

                    if(distance[curr.node] + wt < distance[nbr]){
                        distance[nbr] = distance[curr.node] + wt;
                        pq.add(new Pair(nbr, distance[nbr]));
                    }
                }
            }
        }

        for(int i = 0; i < distance.length; i++){
            System.out.println(i + "->" + distance[i] + " ");
        }
    }

    //bellman Ford algorithm
    public static void bellmanFord(MyAdjacencyListGraph graph, int source){
        //initializing
        int[] dist = new int[graph.adj.length];
        for(int i = 0; i<dist.length; i++){
            if(i != source){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        int V = graph.adj.length;
        //algorithm - O(V-1) = O(V)
        for(int i = 0; i < V-1; i++){

            boolean updated = false;

            //edges - O(E)
            for(int j = 0; j<graph.adj.length; j++ ){
                for(int k = 0; k < graph.adj[j].size(); k++){
                    Edge e = graph.adj[j].get(k);

                    //Relaxation
                    int u = e.source, v = e.destination, wt = e.weight;
                    if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]){ // dist[u] != Integer.MAX_VALUE -> in maths any int i + ∞ = ∞. but here we have an int limit. so we add this as warning to not try and add anything to Integer.MAX_VALUE.
                        dist[v] = dist[u] + wt;
                        updated = true;
                    }
                }
            }
            //optimization
            if(!updated) break;
        }

        //cycle detection
        for(int j = 0; j<graph.adj.length; j++ ){
            for(int k = 0; k < graph.adj[j].size(); k++){
                Edge e = graph.adj[j].get(k);

                //if Relaxation still happens after we already updated values V-1 times then this means we have encountered a negative cycle
                int u = e.source, v = e.destination, wt = e.weight;
                if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]){ // dist[u] != Integer.MAX_VALUE -> in maths any int i + ∞ = ∞. but here we have an int limit. so we add this as warning to not try and add anything to Integer.MAX_VALUE.
                    System.out.println("Negative Cycle detected");
                }
            }
        }
        //printing solutions
        for(int i = 0; i <  dist.length; i++) System.out.println(i+"->"+dist[i]);
    }

    //Prim's algorithm
    //minimum spanning tree
    public static int Prims(MyAdjacencyListGraph graph){
        boolean[] visited = new boolean[graph.adj.length];
        PriorityQueue<Pair>pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        int totalCost = 0;
        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            if(!visited[curr.node]){
                visited[curr.node] = true;
                totalCost += curr.dist;
                for(Edge e: graph.adj[curr.node]){
                    int nbr = e.destination;
                    int wt = e.weight;
                    pq.add(new Pair(nbr, wt));
                }
            }
        }
        return totalCost;
    }
    
    // krushkal's algorithm
    // minimum spanning tree  
    // used on an edge list
    public static int krushkals(ArrayList<Edge> edges, int V){
        Collections.sort(edges);
        DisjointSetUnion du = new DisjointSetUnion(V);
        int finalCost = 0;
        int count = 0;
        for(int i = 0; i < edges.size(); i++){
            if(count == V-1) break;
            Edge curr = edges.get(i);
            int u = curr.source;
            int v = curr.destination;
            int cost = curr.weight;
            if(du.find(u) != du.find(v)){
                count++;
                du.union(u, v);
                finalCost += cost;
            }
        }
        return finalCost;
    }
    
    //trajan's algorithm for SCC in directed graphs
    //tin[] - discovery  time, used to find ancestors in a graph
    //low[] lowest discovery time, the lowest ancestor(discovery time) that can be reached by a vertice. except for its immediate neighbours in an undirected graph
    private static int timer = 0;
    public static void trajanDFS(int node, MyAdjacencyListGraph graph, boolean[] inStack, int[] tin, int[] low, ArrayDeque<Integer> s, List<List<Integer>> SCC){
        inStack[node] = true;
        s.add(node);
        tin[node] = low[node] = ++timer;

        for(Edge e: graph.adj[node]){
            if(tin[e.destination] == -1){
                trajanDFS(e.destination, graph, inStack, tin, low, s, SCC);
                low[node] = Math.min(low[node], low[e.destination]);
            } else if(inStack[e.destination]){
                low[node] = Math.min(low[node], tin[e.destination]);
            }
        }
        if(low[node] == tin[node]){
            List<Integer> temp = new ArrayList<>();
            while(true){
                int curr = s.removeLast(); 
                inStack[curr] = false;
                temp.add(curr);
                if(curr == node) break;
            }
            SCC.add(temp);
        }
    }
    public static List<List<Integer>> trajansScc (MyAdjacencyListGraph graph){
        int V = graph.adj.length;
        List<List<Integer>> SCC = new ArrayList<>();

        boolean[] inStack = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];

        Arrays.fill(tin, -1);

        timer = 0;

        ArrayDeque<Integer> s = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if(tin[i] == -1){
                trajanDFS(i, graph, inStack, tin, low, s, SCC);
            }
        }
        return SCC;
    }
    //trajan's algrithm for bridges in undirected graphs
    public static void trajanBridgesDFS (int node, int parent, MyAdjacencyListGraph graph, boolean[] visited, int[] tin, int[]low, List<List<Integer>> bridges){
        visited[node] = true;
        tin[node] = low[node] = ++timer;

        for(Edge e: graph.adj[node]){
            int nbr = e.destination;
            if(nbr == parent) continue;

            if(!visited[nbr]){
                trajanBridgesDFS(nbr, node, graph, visited, tin, low, bridges);
                low[node] = Math.min(low[node], low[nbr]);

                if(tin[node] < low[nbr]){
                bridges.add(Arrays.asList(node, nbr));

            }
            } else {
                low[node] = Math.min(low[node], tin[nbr]);
            } 
        }
    }
    public static List<List<Integer>> trajanBridges (MyAdjacencyListGraph graph){
        int V = graph.adj.length;
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];

        List<List<Integer>> bridges = new ArrayList<>();

        timer = 0;
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                trajanBridgesDFS(i, -1, graph, visited, tin, low, bridges);
            }
        }

        return bridges;
    }
    //trajan's algorithm for articulation points 
    //articulation points are vertices in graph on whose removal graph gets split into multiple SCCs are known as articualtion points
    // ex -> (1)-(2)-(3) | here (2) is an articualtion point
    public static void tarjanArticulationPointsDFS (MyAdjacencyListGraph graph, int node, int parent, int[] tin, int[] low, boolean[] visited, HashSet<Integer> points){
        visited[node] = true;
        tin[node] = low[node] = ++timer;
        int children = 0;

        for(Edge e: graph.adj[node]){
            int nbr = e.destination;
            if(nbr == parent) continue;
            if(visited[nbr]){
                low[node] = Math.min(low[node], tin[nbr]);
            } else {
                tarjanArticulationPointsDFS(graph, nbr, node, tin, low, visited, points);
                low[node] = Math.min(low[node], low[nbr]);
                if(parent != -1 && tin[node] <= low[nbr]){
                    points.add(node);
                }
                children++;
            }
        }

        if(parent == -1 && children > 1){
            points.add(node);
        }
    }
    public static List<HashSet<Integer>> tarjanArticulationPoints (MyAdjacencyListGraph graph){
        int V = graph.adj.length;
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        List<HashSet<Integer>> articulation_points = new ArrayList<>();

        timer = 0;
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                HashSet<Integer> points = new HashSet<>();
                tarjanArticulationPointsDFS(graph, i, -1, tin, low, visited,  points);
                articulation_points.add(points);
            }
        }
        return articulation_points;
    }

    //floyd warshal's algorithm
    public static int[][] floydWarshall(int[][] dist){ //using an adjecency matrix here
        int V = dist.length;
        for(int k = 0; k < V; k++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for(int i = 0; i < V; i++){
            if(dist[i][i] != 0){
                System.out.println("cycle detected");
            }
        }
        return dist;
    }
    
    //kosaraju's algorithm
    public static void kosarajuDFS1(int node, MyAdjacencyListGraph graph, boolean[] visited, Stack<Integer> st){
        visited[node] = true;
        for(Edge e : graph.adj[node]){
            int nbr = e.destination;
            if(!visited[nbr]){
                kosarajuDFS1(nbr, graph, visited, st);
            }
        }
        //backtracking
        //add the parent node at last so that it comes out in first when taking out
        st.add(node);
    }
    public static void kosarajuDFS2(int node, MyAdjacencyListGraph graph, boolean[] visited, List<Integer> ans){
        visited[node] = true;
        for(Edge e : graph.adj[node]){
            int nbr = e.destination;
            if(!visited[nbr]){
                kosarajuDFS2(nbr, graph, visited, ans);
            }
        }
        //backtracking+
        // push node after finishing DFS (finishing time)
        ans.add(node); 
    }
    public static List<List<Integer>> kosaraju(MyAdjacencyListGraph graph){
        // code here
        //do a dfs and store the order of dfs in a stack
        int V = graph.adj.length;
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                //call for a dfs that fills up the stack
                kosarajuDFS1(i, graph, visited, st);
            }
        }

        //make a reversed graph duplicate
        MyAdjacencyListGraph graphT = new MyAdjacencyListGraph(V);
        for(int i = 0; i < V; i++){
            //reseting the visited array 
            visited[i] = false;
            for(Edge e : graph.adj[i]){
                int nbr = e.destination;
                graphT.adj[nbr].add(new Edge(nbr, i));
            }
        }

        List<List<Integer>> SCC = new ArrayList<>();

        //now make the SCCs accoding to the order stored in stack using dfs
        while (!st.isEmpty()) {
            int curr = st.pop();
            if(visited[curr]) continue;
            List<Integer> temp = new ArrayList<>();
            //dfs of 2nd type
            kosarajuDFS2(curr, graphT, visited, temp);
            SCC.add(temp);
        }

        return SCC;
    }
    //Notes-
    //there is no gurantee that our graphs will be connected
    public static void main(String[] args){

/*      wt diagram of alg
    UNDIRECTED GRAPH (weights in brackets)

            0
            |
          [5]
            |
            1
          /   \
       [1]     [3]
      /         \
     2 ----[1]---- 3
      \
      [2]
        \
         4
*/
        MyAdjacencyListGraph alg = new MyAdjacencyListGraph(5);
        //graph
        {
        //0th vertex
        alg.adj[0].add(new Edge(0, 1, 5));
        //1st vertex
        alg.adj[1].add(new Edge(1, 0, 5));
        alg.adj[1].add(new Edge(1, 2, 1));
        alg.adj[1].add(new Edge(1, 3, 3));
        //2nd vertex
        alg.adj[2].add(new Edge(2, 1, 1));
        alg.adj[2].add(new Edge(2, 3, 1));
        alg.adj[2].add(new Edge(2, 4, 2));
        //3rd vertex
        alg.adj[3].add(new Edge(3, 1, 3));
        alg.adj[3].add(new Edge(3, 2, 1));
        //4th vertex
        alg.adj[4].add(new Edge(4, 2, 2));
        }

/*      diagram
        UNDIRECTED GRAPH (unweighted) (disconnected)

               0
             /   \
            1     2     7---8
            |     |
            3 --- 4
             \   /
               5 
               |
               6
*/
        MyAdjacencyListGraph alg1 = new MyAdjacencyListGraph(9);
        //graph
        {
        //0th
        alg1.adj[0].add(new Edge(0, 1));
        alg1.adj[0].add(new Edge(0, 2));
        //1st
        alg1.adj[1].add(new Edge(1, 0));
        alg1.adj[1].add(new Edge(1, 3));
        //2nd
        alg1.adj[2].add(new Edge(2, 0));
        alg1.adj[2].add(new Edge(2, 4));
        //3rd
        alg1.adj[3].add(new Edge(3, 1));
        alg1.adj[3].add(new Edge(3, 4));
        alg1.adj[3].add(new Edge(3, 5));
        //4th
        alg1.adj[4].add(new Edge(4, 2));
        alg1.adj[4].add(new Edge(4, 3));
        alg1.adj[4].add(new Edge(4, 5));
        //5th
        alg1.adj[5].add(new Edge(5, 3));
        alg1.adj[5].add(new Edge(5, 4));
        alg1.adj[5].add(new Edge(5, 6));
        //6th
        alg1.adj[6].add(new Edge(6, 5));

        //7th
        alg1.adj[7].add(new Edge(7, 8));
        //8th
        alg1.adj[8].add(new Edge(8, 7));
        }
        
/*      diagram
           0
         /   \
        1     2 
         \   /
           3
*/
        MyAdjacencyListGraph alg2 = new MyAdjacencyListGraph(4);
        //directed graph
        {
        alg2.adj[0].add(new Edge(0, 1));
        alg2.adj[0].add(new Edge(0, 2));

        alg2.adj[1].add(new Edge(1, 3));

        alg2.adj[2].add(new Edge(2, 3));
        }

/*
        1-----0-----3
               \   /
                \ /
                 2
*/
        MyAdjacencyListGraph alg3 = new MyAdjacencyListGraph(4);
        //directed graph
        {
        alg3.adj[0].add(new Edge(0, 2));

        alg3.adj[1].add(new Edge(1, 0));

        alg3.adj[2].add(new Edge(2, 3));

        alg3.adj[3].add(new Edge(3, 0));
        }

/*      diagram
        0 --[2]--> 1 --[7]--> 3 --[1]--> 5
        |           |          ^          ^
        |           [1]        |          |
        [4]         |          |          |
        |           v          [2]        [5]
        \--------->2 --[3]--> 4 ---------/
      
*/       
        MyAdjacencyListGraph alg4 = new MyAdjacencyListGraph(6);
        {
        // 0th vertex
        alg4.adj[0].add(new Edge(0, 1, 2)); // 0 -> 1, weight 2
        alg4.adj[0].add(new Edge(0, 2, 4)); // 0 -> 2, weight 4

        // 1st vertex
        alg4.adj[1].add(new Edge(1, 3, 7)); // 1 -> 3, weight 7
        alg4.adj[1].add(new Edge(1, 2, 1)); // 1 -> 2, weight 1

        // 2nd vertex
        alg4.adj[2].add(new Edge(2, 4, 3)); // 2 -> 4, weight 3

        // 3rd vertex
        alg4.adj[3].add(new Edge(3, 5, 1)); // 3 -> 5, weight 1

        // 4th vertex
        alg4.adj[4].add(new Edge(4, 3, 2)); // 4 -> 3, weight 2
        alg4.adj[4].add(new Edge(4, 5, 5)); // 4 -> 5, weight 5
        }
        
        MyAdjacencyListGraph alg5 = new MyAdjacencyListGraph(5);
        {
        // 0th vertex
        alg5.adj[0].add(new Edge(0, 1, 2)); 
        alg5.adj[0].add(new Edge(0, 2, 4)); 

        // 1st vertex
        alg5.adj[1].add(new Edge(1, 2, -4)); 

        // 2nd vertex
        alg5.adj[2].add(new Edge(2, 3, 2)); 

        // 3rd vertex
        alg5.adj[3].add(new Edge(3, 4, 4));

        // 4th vertex
        alg5.adj[4].add(new Edge(4, 1, -1)); 
        }
        
        System.out.println(tarjanArticulationPoints(alg));
    }
}