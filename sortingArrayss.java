import java.util.*;
public class sortingArrayss {

    //inbuilt sort functions
    //Time complexity : O(n log n) 
    public static void in_built_sorting_functions(int[] array){

        //need to import java.util.Arrays;

            //normal ascending order sort:

                //Arrays.sort(array);

            //to sort a part of an array:
                //Arrays.sort(array, start, end)

                //Arrays.sort(array, starting_position, non-inclusive_position);

                //Arrays.sort(array, start index, (non- inclusive) end index - 1)



        //need to import java.util.Collections;
        //works only on Integer[] array ✅ and NOT on int[] array ❌
        //Collections.reverseOrder() : comparators, are functions in java that define logic

            //decending order sort

                //Arrays.sort(array, Collections.reverseOrder()); 
            
            //to sort a part of an array in decending order

                //Arrays.sort(array, start, end, Collections.reverseOrder())

                //Arrays.sort(array, starting_position, non-inclusive_position, Collections.reverseOrder());

                //Arrays.sort(array, start index, (non- inclusive) end index - 1, Collections.reverseOrder())

    }

    //self made functions/Meathods

    //print an array
    public static void printArray (int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        //line statement
        System.out.println();
    }

    //print an String array
    public static void printArray (String[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        //line statement
        System.out.println();
    }

    //how to make a frequency array
    public static void frequencyArray(int[] array) {

        //finding the array size of the new frequency array
        int max = Integer.MIN_VALUE;
        // for(int i : array) = for(int i = 0; i < array.length; i++) 
        for(int i : array) {
            if(i > max){
                max = i;
            }
        }

        //creating a new frequency array
        int[] freq = new int[max + 1];

        //tally in the frequency table
        for(int i : array) {
            freq[i]++;
        }

        printArray(freq);
    }

    //Bubble sort
    //Time complexity : O(n²) | Space complexity : O(1)
    public static void bubbleSort(int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++) {
            int swaps = 0;
            for(int j = 0; j < n - 1 - i; j++) {
                if(array[j] > array[j + 1]){
                    //swap
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;

                    //total swaps
                    swaps++;
                }
            }
            //break the loop if no swaps happens - the array is aleady sorted
            //best case - time complexity : O(n)
            if(swaps == 0) {
                return;
            }
        }
    }

    //selection sort
    //Time complexity : O(n²) | Space complexity : O(1)
    public static void selectionSort (int[] array) {
        int n = array.length;
        
        for(int i = 0; i < n - 1; i++) {
            int minValue = i;
            for(int j = i + 1; j < n; j++) {
                if(array[minValue] > array[j]) {
                    minValue = j;
                }
            }
            //swap if index i is not the smallest value
            if(minValue != i) {
                int temp = array[minValue];
                array[minValue] = array[i];
                array[i] = temp;
            }
        }
    }

    //insertion sort
    //Time complexity : O(n²) | Space complexity : O(1)
    public static void insertionSort (int [] array){
        int n = array.length;
        
        for(int i = 1; i < n; i++) {
            int current = i;
            int temp = array[current];
            int previous = i - 1;

            //while loop will never run if the array is in ascending order
            //which is best case time complexity : O(n)
            while (previous >= 0 && array[previous] > temp){
                 array[previous + 1] = array[previous];
                 previous--;
            }
            array[previous + 1] = temp;
        }
    }

    //counting sort
    /*only applicable for positive values because frequency array cannot be made with -ve values
    if you want to make it then you need to make a seperate array for negative values.
    treat them as positive values and then do similar process to a normal counting sort.
    at last step make them in decending order.*/
    //Time complexity : O(n + range) | Space complexity : O(range)
    public static void countingSort(int[] array) {
        
        //finding the array size of the new frequency array
        int max = Integer.MIN_VALUE;

        // for(int i : array) = for(int i = 0; i < array.length; i++) 
        for(int i : array) {
            max = Math.max(max, i);
        }

        //creating a new frequency array
        int[] freq = new int[max + 1];

        //tally in the frequency table
        for(int i : array) {
            freq[i]++;
        }

        //making a count to properly place values in the original array 
        int j = 0;

        //updating the array
        int m = freq.length;
        for(int i = 0; i < m; i++) {
            while(freq[i] > 0) {
                array[j] = i;
                freq[i]--;
                j++;
            }
        }
    }

