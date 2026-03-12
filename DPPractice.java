import java.util.*;
public class DPPractice {
    //Notes
    /* 
    Dynamic programming are a set of techniques to solve problems.
    Identify a DP problem by checking for Overlapping Subproblems (repeatedly solving the same smaller problems) and 
    Optimal Substructure (optimal solution built from optimal sub-solutions)
     */

    //DP fibbonachi | TC: O(n)
    public static int fibb(int n, int[] data) {
        //base case
        if(n==0 || n==1) {
            return n;
        }
        
        if(data[n] != 0) {
            return data[n];
        }

        int sum = fibb(n-1, data) + fibb(n-2, data);
        data[n] = sum; //store data and then return
        return sum;
    }
    //fibbonachi | TC: O(2^n)
    public static int fibb(int n) {
        //base case
        if(n==0 || n==1) return n;
        return fibb(n-1) + fibb(n-2);
    }

    //DP 0-1 Knapsack with memoization
    public static int knapSack (int[] value, int[] weight, int capacity, int i, int[][] dp) {
        //base case
        if(capacity == 0 || i == 0) {
            return 0;
        }

        if(dp[i][capacity] != -1) {
            return dp[i][capacity];
        }

        int include = 0;
        int exclude = 0;
        //(include or exclude) Xor exclude

        if(weight[i-1] <= capacity) { //if valid
            //include
            include = value[i-1] + knapSack(value, weight, capacity - weight[i-1], i-1, dp);
        }

        //exclude
        exclude = knapSack(value, weight, capacity, i-1, dp);
        
        dp[i][capacity] = Math.max(include, exclude);
        return dp[i][capacity];
    }
    //DP 0-1 Knapsack with tabulation
    public static int knapSack (int[] value, int[] weight, int capacity) {
        int n = value.length;

        //table creation
        //there are 2 variable changing - value, and weight -> 2D array
        int[][] dp = new int[n+1][capacity+1];
        
        for(int i = 1; i <= n; i++) {
            for(int w = 1; w <= capacity; w++) {
                int exclude = dp[i-1][w];
                int include = 0;
                if(weight[i-1] <= w) {
                    include = value[i-1] + dp[i-1][w-weight[i-1]];
                }
                dp[i][w] = Math.max(include, exclude);
            }
        }
        
        return dp[n][capacity];
    }
    //0-1 Knapsack
    public static int knapSack (int[] value, int[] weight, int capacity, int i) {
        //base case
        if(capacity == 0 || i == 0) {
            return 0;
        }

        int include = 0;
        int exclude = 0;
        //(include or exclude) Xor exclude

        if(weight[i-1] <= capacity) { //if valid
            //include
            include = value[i-1] + knapSack(value, weight, capacity - weight[i-1], i-1);
        }

        //exclude
        exclude = knapSack(value, weight, capacity, i-1);
        
        return Math.max(include, exclude);
    }
    //DP 0-1 Knapsack unbounded with tabulation
    public static int knapSackUnbounded (int[] value, int[] weight, int capacity) {
        int n = value.length;

        //table creation
        //there are 2 variable changing - value, and weight -> 2D array
        int[][] dp = new int[n+1][capacity+1];
        
        for(int i = 1; i <= n; i++) {
            for(int w = 1; w <= capacity; w++) {
                int exclude = dp[i-1][w];
                int include = 0;
                if(weight[i-1] <= w) {
                    include = value[i-1] + dp[i][w-weight[i-1]];
                }
                dp[i][w] = Math.max(include, exclude);
            }
        }
        
        return dp[n][capacity];
    }
    // learn how to return the items used
    //DP 0-1 Knapsack with tabulation
    public static List<Integer> knapSackList (int[] value, int[] weight, int capacity) {
        int n = value.length;

        //table creation
        //there are 2 variable changing - value, and weight -> 2D array
        int[][] dp = new int[n+1][capacity+1];
        
        for(int i = 1; i <= n; i++) {
            for(int w = 1; w <= capacity; w++) {
                int exclude = dp[i-1][w];
                int include = 0;
                if(weight[i-1] <= w) {
                    include = value[i-1] + dp[i-1][w-weight[i-1]];
                }
                dp[i][w] = Math.max(include, exclude);
            }
        }  

        int w = capacity;
        List<Integer> ans = new ArrayList<>();
        for(int i = n; i > 0; i--){
            if(dp[i-1][w] != dp[i][w]) {
                ans.add(i-1);
                w -= weight[i-1];
            }
        }
        
        return ans;
    }

