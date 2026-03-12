
import java.util.*;

public class ChitkaraPractice {

    public static void main (String[] args) {

        LinkedList<Integer> ll = new LinkedList<>();

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }

        System.out.println(listSum(list));
        System.out.println(list);
        reverseList(list);
        System.out.println(list);
        removeSec(list);
        System.out.println(list);

        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};

        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++){
            ArrayList<Integer> row = new ArrayList<>();
            transpose.add(row);
            for(int j = 0; j < matrix[0].length; j++){
                row.add(matrix[j][i]);
            }
        }
        System.out.println(transpose);
        
    }

    public static boolean isPalindrome(int[] arr) { 
        int n = arr.length;
        for (int i = 0; i < (n/2) - 1; i++) {
            if(arr[i] != arr[n-1-i]) {
                return false;
            }
        }
        return true;
    }
    
    public static int listSum(ArrayList<Integer> list){
        int sum = 0;
        for(int i : list){
            sum += list.get(i);
        }
        return sum;
    }

    public static void removeSec(ArrayList<Integer> list) {
        int n = list.size();
        for(int i = n-1; i >= 0; i--) {
            if(i % 2 == 0) {
                list.remove(i);
            }
        }
        return;
    }

    

    public static void reverseList(ArrayList<Integer> list) {
        int n = list.size();

        for(int i = 0; i < n / 2; i++){
            int temp = list.get(i);
            list.set(i, list.get(n-1-i));
            list.set(n-1-i, temp);
        }
        return;
    }
    

    public static String longestCommonPrefix(String[] strs) {
        String ans = new String(strs[0]);
        int n = strs.length;
        for(int i = 1; i < n; i++) {
            int t = 0;
            while(true) {
                if(ans.charAt(t) == strs[i].charAt(t)){
                    t++;
                } else {
                    break;
                }
            }
            ans = ans.substring(0, t);
        }
        return ans;
    }

    public static String simplifyPath (String directions) {
        int n = directions.length();
        int vert = 0;
        int hori = 0;
        for(int i = 0; i < n; i++) {
            char c = directions.charAt(i);
            if(c == 'W') {
                hori--;
            } else if(c == 'E') {
                hori++;
            } else if(c == 'N') {
                vert++;
            }else if(c == 'S') {
                    vert--;
            }
        }
        StringBuilder ss = new StringBuilder("");
        if(hori < 0) {
            for(int i = hori; i < 0; i++) {
                ss.append('W');
            }
        } else if (hori > 0) {
            for(int i = hori; i > 0; i--) {
                ss.append('E');
            }
        }
        if(vert < 0) {
            for(int i = vert; i < 0; i++) {
                ss.append('S');
            }
        } else if (vert > 0) {
            for(int i = vert; i > 0; i--) {
                ss.append('N');
            }
        }
        return ss.toString();
    }


    public static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    System.out.println();
    }

    public static void sort (int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n-1; i++) {
            int maxVal = Integer.MIN_VALUE;
            int idx = 0;
            for(int j = 0; j < n-i; j++) {
                if(arr[j] > maxVal) {
                    idx = j;
                    maxVal = arr[j];
                }
            }
            //swap
            int temp = arr[idx];
            arr[idx] = arr[n-1-i];
            arr[n-i-1] = temp;
        }
        return;
    }

    public static void bubblesort (int[] arr) {
        int n = arr.length;
        for(int j = 0; j < n-1; j++) {
            boolean swap = false;
            for (int i = 0; i < n-j-1; i++) {
                if(arr[i] > arr[i+1]) {
                    //swap
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swap = true;
                }
            }
            if (swap == false) {
                return;
            }
        }
        return;
    }

    public static int binarySearch (int[] arr, int target) {
        int start = 0, end = arr.length-1;
        
        while(start < end) {
            int mid = start + (end - start)/2;
            if(target == arr[mid]) {
                return mid;
            } else if(target < arr[mid]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }

    public static int binToDec (int n) {
        int ans = 0;
        int mul = 0;
        int j = n;
        for(int i = 0; i < j; i++) {
            int curr = n % 10;
            ans += Math.pow(2, mul) * curr;
            mul++;
            n = n/10;
        }
        return ans;
    }

    public static boolean isPrime(int n) {
        if(n == 1 || n == 0){
            return false;
        }
        for(int i = 2; i <= Math.sqrt(n); i++ ){
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
    

    //example meathod
    public int returning (int a) {
        //after some calculations
        int ans = a + 1;
        return ans;
    }
     
    //subarray sum
    public static boolean subarrayMatch (int[] arr, int key) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currSum = 0;
                for(int k = i; k <= j; k++) {
                    currSum += arr[k];
                }
                if(currSum == key) {
                    return true;
                }
            }
        }
        return false;
    }

    //majority element problem with the moore's voting algorithm, i did look up how exactly the algorithm worked, but i wrote the syntax from scrathc
    public static int majorityElementAns (int[] arr, int start, int end) {
        int majorElement = majorityElement(arr, start, end);
        int n = arr.length;
        int count = 0;
        for(int i : arr) {
            if(majorElement == i) {
                count++;
            }
        }
        if(count > n/2) {
            return majorElement;
        } else {
            return -1;
        }
    }

    public static int majorityElement (int[] arr, int start, int end) {
        if(start == end) {
            return arr[start];
        }
        int mid = start + (end - start)/2;
        int left = majorityElement(arr, start, mid);
        int right = majorityElement(arr, mid+1, end);

        if(left == right) {
            return left;
        } else {
            int tallyLeft = tallyCounter(arr, start, mid, right);
            int tallyRight = tallyCounter(arr, mid+1, end, left);
            if(tallyLeft >= tallyRight) {
                return left;
            } else {
                return right;
            }
        }
    }
    public static int tallyCounter(int[] arr, int start, int end, int key) {
        int count = 0;
        for(int i = start; i < end; i++) {
            if(arr[i] == key) {
                count++;
            }
        }
        return count;
    }

    //practice
    public static int[] reverseArray(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
        return arr;
    }

    //kadane's algorithm
    public static int kadane2 (int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int currMax = 0;
        for(int i = 0; i < n; i++) {
            currMax = Math.max(currMax + arr[i], arr[i]);
            max = Math.max(currMax, max);
        }
        return max;
    }


    public static int[] rotateArray (int[] arr, int a) {
        //use of modular arithematics
        int n = arr.length;
        int k = a%n;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[(k+i)%n] = arr[i];
        }
        return ans;
    }

    //sort checker
    public static boolean sortCheck (int [] arr) {
        int n = arr.length;
        for(int  i = 1; i < n; i++) {
            if(arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
        //idk how to check with an rotated array... :( 
    }

    
    public static int secLar(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int seMax = max;
        for(int i = 0; i < n; i++) {
            if(arr[i] > max) {
                seMax = max;
                max = arr[i];
            } else if (arr[i] > seMax && arr[i] < max) {
                seMax = arr[i];
            }
        }
        return seMax;
    }

    public static int[] zeroAtStart (int[] arr) {
        int n = arr.length;
        int idx = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
                idx++;
            }
        }
        return arr;
    }

    //meathod for prefix sum array
    static int[] prefixSum1(int arr[]) {
        int n = arr.length;
        int[] prefix = new int[n];
        for(int i = 0; i < n; i++) {
            if(i ==0) {
                prefix[0] = arr[0];
            } else {
                prefix[i] = prefix[i - 1] + arr[i];
            }
        }
        return prefix;
    }
    static int maxSubArraysum(int arr[]) {
        int n = arr.length;
        int[] prefix = prefixSum1(arr);
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            
            for (int j = i; j < n; j++) {
                int currSum;
                if(i == 0) {
                    currSum = prefix[j];
                } else {
                    currSum = prefix[j] - prefix[i-1];
                }
                maxSum = Math.max(maxSum, currSum);
            }
            
        }
        return maxSum;
    }


    //kadane's algorithm
    public static int kadane(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = 0; i < n; i++) {
            currSum = Math.max(currSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, currSum);
        }   
        return maxSum;
    }

    //MAX SUM ARRAY FOR THE TOATL OF 10 RAISED TO THE POWER OF THE FIRST FIVE EXA
    public static void functionTrail (int[] arr) {
        int n = arr.length;
        
    }


    public static int[] bubbleSort (int[] arr) {
        int n = arr.length;
        for(int j = 0; j < n-1; j++) {
            boolean swap = false;
            for(int i = 0; i < n-1-j; i++) {
                if(arr[i] > arr[i+1]) {
                    //swap
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swap = true;
                }
            }
            if(swap == false) {
                break;
            }
        }
        return arr;
    }

    //selection sort
    public static int[] selectionSort (int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n-1; i++) {
            int maxIdx = i;
            for(int j = i; j < n ; j ++) {
                if(arr[j] < arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            if(maxIdx != i) {
                int temp = arr[i];
                arr[i] = arr[maxIdx];
                arr[maxIdx] = temp;
            }
        }
        return arr;
    }

    public static int[] insertionSort (int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            int curr = arr[i];
            int prev = i - 1;
            while(prev >= 0 && arr[prev] > curr) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = curr;
        }
        return arr;
    }

    
}