    //merge sort
    //Time complexity : O(n log n) | Space complexity : O(n) | recursion stack space : O(log n)
    public static void mergeSort( int[] array, int si, int ei) {
        if(si >= ei) {
            return;
        }
        //work
        int mid = si + (ei - si) / 2;
        mergeSort(array, si, mid);
        mergeSort(array, (mid + 1), ei);
        merge(array, si, ei, mid);
    }

    public static void merge (int[] array, int si, int ei, int mid) {
        int[] temp = new int[ei - si + 1];
        int i = si;
        int j = mid + 1;
        int k = 0;
        while( i <= mid && j <= ei) {
            if(array[i] < array[j]) {
                temp[k++] = array[i++];
            }
            else{
                temp[k++] = array[j++];
            }
        }

        //for left over
        while(i <= mid){
            temp[k++] = array[i++];
        }
        while(j <= ei) {
            temp[k++] = array[j++]; 
        }

        for(k = 0, i = si; k < temp.length; k++, i++) {
            array[i] = temp[k];
        }
    }

    //quick sort
    //Time complexity : O(n log n)->(Average) | Space complexity : O(1) | recursion stack space : O(log n)->(Average)
    //worst time complexity : O(n²) | worst recursion stack space : O(n)
    public static void quickSort(int[] array, int si, int ei) {
        if(ei <= si) {
            return;
        }
        int pivot = array[ei];
        int j = si; 
        for(int i = si; i < ei; i++) {
            if(array[i] <= pivot) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                j++;
            }
        }
        int temp = array[j];
        array[j] = array[ei];
        array[ei] = temp;
        quickSort(array, si, j-1);
        quickSort(array, j+1, ei);
    }

    //HOMEWORK

    //bubble sort
    public static void bubbleSortHW(int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++){
            int swapCount = 0;
            for(int j = i + 1; j < n; j++) {
                if(array[i] > array[j]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                    swapCount++;
                }
            }
            if(swapCount == 0) {
                return;
            }
        }
    }

    //selection sort
    public static void selectionSortHW (int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++){
            int minValue = i;
            for(int j = i + 1; j < n; j++) {
                if(array[minValue] > array[j]) {
                    minValue = j;
                }
            }
            //swap
            if(minValue != i) {
                int temp = array[minValue];
                array[minValue] = array[i];
                array[i] =temp;
            }
        }
    }

    //insersion sort
    public static void insertionSortHW(int[] array) {
        int n = array.length;

        for(int i = 1; i < n; i++) {
            int current = i;
            int prev = i - 1;
            int temp = array[current];
            while(prev >= 0 && array[prev] > temp) {
                array[prev + 1] = array[prev];
                prev--;
            }
            array[prev + 1] = temp;
        }
    }

    //counting sort
    public static void countingSortHW (int[] array){
        int maxValue = Integer.MIN_VALUE;

        for(int i : array) {
            maxValue = Math.max(maxValue, i);
        }

        int[] freq = new int[maxValue + 1];

        for(int i : array) {
            freq[i]++;
        }

        int j = 0;

        for(int i = 0; i < freq.length; i++) {
            while(freq[i] > 0) {
                array[j] = i;
                j++;
                freq[i]--;
            }
        }
    }

    //merge sort
    public static void mergeSortHW(int[] array, int si, int ei) {
        if(si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSortHW(array, si, mid);
        mergeSortHW(array, mid + 1, ei);
        mergeHW(array, si, mid, ei);
    }

    public static void mergeHW(int[] array, int si, int mid, int ei) {
        int i = si, j = mid + 1, k = 0;
        int[] temp = new int[ei - si + 1];
        while(i <= mid && j <= ei) {
            if(array[i] <= array[j]) {
                temp[k++] = array[i++];
            }
            else{
                temp[k++] = array[j++];
            }
        }
        while(i <= mid) {
            temp[k++] = array[i++];
        }
        while(j <= ei) {
            temp[k++] = array[j++];
        }
        for(i = si, k = 0; k < temp.length; k++,i++) {
            array[i] = temp[k];
        }
    }

    //quick sort
    public static void quickSortHW (int[] arr, int si, int ei) {
        if(si >= ei){
            return;
        }
        int j = si;
        int pivot = arr[ei];
        for(int i = si; i < ei; i++) {
            if(arr[i] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        int temp = arr[j];
        arr[j] = arr[ei];
        arr[ei] = temp;
        quickSortHW(arr, si, j-1);
        quickSortHW(arr, j+1, ei);
    }

    //merge sort for strings
    public static void mergeSort (String[] words, int si, int ei) {
        if(si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort(words, si, mid);
        mergeSort(words, mid+1, ei);
        merge(words, si, ei, mid);
    }

    public static void merge (String[] words, int si, int ei, int mid) {
        int i = si, j = mid+1,k = 0;
        String[] temp = new String[ei - si + 1];
        while(i <= mid && j <= ei) {
            if(words[i].compareToIgnoreCase(words[j]) < 0) {
                temp[k++] = words[i++]; // for ascending order
                //temp[k++] = words[j++]; //for decending order
            }
            else {
                temp[k++] = words[j++]; //for ascending order
                //temp[k++] = words[i++]; //for decending order
            }
        }
        while (i <= mid) {
            temp[k++] = words[i++];
        }
        while(j <= ei) {
            temp[k++] = words[j++];
        }
        for(k = 0, i = si; k < temp.length; k++,i++){
            words[i] = temp[k];
        }
    }

    //practice
    //insertion sort reverse
    public static void insertionSortPractice(int[] array) {
        int n = array.length;

        for(int i = 1; i < n; i++){
            int current = array[i];
            int prev = i - 1;

            while(prev >= 0 && array[prev] < current) {
                array[prev + 1] = array[prev];
                prev--;
            }
            
        array[prev + 1] = current;
        }
    }

    //bubble sort reverse
    public static void bubbleSortPractice(int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++){
            int swapCount = 0;
            for(int j = i + 1; j < n; j++) {
                if(array[i] < array[j]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                    swapCount++;
                }
            }
            if(swapCount == 0) {
                return;
            }
        }
    }

    //selection sort
    public static void selectionSortPractice (int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++){
            int maxValue = i;
            for(int j = i + 1; j < n; j++) {
                if(array[maxValue] < array[j]) {
                    maxValue = j;
                }
            }
            //swap
            if(maxValue != i) {
                int temp = array[maxValue];
                array[maxValue] = array[i];
                array[i] =temp;
            }
        }
    }

    //counting sort revers
    public static void countingSortPractice (int[] array){
        int maxValue = Integer.MIN_VALUE;

        for(int i : array) {
            maxValue = Math.max(maxValue, i);
        }

        int[] freq = new int[maxValue + 1];

        for(int i : array) {
            freq[i]++;
        }

        int j = 0;

        for(int i = freq.length - 1; i >= 0; i--) {
            while(freq[i] > 0) {
                array[j] = i;
                j++;
                freq[i]--;
            }
        }
    }

    //merge sort
    public static void mergeSortPractice(int[] arr, int si, int ei) {
        if(si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSortPractice(arr, si, mid);
        mergeSortPractice(arr, mid+1, ei);
        mergePractice(arr, si, mid, ei);
    }
    public static void mergePractice(int[] arr, int si, int mid, int ei) {
        int[] temp = new int[ei - si + 1];
        int i = si, k = 0, j = mid+1;
        while(i <= mid && j <= ei){
            if(arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= mid){
            temp[k++] = arr[i++];
        }
        while(j <= ei){
            temp[k++] = arr[j++];
        }
        for(i = si, k = 0; k < temp.length; k++,i++) {
            arr[i] = temp[k];
        }
    }


    //2D arrays
    //print 2d matrix
    public static void print(int[][] array){
        int n = array.length, m = array[0].length;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(array[i][j] + " ");
            } 
            System.out.println();
        }    
    }

    //linear search
    //Time complexity : O(n²)
    public static boolean search(int[][] array, int target) {
        int n = array.length, m = array[0].length;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(array[i][j] == target) {
                    System.out.println("found at index : " + i + "," + j);
                    return true;
                }
            }
        }
        System.out.println("key not found");
        return false;
    }

    //finding maximum value in a matrix array
    public static int maxValue(int[][] array) {
        int n = array.length, m = array[0].length;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                maxValue = Math.max(maxValue, array[i][j]);
            }
        }
        return maxValue;
    }

    //finding minimum value in a matrix array
    public static int minValue(int[][] array) {
        int n = array.length, m = array[0].length;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                minValue = Math.min(minValue, array[i][j]);
            }
        }
        return minValue;
    }

    //print spiral matrix
    //my version. fails for edge cases!!!!
    public static void spiralPrintSelf(int[][] array){
        int n = array.length, m = array[0].length;

        int startRow = 0 , endRow = n - 1; //rows
        int startCol = 0 , endCol = m - 1; //columns

        while(startRow <= endRow && startCol <= endCol) {
            //top boundary
            //only column changes
            for(int j = startCol; j <= endCol; j++) {
                System.out.print(array[startRow][j]);
            }
            startRow++;
            //only row changes
            for(int i = startRow; i <= endRow; i++) {
                System.out.print(array[i][endCol]);
            }
            endCol--;
            //only column changes (in reverse)
            for(int j = endCol; j >= startCol; j--) {
                System.out.print(array[endRow][j]);
            }
            endRow--;
            //only row changes (in reverse)
            for(int i = endRow; i >= startRow; i--) {
                System.out.print(array[i][startCol]);
            }
            startCol++;
        }
            
    }

    public static void spiralPrintSelfCorrected(int[][] array){
        int n = array.length, m = array[0].length;

        int startRow = 0 , endRow = n - 1; //rows
        int startCol = 0 , endCol = m - 1; //columns

        while(startRow <= endRow && startCol <= endCol) {
            //top boundary
            //only column changes
            for(int j = startCol; j <= endCol; j++) {
                System.out.print(array[startRow][j]);
            }
            startRow++;
            
            //right boundary
            //only row changes
            for(int i = startRow; i <= endRow; i++) {
                System.out.print(array[i][endCol]);
            }
            endCol--;

            //bottom boundary
            //only column changes (in reverse)
            if(startRow <= endRow) {
                for(int j = endCol; j >= startCol; j--) {
                    System.out.print(array[endRow][j]);
                }
                endRow--;
            }
            
            //left boundary
            //only row changes (in reverse)
            if(startCol <= endCol) {
                for(int i = endRow; i >= startRow; i--) {
                    System.out.print(array[i][startCol]);
                }
                startCol++;
            }
        }
    }

    //shradha mam's code
    public static void spiralPrint(int[][] array){
        int n = array.length, m = array[0].length;

        int startRow = 0 , endRow = n - 1; //rows
        int startCol = 0 , endCol = m - 1; //columns

        while(startRow <= endRow && startCol <= endCol) {
            //top
            for(int j = startCol; j <= endCol; j++) {
                System.out.print(array[startRow][j]);
            }
            //right
            for(int i = startRow + 1; i <= endRow; i++) {
                System.out.print(array[i][endCol]);
            }
            //(in reverse)
            //bottom
            for(int j = endCol - 1; j >= startCol; j--) {
                if (startRow == endRow) {
                    break;
                }
                System.out.print(array[endRow][j]);
            }
            //(in reverse)
            //left
            for(int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol) {
                    break;
                }
                System.out.print(array[i][startCol]);
            }
            startRow++;
            endCol--;
            endRow--;
            startCol++;
        }
        return;  
    }

    //diagonal sum
    //Time complexity : O(n²)
    public static int diagonalSumWrong(int[][] array) {
        int n = array.length, m = array[0].length;
        if(n != m) {
            return -1;
        }
        int diagonalSum = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j || (i + j) == n - 1) {
                    diagonalSum += array[i][j];
                }
            }
        }
        if(n % 2 == 1) {
            diagonalSum -= array[n/2][n/2];
        }
        return diagonalSum;
    }

    //diagonal sum
    //Time complexity : O(n) 
    public static int daigonalSum(int[][] array) {
        int n = array.length;
        if(n != array[0].length){
            return Integer.MIN_VALUE;
        }

        int diagonalSum = 0;
        for(int i = 0; i < n; i++) {
            diagonalSum += array[i][i];
            if (i != n - i - 1) {
                diagonalSum += array[i][n - 1 - i];
            }
        }
        return diagonalSum;
    } 

    //my attempt at a binary search in a matrix
    //only works IF the matrix is "globally sorted"
    public static void binarySearch2DSelf(int[][] array, int target) {
        int n = array.length, m = array[0].length;

        //first element coordinate
        int fr = 0;
        int fc = 0;

        //last element coordinate
        int lr = n - 1;
        int lc = m - 1;

        while(fr < lr) {
            int midr = fr + (lr - fr)/2;
            int midc = m/2;
            if(array[midr][midc] == target) {
                System.out.print(midr + " " + midc);
                return;
            }
            else if(array[midr][midc] < target) {
                fr = midr;
            }
            else{
                lr = midr;
            }
        }
        while(fc <= lc) { 
            int midr = fr;
            int midc = fc + (lc - fc)/2;
            if(array[midr][midc] == target) {
                System.out.print(midr + " " + midc);
                return;
            }
            else if(array[midr][midc] < target) {
                fc = midc;
            }
            else{
                lc = midc;
            }
        }
        System.out.println("target not found");
        return;
    }

    //staircase search
    //Time complexity : O(n + m) |space comlexity :
    //works for   
    public static boolean staircaseSearch(int[][] array, int target) {
        int n = array.length, m = array[0].length;
        
        int row = n - 1, column = 0; 

        while(row >= 0 && column < m) {
            if(target == array[row][column]) {
                System.out.println(row + " " + column);
                return true;
            }
            else if( target > array[row][column]) {
                column++;
            }
            else {
                row--;
            }
        }
        System.out.println("key Not found");
        return false;
    }

    //homework
    //frequency calculator for any number in an array

    public static int frequencyCalculator(int[][] array, int number) {
        int n = array.length, m = array[0].length;

        int freq = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(array[i][j] == number) {
                    freq++;
                }
            } 
        }
        return freq;
    }

    public static int sumOfRow (int[][] array, int row) {
        int n = array.length, m = array[0].length;
        if(row < 0 || row >n - 1) {
            return -1;
        }
        int sum = 0;
        for(int j = 0; j < m; j++) {
            sum += array[row][j];
        }
        return sum;
    }

    public static void transpose(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        
        int[][]transposeMatrix = new int[m][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                transposeMatrix[j][i] = matrix[i][j]; 
            } 
        }
        print(transposeMatrix);
        return;
    }
    
    //proper binary search for global sorted 2d array
    public static boolean binarySearch2D(int[][] matrix,int target) {
        int n = matrix.length, m = matrix[0].length;
        int start = 0;
        int end = n*m - 1;
        int row, column;

        while(start <= end){
            int mid = start + ( end - start) / 2;
            row = mid / m; 
            column = mid % m;

            if(target == matrix[row][column]) {
                System.out.println("found at : " + row + ", " + column);
                return true;
            }
            else if(target < matrix[row][column]) {
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        System.out.println("key not found");
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrixeven = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] matrixodd = {
            {1,2,3},
            {4,5,6},
            {7,8,5}};

        int[][] matrixrect = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12}};
        int[][] edgematrixr = {{1,2,3}};
        int[][] edgematrixc = {{1},{2},{3}};
        int[][] single = {{1}};
        int[] test  = {1,8,3,6,0,1,5,8,2,9,6,4,8,7};
        String[] testS = {"yashik", "dhruv", "sunita","mahesh"};

        quickSortHW(test, 0, (test.length - 1));
        printArray(test);
        mergeSort(testS, 0, testS.length-1 );
        printArray(testS);
        
    }
}