    //target sum subset - tabulation
    public static boolean targetSum (int[] value, int target) {
        int n = value.length;

        //table of items and targets
        //to see ifit is possible to reach a target perfectly at a given no of items
        boolean[][] dp = new boolean[n+1][target+1];

        //initialize - if the tagret is 0 then not including the item is always an valid answer
        for(int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int t = 1; t <= target; t++) {
                //excluding and (if valid) including
                if(dp[i-1][t] || (value[i-1] <= t && dp[i-1][t-value[i-1]])) {
                    dp[i][t] = true;
                }
            }
        }

        return dp[n][target];
    }

    //longest common subsequence
    public static int longestCommonSubsequence (String a, String b) {

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n+1][m+1];
        //initialization - if n == 0 || m == 0 -> ans = 0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        //how to print the output string
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;
        while(i>0 && j >0) {
            if(a.charAt(i-1) == b.charAt(j-1)){ 
                lcs.append(a.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println("Longest Common Subsequence = " + lcs.reverse().toString());

        return dp[n][m];
    }

    public static int longestCommonSubstring (String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
        int ans = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(dp[i][j], ans);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return ans;
    }

    //longest increasing subsequence = longest common sorted unique subsequence
    public static int longestIncreasingSubsequence (int[] arr1) {

        HashSet<Integer> hs = new HashSet<>();
        for (int i : arr1) {
            hs.add(i);
        }

        int[] arr2 = new int[hs.size()];
        int i = 0;
        for(int num : hs) {
            arr2[i] = num;
            i++;
        }
        Arrays.sort(arr2);

        return longestCommonSubsequence(arr1, arr2);
    }
    //helper longest common subsequence for LIS
    public static int longestCommonSubsequence(int[] arr1, int[] arr2) {
        //using tabulation

        int n = arr1.length, m = arr2.length;
        int[][] dp = new int[n+1][m+1];

        //initailization at i == 0 || j == 0 dp[i][j] = 0

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }
    
    //longest increasing subsequence -> optimised with my attempt at it
    public static int LISmemoization (int[] nums) {
        int n = nums.length;

        //making an dp array
        int[][] dp = new int[n][n+1];
        //[idx][previous idx] - since previous index can only be (-1 to n-2) we take {+1} to change coordinate to (0 to n-1)

        //initailization
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helperLIS(nums, 0, -1, dp);

    }
    public static int helperLIS (int[] nums, int i, int p, int[][] dp) {
        int n = nums.length;
        //base case
        if(i == n) {
            return 0;
        }

        //memoization
        if(dp[i][p+1] != -1) return dp[i][p+1];

        int skip = helperLIS(nums, i+1, p, dp);

        int take = 0;
        if(p == -1 || nums[p] < nums[i]) {
            take = helperLIS(nums, i+1, i, dp) + 1;
        }

        dp[i][p+1] = Math.max(skip, take);
        return dp[i][p+1];
    }

    //catlan's number recursive with memoization
    public static int catlanMemo (int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return catlanHelper(n, dp);
    }
    public static int catlanHelper (int n, int[] dp) {
        //base case
        if(n <= 1) return 1;
        if(dp[n] != -1) return dp[n];

        int sum = 0;
        for(int i = 0; i < n/2; i++) {
            sum += catlanHelper(i, dp) * catlanHelper(n-1-i, dp);
        }
        sum *= 2;
        if(n%2==1) {
            int mid = catlanHelper(n/2, dp);
            sum += mid * mid;
        }
        dp[n] = sum;
        return dp[n];
    }
    //catlan's number tabulation
    public static int catlan (int n) {
        if (n <= 1) return 1;
        
        int[] dp = new int[n+1];
        //initialize
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            int sum = 0;
            for(int j = 0; j < i/2; j++) {
                sum += dp[j] * dp[i-1-j];
            }
            sum *= 2;

            if(i%2==1) {
                sum += dp[i/2] * dp[i/2];
            }
            dp[i] = sum;
        }
        
        return dp[n];
    }

    //mountain ranges - brute force with permutation and combinations
    //for an effecient solution it is the same as catlan's number
    public static int mountainRanges(int n) {
        int[] ans = {0   };
        mountainRangesHelper(n, 0, 0, ans);
        return ans[0];
    }
    public static void mountainRangesHelper (int n, int Tup, int Tdown, int[] ans) {
        //base case
        if(Tdown == n){
            ans[0]+=1;
            return;
        }

        if(Tup < n) {
            mountainRangesHelper(n, Tup+1, Tdown, ans);
        }
        if(Tdown < Tup) {
            mountainRangesHelper(n, Tup, Tdown+1, ans);
        }
    }
    
    //matrix chain multiplication
    //recursion
    public static int matrixChainMultiplicationRecursion (int[] arr, int i, int j) {
        //base case 
        if(i==j) return 0;
        int ans = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            int cost1 = matrixChainMultiplicationRecursion(arr, i, k);
            int cost2 = matrixChainMultiplicationRecursion(arr, k+1, j);
            int cost3 = arr[i-1] * arr[k] * arr[j];
            int total_cost = cost1 + cost2 + cost3;
            ans = Math.min(ans, total_cost);
        }
        return ans;
    }
    //dp with memoization
    public static int matrixChainMultiplicationMemoization(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return matrixChainMultiplicationMemoizationHelper(arr, 1, n-1, dp);
    }
    public static int matrixChainMultiplicationMemoizationHelper (int[] arr, int i, int j, int[][] dp) {
        //base case 
        if(i==j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int ans = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            int cost1 = matrixChainMultiplicationMemoizationHelper(arr, i, k, dp);
            int cost2 = matrixChainMultiplicationMemoizationHelper(arr, k+1, j, dp);
            int cost3 = arr[i-1] * arr[k] * arr[j];
            int total_cost = cost1 + cost2 + cost3;

            ans = Math.min(ans, total_cost);
        }

        dp[i][j] = ans;
        return dp[i][j];
    }
    //dp with tabulation
    public static int matrixChainMultiplicationTabulation (int[] arr) {
        int n = arr.length;

        //table
        int[][] dp = new int[n][n];
        //initialization - diagonal elements = 0
        
        //diagonal matrix traversal
        for(int d = 2; d < n; d++) {
            for(int i = 1; i < n-(d-1); i++) {
                int j = i+(d-1);
                int ans = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    int cost1 = dp[i][k];
                    int cost2 = dp[k+1][j];
                    int cost3 = arr[i-1] * arr[k] * arr[j];
                    int total_cost = cost1 + cost2 + cost3;
                    ans = Math.min(ans, total_cost);
                }
                dp[i][j] = ans;
            }
        }
        return dp[1][n-1];
    }

    public static void main(String[] args) {

        //matrix chain multiplication - minimum cost
        int[] arr2 = {10, 20, 30, 40, 30, 20, 10, 5, 10, 20, 30, 40, 30, 20, 10, 5, 10, 20, 30, 40, 30, 20, 10, 5, 10};
        //sol - 61375
        System.out.println(matrixChainMultiplicationMemoization(arr2));
        System.out.println(matrixChainMultiplicationTabulation(arr2));

        //mountain ranges
        System.out.println(mountainRanges(5));
        //catlan numbers
        System.out.println(catlan(4));

        //longest Increasing Subsequence
        int[] arr1 = {50, 3, 10, 7, 40, 80};
        System.out.println(longestIncreasingSubsequence(arr1));
        System.out.println(LISmemoization(arr1));

        //longest common substring
        String a = "abcde";
        String b = "ace";
        System.out.println(longestCommonSubstring(a, b));


        //longest common subsequence
        String a1 = "abcde";
        String b1 = "ace";
        System.out.println(longestCommonSubsequence(a1, b1));

        //target sum
        int[] val = {4, 2, 7, 3};
        System.out.println(targetSum(val, 8));
    
        //0-1 knapsack bounded
        int[] value = {15, 14, 10, 45, 30};
        int[] weight = {2, 5, 1, 3, 4};
        int capacity = 7;
        int[][] dp = new int[value.length+1][capacity+1];

        for(int i = 0; i < value.length+1; i++) {
            Arrays.fill(dp[i], -1);
        }
        //tabulation
        System.out.println(knapSack(value, weight, capacity));
        //tabulation list
        System.out.println(knapSackList(value, weight, capacity));
        //tabulation unbounded
        System.out.println(knapSackUnbounded(value, weight, capacity));
        //memoization
        System.out.println(knapSack(value, weight, capacity, value.length, dp));
        //System.out.println(knapSack(value, weight, capacity, value.length));

        //fibbonachi
        int n = 40;
        int[] fibb_data = new int[n+1]; // 0 is included
        System.out.println(fibb(n, fibb_data));
        //System.out.println(fibb(n));

    }
}
