import java.lang.reflect.Array;
import java.util.*;

import javax.print.event.PrintJobAttributeEvent;

public class GreedyPractice {

    public static int ActivitySelection (int[] start, int [] end) {
        
        int n = start.length;
        int[][] matrix = new int[n][3];
        for(int i = 0; i < n; i++) {
            matrix[i][0] = start[i];
            matrix[i][1] = end[i];
            matrix[i][2] = i;//storing indexes
        }
        
        Arrays.sort(matrix, (a,b) -> a[1] - b[1]);

        List<Integer> list = new ArrayList<>();
        list.add(matrix[0][2 ]);
        int lastEnd = matrix[0][1];
        for (int i = 1; i < n; i++){
            if(matrix[i][0] > lastEnd) {
                list.add(matrix[i][2]);
                lastEnd = matrix[i][1];
            }
        }

        //printing the soulution
        System.out.println(list); 
        for (int i : list) {
            System.out.println("[" + start[i] + "," + end[i] + "]");
        }
        return list.size();
        
    }

    public static double maxProfit (int[] val, int[] weight, int sack) {
        int n = val.length;
        double[][] ratio = new double[n][2];
        for (int i = 0; i < n; i++) {
            ratio[i][0] = i;
            ratio[i][1] = (double) val[i] / weight[i];
        }

        Arrays.sort(ratio, (a,b) -> Double.compare( b[1], a[1]));

        int capacity = sack;
        double profit = 0;
        for (int i = 0; i < n; i++) {
            int idx = (int)ratio[i][0];
            if (weight[idx] <= capacity) {
                profit += val[idx];
                capacity -= weight[idx];
            } else {
                profit += capacity * ratio[i][1];
                capacity = 0;
                break;
            }
        }
        return profit;
    }

    //chcola problem

    public static int minCost(int[] vertical, int[] horizontal) {
        int x = 1, y = 1;
        Arrays.sort(vertical);
        int n = vertical.length;
        Arrays.sort(horizontal);
        int m = horizontal.length;
        int i = n-1, j = m-1;
        int cost = 0;
        while (i >= 0 && j >= 0) {
            if (vertical[i] >= horizontal[j]) {
                cost += vertical[i--] * y;
                x++;
            } else {
                cost+= horizontal[j--] * x;
                y++;
            }
        }
        while (i >= 0) {
            cost += vertical[i--] * y;
            x++;
        }
        while (j >= 0) {
            cost+= horizontal[j--] * x;
            y++;
        }
        return cost;
    }

    //KTH ODD ELEMENT
    public static int kthOdd (int low, int high, int k) {
        int total = high - low + 1;

        //check for our of bounds
        if ((total & 1) == 1 && (high & 1) == 1) {
            if (k > total / 2 + 1) return 0;
            
        } else {
            if (k > total / 2) return 0;
        }

        if ((high & 1) == 1) {
            return high - ((k - 1) * 2);
        } else {
            return high - ((k - 1) * 2) - 1;
        }

    }

    public static String smallestWord (int n, int k) {
        StringBuilder str = new StringBuilder("");
        for ( int i = n; i >= 1; i--) {
            int currMax = k - (i-1);
            if (currMax >= 26) {
                str.insert(0,'z');
                k -= 26;          
            } else {
                str.insert(0, (char)('a' + currMax - 1));
                k -= currMax;
            }   
        }
        return str.toString();
    }

    public static int stock (int[] prices) {
        int n = prices.length;
        int buy =  prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            if(buy < prices[i]) {
                buy = prices[i];
            }
            int currMax = buy - prices[i];
            maxProfit = Math.max(maxProfit, currMax);
        }
        return maxProfit;
    }

    
    public static void main (String[] args) {

        System.out.println(kthOdd(-2,2,1));

        // int[] start = {1,8,3,0,5,5};
        // int[] end =   {2,9,4,6,7,9};
        // System.out.println("max activities = " + ActivitySelection(start, end));

        // int[] val = {60,100,120};
        // int[] weight = {10,20,30};
        // int sack = 50;
        // System.out.println(maxProfit(val, weight, sack));

        // int[] start = {5,39,5,27,50};
        // int[] end = {24,60,28,40,90};
        // System.out.println("max cahins form will be " + ActivitySelection(start, end));

        // int[] vertical = {2,1,3,1,4};
        // int[] horizotal = {4,1,2};
        // System.out.println(minCost(vertical, horizotal));

        System.out.println(smallestWord(5, 42));
    }
}