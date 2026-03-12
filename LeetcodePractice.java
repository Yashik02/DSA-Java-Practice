import java.util.*;
import java.util.function.Function;


public class LeetcodePractice {

    //solution 
    //bruteforce approach

    public static int maxArea(int[] array) {
        int base = array.length;

        int area;
        int maxArea = Integer.MIN_VALUE;

        for(int i = 0; i < base; i++) {
            for(int j = i + 1; j < base; j++) {
                area = (j - i) * Math.min(array[i], array[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }   

    public static int MaxArea(int[] array) {
        int n = array.length;
        int left = 0, right = n - 1;
        int maxArea = Integer.MIN_VALUE;

        while(left < right) {

            int leftHeight = array[left], rightHeight = array[right];
        
            if(leftHeight < rightHeight) {
                int area = (right - left) * leftHeight;
                left++;
                maxArea = Math.max(maxArea, area);
            }
            else{
                int area = (right - left) * rightHeight;
                right--;
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static int Maxrea(int[] array) {
        int left = 0, right = array.length - 1;
        int maxArea = Integer.MIN_VALUE;

        while(left < right) {
            if(array[left] < array[right]) {
                left++;
                maxArea = Math.max(maxArea, (right - left) * array[left]);
            }
            else{
                right--;
                maxArea = Math.max(maxArea, (right - left) * array[right]);
            }
        }
        return maxArea;
    }

    
    
    @FunctionalInterface
    interface Operation {
        int apply(int a, int b);
    }

    public static int perform (int a, int b, Operation op) {
        return op.apply(a,b);
    }

    @FunctionalInterface
    interface Single {
        int apply(int a);
    }

    public static int perform (int a, Single op) {
        return op.apply(a);
    }

    public static <String, Integer> Integer applyFunction(String input, Function<String, Integer> func) {
        return func.apply(input);
    }

    // heap sort
    
    
    public static void main (String[] args) {

        //logics
        Operation add = (a,b) -> a+b;
        Operation mul = (a,b) -> a*b;
        Operation div = (a,b) -> a/b;
        Operation sub = (a,b) -> a-b;
        Single sq = a -> a*a;
        
        Function<Integer, String> shoutOut = x -> "02 rocks";
        System.out.println(applyFunction(02, shoutOut));

        //System.out.println(perform(2,sq));

        
    }
}
