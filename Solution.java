//LeetCode

import java.util.*;

public class Solution {

    //Classes
    public class Edge {
        int src;
        int dest;
        int wt;
        Edge(int src, int dest, int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }
    public class Pair {
        int dest;
        int cost;
        int stops;
        Pair(int dest, int cost, int stops){
            this.dest = dest;
            this.cost = cost;
            this.stops = stops;
        }
        Pair(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }
    public class Cords {
        int x;
        int y;
        Cords(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //GFG code here
    public static String findOrder(String[] words) {

        

        //making edges
        Set<List<Character>> edges = new HashSet<>();

        for(int i = 0; i < words.length-1; i++){
            String a = words[i];
            String b = words[i+1];

            boolean found = false;

            for(int k = 0; k < Math.min(a.length(), b.length()); k++){
                char ca = a.charAt(k);
                char cb = b.charAt(k);
                if(ca != cb){
                    edges.add(Arrays.asList(ca, cb)); //ca->cb edge fill form
                    found = true;
                    break;
                }
            }
            if(!found && a.length()>b.length()){
                return "";
            }
        }

        //make an adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            adj.add(new ArrayList<>());
        }

        //making indegree array
        int[] indeg = new int[26];
        for(List<Character> edge : edges){
            // u -> v
            int u = edge.get(0)-'a';
            int v = edge.get(1)-'a';
            adj.get(u).add(v);
            indeg[v]++;
        }

        // making an used array
        boolean[] used = new boolean[26];
        for(String w: words){
            for(char c: w.toCharArray()){
                used[c-'a'] = true;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        int totalUsed = 0;
        for(int i = 0; i < indeg.length; i++){ // adding initial elements
            if(used[i]){
                if(indeg[i] == 0){
                    q.add(i);
                }
                totalUsed++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            sb.append((char)(curr+'a'));
            count++;

            for(int i : adj.get(curr)){
                indeg[i]--;
                if(indeg[i] == 0){
                    q.add(i);
                }
            }
        }

        if(count < totalUsed){ //cycle detection
            return "";
        }
        return sb.toString();
    }

    public int minCost(int[][] houses) {
        // code here
        boolean[] visited = new boolean[houses.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Pair(0, 0));
        int totalCost = 0;
        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            if(!visited[curr.dest]){
                visited[curr.dest] = true;
                totalCost += curr.cost;

                for(int i = 0; i < houses.length; i++){
                    pq.add(new Pair(i, Math.abs(houses[curr.dest][0] - houses[i][0]) + Math.abs(houses[curr.dest][1] - houses[i][1])));
                }
            }
        }
        return totalCost;
    }
    
    public void floydWarshall(int[][] dist) {
        // Code here
        int V = dist.length;
        for(int k = 0; k < V; k++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    if(i == j || k == i || k == j) continue;
                    if(dist[i][k] == 100000000 || dist[k][j] == 100000000) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
    }
    //climbing stairs brute force
    static int total = 0;
    public static void helper(int n, int sum){
        if(n == sum){
            total++;
            return;
        }
        if(sum > n){
            return;
        }
        helper(n, sum + 1);
        helper(n, sum + 2);
    }
    public static int countWays(int n) {
        // your code here
        total = 0;
        helper(n, 0);
        return total;
    }

    //in trial - not the solution
    public void findMotherVertexDFS (List<ArrayList<Integer>> adj, int node, boolean[] visited, Stack<Integer> st){
        visited[node] = true;
        for(int nbr: adj.get(node)){
            if(!visited[nbr]){
                findMotherVertexDFS(adj, nbr, visited, st);
            }
        }
        st.add(node);
    }
    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //traverse in dfs to make a stack
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                //dfs and fill the stack
                findMotherVertexDFS(adj, i, visited, st);
            }
        }

        return st.pop();
    }

    //
    //leetcode code here

    //champain tower
    //DFS approach fails because the champain flow is updated multiple times (all paths in a graph)
    public void fill(double[][] glasses, double pour, int row, int col) {
        if(row >= glasses.length || col >= glasses[row].length) {
            return;
        }
        double space = 1-glasses[row][col];
        if(pour <= space) {
            glasses[row][col] += pour;
        } else {
            glasses[row][col] = 1;
            pour = pour-space;
            fill(glasses, pour/2, row+1, col);
            fill(glasses, pour/2, row+1, col+1);
        }
    }
    public double champagneTowerDFS(int poured, int query_row, int query_glass) {
        double[][] glasses = new double[query_row+1][];
        for(int i = 0; i <= query_row; i++) {
            glasses[i] = new double[i+1];
        }
        fill(glasses, poured, 0, 0);
        return glasses[query_row][query_glass];
    }
    //bfs approach
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] glasses = new double[query_row+1][query_row+1];
        glasses[0][0] = poured;

        for(int r = 0; r <= query_row; r++) {
            for(int c = 0; c <= r; c++) {
                double overflow = (glasses[r][c] - 1.0)/2.0;
                if(overflow > 0) {
                    glasses[r+1][c] += overflow;
                    glasses[r+1][c+1] += overflow;
                }
            }
        }
        return Math.min(1, glasses[query_row][query_glass]);
    }
    

