import java.util.*;
import java.util.LinkedList;
public class QueuePractice {

    public static Queue<Integer> interleave (Queue<Integer> nums) {
        Queue<Integer> q1 = new ArrayDeque<>();
        int n = nums.size()/2;
        for (int i = 1; i <= n; i++) {
            q1.add(nums.remove());
        }

        while (!q1.isEmpty()) {
            nums.add(q1.remove());
            nums.add(nums.remove());
        }
        return nums;
    }

    public static Queue<Integer> reverse (Queue<Integer> nums) {
        if (nums.isEmpty()) {
            return nums;
        }

        int num = nums.remove();
        reverse(nums);
        nums.add(num);
        return nums;
    }

    public static int decToBin (int n) {
        Queue<Integer> q = new LinkedList<>();
        while (n != 0) {
            q.add(n % 2);
            n /= 2;
        }
        int multiplier = 1;
        while (!q.isEmpty()) {
            n += multiplier * q.remove();
            multiplier *= 10;
        }
        return n;
    }

    

    public static void binaryTillN (int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(decToBin(i) + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr){
        int n = arr.length;
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // reverse queue
    public static Queue<Integer> reverseQ (Queue<Integer> nums) {
        if(nums.isEmpty()) {
            return nums;
        }

        int ex = nums.remove();
        reverseQ(nums);
        nums.add(ex);
        return nums;
    }

    //maximum sliding window

    public static int[] maxSlidingWindow (int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        ArrayDeque<Integer> window = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!window.isEmpty() && arr[window.peekLast()] <= arr[i]) {
                window.removeLast();
            }
            window.add(i);
            if(i - window.peekFirst() + 1 > k) {
                  window.removeFirst();
            }
            if(i >= k-1){
                result[i - k + 1] = arr[window.peekFirst()];
            }
        }
        return result;
    }
    public static int[] maxSlidingWindowPractice(int[] arr, int k){
        int n = arr.length;
        int[] ans = new int[n - k + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            while(!dq.isEmpty() && arr[dq.peek()] < arr[i]){
                dq.remove();
            }
            dq.add(i);

            if(i-k+1 > dq.peekFirst()){
                dq.removeFirst();
            }
            if(i >= k-1){
                ans[i - k + 1] = arr[dq.peekLast()];
            }
            
        }
        return ans;
    }

    public static void main (String[] args) {
        int[] nums = {4,3,2,6};
        printArray(nums);
        System.out.println();

        Queue<Integer> numbers = new ArrayDeque<>();
        for(int i = 1; i <= 10; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);
        reverseQ(numbers);
        System.out.println(numbers);

        int[] arr = {1,3,-1,-3,5,3,6,7};
        printArray(arr);
        int[] res = maxSlidingWindowPractice(arr, 3);
        printArray(res);
    }
}
