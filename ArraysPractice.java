import java.util.*;

public class ArraysPractice {

    public static void update(int Array[]) {
        for (int i = 0; i < Array.length; i++) {
            Array[i]++;
        }
    }
    public static int linearSearch(int array[], int key) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == key) {
                return i;
            }
        }
        return -1;
    }
    public static int largestSearch(int array[]) {
        int hnum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > hnum) {
                hnum = array[i];
            }
        }
        return hnum;
    }
    public static int linearSearch(String array[], String key) {
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static int BinarySearch(int array[], int key) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == key) {
                return mid;
            }
            else if (key < array[mid]) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    } 

    public static void arrayRev(int array[]) {
        int temp;
        for(int i = 0; i < (array.length) / 2; i++) {
            temp = array[i];
            array[i] = array[(array.length - 1)- i];
            array[(array.length - 1)- i] = temp;
        } 
    }

    public static void pairArray(int array[]) {
        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < (array.length); j++) {
                System.out.print("(" + array[i] + ", " + array[j] + ") ");
            }
            System.out.println();
        }
    }

    public static void subArray(int array[]) {
        int n = array.length;
        int count = 0;
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int sum = 0;
                System.out.print("sum of (");
                for(int k = i; k <= j; k++) {
                    System.out.print(array[k]);
                    if(k < j) {
                        System.out.print(",");
                    }
                    sum += array[k];
                }
                count++;
                System.out.print("): " + sum + " | ");
                if(sum > high) {
                    high = sum;
                }
                if(sum < low) {
                    low = sum;
                }
            }
            System.out.println();
        }
        System.out.println("total subsets are : " + count );  
        System.out.println("lowest sum is : " + low);  
        System.out.println("highest sum is : " + high);
    }

    public static void subarray(int array[]) {
        int counter = 0;
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;

        for(int i = 0; i < array.length; i++) {
            for(int j = i; j < array.length; j++) {
                int sum = 0;
                for(int k = i; k <= j; k++) {   //printer
                    System.out.print(array[k]);
                    sum += array[k];
                }
                System.out.print(" : sum = " + sum + "\t");
                counter++;
                if(sum > high) {
                    high = sum;
                }
                if(sum < low) {
                    low = sum;
                }
            }
            System.out.println();
        }
        System.out.println("counter, highest sum, lowest sum = " + counter + ", " + high + ", " + low);
    }

    public static void maxSubArray3(int array[]){
        int high = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            for(int j = i; j < array.length; j++) {
                int sum = 0;
                for(int k = i; k <= j; k++) {
                    sum += array[k];
                }
                System.out.print(sum + "\t");
                if(sum > high) {
                    high = sum;
                }
            }
            System.out.println();
        }
        System.out.println("highest sum = " + high );
    }

    public static void maxSubArray2(int array[]){
        int high = Integer.MIN_VALUE;
        int prefix[] = new int[array.length];
        //calculate prefix
        prefix[0] = array[0];
        for(int i = 1; i < array.length; i++) {
            prefix[i] = prefix[i - 1] + array[i];
        }
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            for(int j = i; j < array.length; j++) {
                sum = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];
            }    
            if(sum > high) {
                high = sum;
            }
        }
        System.out.println("highest sum = " + high );
    }

    public static void maxSubArray1(int array[]) {
        int currentSum = 0; 
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            currentSum = Math.max(array[i], currentSum + array[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        System.out.println(maxSum);
    }

    public static void kadanes(int array[]) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if(currentSum < 0) {
                currentSum = 0;
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        System.out.println(maxSum);
    }

    public static int rainWaterCalculator(int array[]) {
        int collectedWater = 0;
        int left;
        int right;
        if(array.length < 3){
            return -1;
        }
        for(int i = 1; i < array.length - 1; i++){
            left = array[i];
            right = array[i];
            //leftLoop
            for(int j = i; j >= 0; j--) {
                if(left < array[j]) {
                    left = array[j];
                }
            }
            //rightLoop
            for(int k = i; k < array.length; k++) {
                if(right < array[k]) {
                    right = array[k];
                }
            }
            //calculate water level
            collectedWater += ((Math.min(left, right)) - array[i]);
        }
        return collectedWater;
    }

    public static int RainWaterCalculator(int[] array) {
        int n = array.length;
        //auxiliary arrays
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        if(n < 3) {
            return -1;
        }

        //leftMax boundary loop
        leftMax[0] = array[0];
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], array[i]);
        }

        //rightMax boundary loop
        rightMax[n - 1] = array[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], array[i]);
        }

        //calculating rainwater
        int collectedWater = 0;
        for(int i = 1; i < n - 1; i++) {
            collectedWater += (Math.min(rightMax[i], leftMax[i]) - array[i]);
        }
        return collectedWater;
    }

    public static int rainWaterCalculatorHWSOL(int[] array) {
        int n = array.length;
        if (n < 3) {
            return -1;
        }
        int left = 0, right = n - 1; 
        int maxRight = array[right], maxLeft = array[left];
        int totalWater = 0;

        while ( left < right){
            if (maxLeft <= maxRight){
                left++;
                maxLeft = Math.max(maxLeft,array[left]);
                totalWater += maxLeft - array[left];
            }
            else {
                right--;
                maxRight = Math.max(maxRight, array[right]);
                totalWater += maxRight - array[right];
            }
        }
        return totalWater;
    }

    public static void stockCalculator(int[] array) {
        int n = array.length;

        int difference = Integer.MIN_VALUE;
        int diff;
        if (n < 2){
            System.out.println("not enough data");
            return;
        }
        //creating a left min array
        int[] leftMin = new int[n];
        
        leftMin[0] = array[0];
        for(int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], array[i]);
            if(Math.min(leftMin[i - 1], array[i]) == array[i]) {
            }
        }
        //creating a right max array
        int[] rightMax = new int[n];
        rightMax[n - 1] = array[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], array[i]);
            if(Math.max(rightMax[i + 1], array[i]) == array[i]) {
            }
        }


        //finding the difference for that day
        for(int i = 0; i < n; i++) { 
            diff = (rightMax[i] - leftMin[i]);
            difference = Math.max(diff, difference);
        }

        //choosing the max differenct
        System.out.println("best profit : " + difference);
        return;
    }


    public static int stockCalculator2(int[] array) {
        int n = array.length;
        int buyPrice = array[0];
        int profit = 0;
        int maxProfit = 0;
        for(int i = 1; i < n; i++) {
            int sellPrice = array[i];
            //calculatiing profit
            if(buyPrice > sellPrice) {
                buyPrice = sellPrice;
            }
            else {
                profit = (sellPrice - buyPrice);
            }
            if( profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }

    public static boolean dupeDetecter(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for( int j = i + 1; j < array.length; j++) {
                if(array[i] == array[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int rotatedBinarySearch( int array[], int value) {
        int start = 0;
        int end = array.length - 1;
        while( start <= end) {
            int mid = start +(end - start) / 2;
            if (value == array[mid]) {
                return mid;
            }
            //see if left half is sorted
            if (array[mid] >= array[start]) {
                if(value < array[mid] && value >= array[start]) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
            //or else right half is sorted
            else {
                if(value > array[mid] && value <= array[end]) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int rainWaterCalculatorHW(int array[]) {
        //auxiliary array consept
        int n = array.length;
        if (n < 3) {
            return -1;
        }

        //calculating left maximum array
        int leftMax[] = new int[n];
        leftMax[0] = array[0];
        for(int i = 1; i < n ; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], array[i]);
        }

        //calculating the right maxximum array
        int rightMax[] = new int[n];
        rightMax[n - 1] = array[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], array[i]);
        }

        //calculating the water level
        int collectedWater = 0;
        for (int i = 1 ; i < n - 1; i++){
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            collectedWater+= waterLevel - array[i];
        }
        return collectedWater;
    }

    public static void tripletSum (int array[]) {
        int n = array.length;
        boolean found = false;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    if(array[i] + array[j] + array[k] == 0) {
                        System.out.print(array[i] + "," +array[j] + "," + array[k] + "  ");
                        found =true;
                    }
                }
            }
        }

        if(found == false) {
            System.out.println("no triplet found");
        }
    }    

    public static void tripletSumNoDupes(int array[]) {
        int n = array.length;
        Arrays.sort(array);
        for(int i = 0; i < n - 2; i++) {
            if( i> 0 && array[i] == array[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;

            while(left < right) {
                int sum = array[i] + array[left] + array[right];
                if( sum == 0) {
                    System.out.println(array[i] + "," + array[left] + "," + array[right]);
                    left++;
                    right--;

                    //skip the duplicate values
                    while(left < right && array[left] == array[left - 1]) {
                        left++;
                    }
                    while(left < right && array[right] == array[right + 1]) {
                        right--;
                    }
                }
                else if (sum > 0) {
                    right--;

                    //skip the duplicate values
                    while(left < right && array[right] == array[right + 1]) {
                        right--;
                    }
                }
                else {
                    left++;

                    //skip the duplicate values
                    while(left < right && array[left] == array[left - 1]) {
                        left++;
                    }
                }
            }
        }
    }

    public static int RotatebinarySearch (int[] array, int target) {
        int n = array.length;
        int start = 0, end = n - 1, mid;
        while(start <= end) {
            mid = start + ((end - start) / 2 );
            if(array[mid] == target) {
                return mid;
            }
            if (array[start] <= array[mid]){
                if (target < array [mid] && target >= array[start]) {
                    end = mid -1;
                }
                else {
                    start = mid + 1;
                }
            }
            else{
                if (target > array[mid] && target <= array[end]) {
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }



    public static void main (String[] args) {
        //-33,-17, -15, -12, -3, 0, 
        int Marks[] = {-1, 0, 1, 2, -1, -4};
        int rainwater[] = {1, 0, -10, -7};
        System.out.println(rainWaterCalculatorHWSOL(rainwater));
        tripletSumNoDupes(Marks);
    }
}