    //word-break problem on leetcode
    public boolean helper (String s, HashSet<String> dict, int i, int j, int[][] dp) {
        int n = s.length();
        //base case
        if(s.length() == 0) {
            return false;
        }
        if(dict.contains(s)) {
            return true;
        }

        if(dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        for(int k = 1; k < n; k++) {
            if(helper(s.substring(0, k), dict, i, k, dp) && helper(s.substring(k), dict, k, j, dp)) {
                dp[i][j] = 1;
                return true;
            }
        }

        dp[i][j] = 0;
        return false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        HashSet<String> hs = new HashSet<>();
        for(String words: wordDict) {
            hs.add(words);
        }

        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper(s, hs, 0, n-1, dp);
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //sets
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return 0;

        Set<String> visited = new HashSet<>();
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();

        start.add(beginWord);
        end.add(endWord);
        
        int level = 1;

        while(!start.isEmpty() && !end.isEmpty()){
            Set<String> nextStart = new HashSet<>();

            //optimization
            if(start.size() > end.size()){
                Set<String> temp = start;
                start = end;
                end = temp;
            }

            for(String curr : start){
                for(int i = 0; i < curr.length(); i++){
                    for(int c = 0; c < 26; c++){
                        if(c == curr.charAt(i)-'a') continue;
                        String candidate = curr.substring(0, i) + (char)(c+'a') + curr.substring(i+1);
                        if(end.contains(candidate)) return level+1;
                        if(words.contains(candidate) && !visited.contains(candidate)){
                            visited.add(candidate);
                            nextStart.add(candidate);
                        }
                    }
                }
            }
            start = nextStart;
            level++;

        }
        return 0;
    }

    public boolean helperSorted(String a, String b, Map<Character, Integer> rank){
        for(int i = 0; i < Math.min(a.length(), b.length()); i++){
            if(rank.get(a.charAt(i)) > rank.get(b.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public boolean isAlienSorted(String[] words, String order) {
        //make an hashmap
        Map<Character, Integer> rank = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            rank.put(order.charAt(i), i);
        }
        //classic chekcing is sorted
        for(int i = 0; i < words.length-1; i++){
            if(!helperSorted(words[i], words[i+1], rank)){
                return false;
            }
        }
        return true;
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //first make an adjecency list
        List<Edge>[] graph = new List[n];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }
        //filling the adjecency list
        for(int[] flight : flights){
            graph[flight[0]].add(new Edge(flight[0], flight[1], flight[2]));
        }

        //making an dist array
        int[] cost = new int[n];
        for(int i = 0; i < cost.length; i++){
            if(i!=src){
                cost[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, 0, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            
            if(curr.stops > k) break;

            for(Edge e : graph[curr.dest]){
                if(curr.cost + e.wt < cost[e.dest]){
                    cost[e.dest] = curr.cost + e.wt;
                    q.add(new Pair(e.dest, cost[e.dest], curr.stops+1));
                }
            }
        }
        if(cost[dst] == Integer.MAX_VALUE){
            return -1;
        } else {
            return cost[dst];
        }  
    }
    
    //i will use BFS here
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original_color = image[sr][sc];
        if(original_color == color) return image;
        int row = image.length;
        int col = image[0].length;
        Queue<Cords> q = new ArrayDeque<>();
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};

        image[sr][sc] = color;
        q.add(new Cords(sr, sc));

        while(!q.isEmpty()){
            Cords curr = q.poll();

            for(int k = 0; k < 4; k++){
                //neighbours
                int i = curr.x+x[k];
                int j = curr.y+y[k];
                if((i>=0 && j>=0 && i < row && j<col) && image[i][j] == original_color){
                    image[i][j] = color;
                    q.add(new Cords(i, j));
                }
            }
        }
        return image;
    }

    private int timer = 0;
    private void dfs(int node, int parent, ArrayList<ArrayList<Integer>> graph, boolean[] vis, int[]tin, int[]low, List<List<Integer>> bridges){
        vis[node] = true;
        tin[node] = low[node] = timer++;
        for(int dest : graph.get(node)){
            if(dest == parent) continue;
            if(!vis[dest]){
                dfs(dest, node, graph, vis, tin, low, bridges);
                low[node] = Math.min(low[node], low[dest]);
            } else {
                low[node] = Math.min(low[node], low[dest]);
            }

            if(low[dest] > tin[node]){
                bridges.add(Arrays.asList(node, dest));
            }
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //make an adjecency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<Integer>());
        }
        for(List<Integer> con : connections){
            int u = con.get(0);
            int v = con.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        //making auxilary arrays
        boolean[] vis = new boolean[n];
        int[] tin = new int[n]; // time in
        int[] low = new int[n];

        //storing answers
        List<List<Integer>> bridges = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                dfs(i, -1, adj, vis, tin, low, bridges);
            }
        }
        //function call for DFS
        return bridges;
    }
    //helperMeathods

    


    //Main Function
    public static void main(String[] args) {
        
        //function calls/sample runs:-
        
        // List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        // System.out.println(ladderLength("hit", "cog", wordList));

        // String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        // System.out.println(findOrder(words));

        System.out.println(countWays(4));

        int[][] image = {
            {1,1,0,0},
            {1,1,0,0},
            {0,0,1,0},
            {1,1,0,0}
        };
        
    }
}
