import java.util.Arrays;

public class RecursionPractice {

    //recursion
        //trust! that the base will be automatically be calculated

    /*Stack overflow : if a recursion gets called too many times it ends up overslowing the meamory stack.
        OR it if it have too many parameters and each parameter takes too much meamory
        then it gives an error called stack overflow. */
        // happens if base case is absent 
    
    //fuctions: 

    //factorial
    // f(n) = n * f(n-1) | f(0) = 1
    public static int factorial (int n) {
        if(n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    //printing numbers in decreasing order
    // n , f(n-1) | (till 1)
    public static void decreasingNumbers (int n) {
        if(n == 1) {
            System.out.println("1");
            return;
        }
        System.out.print(n + ", ");
        decreasingNumbers(n - 1);
        return;//NOT useless as it gets called
    }

    //printing numbers in increasing order
    // f(n-1) , n | (till 1)
    public static void increasingNumbers (int n) {
        if(n == 1) {
            System.out.print(1);
            return;
        }
        increasingNumbers(n - 1);
        System.out.print(", " + n);
        return;
    }

    //sum of N natural numbers
    // f(n) = f(n-1) + f(n) | f(1) = 1
    public static int sumNatural (int n) {
        if(n == 1) {
            return 1;
        }
        return sumNatural(n - 1) + n;
    }

    //calculate nth term of fibbonachi series
    //0, 1, 1, 2, 3, 5, 8, 13, 21....
    //f(n) =  f(n-1) + f(n - 2) | f(0) = 0, f(1) = 1
    //Time complexity :  O(2ⁿ) | Space complexit : O(n)
    public static int fibonacci(int nth) {
        if(nth == 0 || nth == 1) {
            return nth; //nth is the same as it;s value
        }
        return fibonacci(nth - 1) + fibonacci(nth - 2);
    }

    //making an easy sort check hard with recursion, checking true or false
    public static boolean isSorted (int[] array, int i ) {
        if(i == array.length - 1) {
            return true;
        }
        if(array[i] > array[i + 1]) {
            return false;
        }
        return isSorted(array, i + 1);
    }

    //function to find the first occurance of an element in an array
    public static int firstOccurance (int[] array, int target, int startingIndex) {
        if(startingIndex > array.length - 1) {
            return -1;
        }
        if(array[startingIndex] == target) {
            return startingIndex;
        }
        return firstOccurance(array, target, startingIndex + 1);
    }

    //function to find the first occurance of an element in an array
    public static int lastOccurance (int[] array, int target,int lastIndex) {
        if(lastIndex < 0) {
            return -1;
        }
        if(array[lastIndex] == target) {
            return lastIndex;
        }
        return lastOccurance(array, target,lastIndex - 1);
    }

    //really confusing one
    public static int lastOccurance1 (int[] array, int target, int i){
        if(i == array.length) {
            return -1;
        }
        int isFound = lastOccurance1(array, target, i + 1);
        if(isFound == -1 && array[i] == target) {
            return i;
        }
        return isFound;
    }

    //power function x^n
    //f(x^n) = f(x) * f(x^(n-1))
    public static int power(int base, int power) {
        if(power == 0) {
            return 1;
        }
        return base * power(base, power - 1);
    }

    //power function optimized 
    public static int powerOptimized (int base, int power) {
        if (power == 0) {
            return 1;
        }
        int value = powerOptimized(base, power/2);
        if((power & 1) == 0) {
            return value * value;
        }
        return base * value * value;
    }
    
    //tiling problem 
    // as we cheched the possible combinations for each value of n, we noticed that the combinations increases with n the same was fibonacchi series does!
    //f(x) = f(x-1) + f(x-2) | f(1) = 1, f(2) = 2
    public static int tilingCombination (int n) {
        if(n == 0) {
            return 1;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return tilingCombination(n-1) + tilingCombination(n-2);
    }

    //2 * n tiles
    public static int tilesCombination2 (int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        int vertical = tilesCombination2(n-1);

        int horizontal = tilesCombination2(n-2);

        int totalWays = horizontal + vertical;
        return totalWays;
    }

    //4*n tiles
    public static int tilesCombination4 (int n) {
        if(n == 0 || n == 1 || n == 2 || n == 3){
            return 1;
        }
        int vertical = tilesCombination4(n - 1);
        int horizontal = tilesCombination4(n - 4);
        int totalWays = vertical + horizontal;
        return totalWays;
    }

    //practice for String type questions
    public static String noDupes (String word) {
        int n = word.length();
        StringBuilder newWord = new StringBuilder("");
        for(int i = 0; i < n; i++) {
            boolean isNotPresent = true;
            for(int j = 0; j < i; j++) {
                if(word.charAt(i) == word.charAt(j)) {
                    isNotPresent = false;
                }
            }
            if (isNotPresent) {
                newWord.append(word.charAt(i));
            }
        }
        return newWord.toString();
    }
    //practice for string type question more optimized
    public static String noDupeFreq (String word) {
        StringBuilder newWord = new StringBuilder("");
        boolean[] freq = new boolean[26];
        int n = word.length();
        for(int i = 0; i < n; i++) {
            freq[word.charAt(i) - 'a'] = true;
        }
        for(int i = 0; i < n; i++) {
            if(freq[word.charAt(i) - 'a'] == true){
                newWord.append(word.charAt(i));
                freq[word.charAt(i) - 'a'] = false;
            }
        }
        return newWord.toString();
    }

    //remove double from a string using recursion!!!
    public static void noDupes1 (String word, int ind, boolean[] checker, StringBuilder newWord) {
        if(ind == word.length()) {
            System.out.println(newWord);
            return;
        }
        if(checker[word.charAt(ind) - 'a'] == false) {
            newWord.append(word.charAt(ind));
            checker[word.charAt(ind) - 'a'] = true;
        }
        noDupes1(word, ind + 1, checker, newWord);
        return;
    }

    //repetition of the last one!
    public static void noDupes2 (String word, int ind, boolean[] checker, StringBuilder newWord) {
        if(ind == word.length()) {
            System.out.println(newWord);
            return;
        }
        if(checker[word.charAt(ind) - 'a'] == true) {
            noDupes2(word, ind + 1, checker, newWord);
        }
        else {
            checker[word.charAt(ind) - 'a'] = true;
            noDupes2(word, ind + 1, checker, newWord.append(word.charAt(ind)));
        }
    }

    //wrong 
    public static void noConssecutive (String word, int i, int[] freq) {
        if(i == word.length()) {
            return;
        }
        freq[word.charAt(i) - 'a']++;
        noConssecutive(word, i+1, freq);
        if(freq[word.charAt(i) - 'a'] == 1) {
            System.out.print(word.charAt(i));
        } 
        return;
    }

    //right!!
    public static void noConssecutive (String word, int i) {
        //base case
        if(i == word.length()) {
            return;
        }
        //last case
        if(i == word.length() - 1) { //shoud use AND here!!
            if (word.charAt(i) != word.charAt(i-1)) {
                System.out.print(word.charAt(i)); //(1)
            }
        }
        //start case 
        else if(i == 0) {  //shoud use AND here!!
            if (word.charAt(i) != word.charAt(i+1)) {
                System.out.print(word.charAt(i)); //(2) :- 1,2,3 are the same conditions so use zero!!
            }
        }
        //next is different
        else{
            if ((word.charAt(i) != word.charAt(i+1)) && (word.charAt(i) != word.charAt(i-1))) {
                System.out.print(word.charAt(i)); //(3)
            }
        }
        //next is same
        noConssecutive(word, i + 1);
        return;
    }

    //friend pairing problem
    public static int friends (int n) {
        if(n == 1 || n == 0) {
            return 1;
        }
        //there are two choices for each person in the group:
        //they can either be friends pairs
        int pairs = (n - 1) * friends(n - 2);
        //they can either stay single
        int single = friends(n - 1);
        //total combinations of ways they can stay single or friends will be : friends + sin7890m;8[qgle
        int possibleCombinations = pairs + single;
        return possibleCombinations;
    }

    //print all binary strings of size n without consecutive ones
    //WORK IS PROGRESS
    public static void printBinary(int n, int last, StringBuilder srt) {
        if(n == 0) {
            System.out.println(srt);
            return;
        }
        if(last == 0) {
            printBinary(n-1, 1, srt.append("1"));
        }
        printBinary(n-1, 0, srt.append("0"));
    }
    //homework
    //print all indexes of a key in an array
    public static void printIndex (int[] array, int target, int i) {
        if(i == array.length) {
            return;
        }
        //work
        if(array[i] == target) {
            System.out.print(i + " ");
        }
        printIndex(array, target, i+1);
    }

    //number in words
    public static String numberInWord (int number, int i, StringBuilder printNum) {
        String num = String.valueOf(number);
        if(i == num.length()) {
            return printNum.toString();
        }
        //kaam
        if (num.charAt(i) == '0') {
            return numberInWord(number, i + 1, printNum.append("zero ") );
        }
        else if (num.charAt(i) == '1') {
            return numberInWord(number, i + 1, printNum.append("one ") );
        }
        else if (num.charAt(i) == '2') {
            return numberInWord(number, i + 1, printNum.append("two ") );
        }
        else if (num.charAt(i) == '3') {
            return numberInWord(number, i + 1, printNum.append("three ") );
        }
        else if (num.charAt(i) == '4') {
            return numberInWord(number, i + 1, printNum.append("four ") );
        }
        else if (num.charAt(i) == '5') {
            return numberInWord(number, i + 1, printNum.append("five ") );
        }
        else if (num.charAt(i) == '6') {
            return numberInWord(number, i + 1, printNum.append("six ") );
        }
        else if (num.charAt(i) == '7') {
            return numberInWord(number, i + 1, printNum.append("seven ") );
        }
        else if (num.charAt(i) == '8') {
            return numberInWord(number, i + 1, printNum.append("eight ") );
        }
        else{
            return numberInWord(number, i + 1, printNum.append("nine ") );
        }
    }
    public class NumberToWords {
        static String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        public static String numtowords(int number, int i, StringBuilder printNum) {
            String num = String.valueOf(number);
            if(i == num.length()) {
                return printNum.toString();
            }
            return numtowords(number, i+1, printNum.append(words[num.charAt(i) - '0'] + " "));
        }
    }

    //length of a string
    public static int stringLength (String word) {
        if(word == ""){
            return 0;
        }
        return 1 + stringLength (word.substring(1));
    }

    public static void practice (String word, int[] arr, int i) {
        if(i == word.length()) {
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] != 0) {
                    System.out.println((char)(j + 'a') + " : " + arr[j]);
                }
            }
            return;
        }
        arr[word.charAt(i) - 'a']++;
        practice(word, arr, i+1);
    }

    public static int practice1 (String word, int i, int[] arr) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j > 1) {
                    count++;
                }
            }
            return count;
        }
        arr[word.charAt(i) - 'a']++;
        return practice1(word, i+1, arr);
    }

    public static void practice3 (String word, int i, int[] arr) {
        if(i == word.length()){
            int maxValue = Integer.MIN_VALUE;
            for(int j : arr) {
                maxValue = Math.max(maxValue, j);
            }
            char maxChar = 'a';
            for(int j = 0; j < arr.length; j++) {
                if(maxValue == arr[j]){
                    maxChar = (char)(j + 'a');
                }
            }
            System.out.println(maxChar + " : " + maxValue);
            return;
        }
        arr[word.charAt(i) - 'a']++;
        practice3(word, i+1, arr);
    }

    public static boolean practice4 (String word1, String word2, int[] arr, int i) {
        if(word1.length() != word2.length()){
            return false;
        }
        if(i == word1.length()) {
            for(int j : arr) {
                if(j != 0) {
                    return false;
                }
            }
            return true;
        }
        arr[word1.charAt(i) - 'a']++;
        arr[word2.charAt(i) - 'a']--;
        return practice4(word1, word2, arr, i+1);
    }

    public static void practice5 (String word, int[] arr, int i) {
        if(i == word.length()) {
            //to print in order of the given word
            for(int j = 0; j < word.length(); j++) {
                if (arr[word.charAt(j) - 'a'] == 1) {
                    System.out.println(word.charAt(j));
                }
            }
            //to print in alphabetical order
            // for(int j = 1; j < arr.length; j++) {
            //     if(arr[j] == 1){
            //         System.out.println((char) (j+'a'));
            //     }
            // }
            return;
        }
        arr[word.charAt(i) - 'a']++;
        practice5(word, arr, i+1);
    }

    public static int practice6 (String word, int[] arr, int i) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j != 0 && j % 2 == 0) {
                    count++;
                }
            }
            return count;
        }
        arr[word.charAt(i) - 'a']++;
        return practice6(word, arr, i+1);
    }

    public static int practice7 (String word, int[] arr, int i) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j > 1) {
                    count++;
                }
            }
            return count;
        }
        arr[word.charAt(i) - 'a']++;
        return practice7(word, arr, i+1);
    }

    public static boolean isPrime (int n) {
        if(n == 1) {
            return false;
        }
        if(n == 2) {
            return true;
        }
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void practice8 (String word, int[] arr, int i) {
        if(i == word.length()) {
            //alphabetical order
            // for(int j = 0; j < arr.length; j++) {
            //     if(arr[j] != 0 && isPrime(arr[j])){
            //         System.out.print((char)(j + 'a') );
            //     }
            // }
            //int order of the word
            for(int j = 0; j < word.length(); j++) {
                if(arr[word.charAt(j) - 'a'] != 0 && isPrime(arr[word.charAt(j) - 'a'])) {
                    System.out.print(word.charAt(j));
                    arr[word.charAt(j) - 'a'] = 0; //to avoid repetitions
                }
            }
            return;
        }
        arr[word.charAt(i) - 'a']++;
        practice8(word, arr, i+1);
    }

    //	Count pairs (i, j) such that word[i] == word[j] and i < j
    public static int practice9 (String word, int[] arr, int i) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j != 0) { //j != 0 is for code effeciency(hopefully?)
                    count += j * (j-1) / 2;
                }
            }
            return count;
        }
        arr[word.charAt(i) - 'a']++;
        return practice9(word, arr, i+1);
    }

    public static int practice10 (String word, int[] arr, int i) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j != 0) {
                    count += j + j * (j - 1) / 2;
                }
            }
            return count;
        }
        arr[word.charAt(i) - 'a']++;
        return practice10(word, arr, i+1);
    }

    public static void practice11 (String word, int i, String str) {
        //can't seem to figure out how to do it with a StringBuilder, i think there is a conflict when using the append function so had to switch to String for now
        if(i == word.length() - 1) {
            str += word.charAt(i);
            System.out.println(str);
            return;
        }
        if(word.charAt(i) != word.charAt(i+1)) {
            str += word.charAt(i);
        }
        practice11(word, i+1, str);
    }

    public static void practice12 (int n, int last, String str) {
        //like practice 11 can't figure outfor  StringBuilder
        if(n == 0) {
            System.out.println(str);
            return;
        }
        if(last == 0){
            practice12(n-1, 1, (str + "1"));
        }
        practice12(n-1, 0, (str + "0"));
    }

    public static boolean isPalindrome(String word) {
        for(int i = 0; i < word.length()/2; i++) {
            if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void practice13 (String word, int i, String str) {
        //could not figure this one out
        if(i == word.length()) {
            if(isPalindrome(str)) {
                System.out.println(str);
            }
            return;
        }
        practice13(word, i+1, (str + word.charAt(i)));
        practice13(word, i+1, str);
    }

    public static int practice14 (String word, int[] arr, int i) {
        if(i == word.length()) {
            int count = 0;
            for(int j : arr) {
                if(j % 2 == 1) {
                    count++;
                }
            }
            if(count == 0 || count == 1) {
                return 1;
            }
            else{
                return 0;
            }
        }
        arr[word.charAt(i) - 'a']++;
        return practice14(word, arr, i+1);
    }

    //next day
    public static void live (int[] arr, int i, int target) {
        if(i == arr.length) {
            return;
        }
        if(arr[i] == target) {
            System.out.println(i);
        }
        live(arr, i+1, target);
    }

    public static void printNumber (int num) {
        if(num == 0) {
            return;
        }
        int lastDig = num % 10;
        num /= 10;
        printNumber(num);
        System.out.println(NumberToWords.words[lastDig]);
    }

    //substring with same start and end
    public static void substringwithsamefrontandlast (String word, String newWord, int i) {
        if(i == word.length()) {
            if(newWord != "" && newWord.charAt(0) == newWord.charAt(newWord.length() - 1)) {
                System.out.println(newWord);
            }
            return;
        }
        substringwithsamefrontandlast(word, (newWord + word.charAt(i)), i + 1);
        substringwithsamefrontandlast(word, newWord, i + 1);
    }

    public static int conmigious (String word, int[] arr, int i) {
        if(i == word.length()) {
            int total = 0;
            for(int j : arr) {
                if(j != 0) {
                    total += j + (j * (j - 1) / 2);
                }
            }
            return total;
        }
        arr[word.charAt(i) - 'a']++;
        return conmigious(word, arr, i + 1);
    }

    public static int tower (int n) {
        if(n == 0) {
            return 0;
        }
        int half = tower(n - 1);
        return half + 1 + half;
    }

    public static void printTower (int n, String source, String helper, String destination) {
        if(n == 0) {
            return;
        }
        printTower(n - 1, source, destination, helper);
        System.out.println("put disk number : " +  n + " from " + source + " to " + destination);
        printTower(n - 1, helper, source, destination);
    }

    public static String reverse(String word, int i, String newWord) {
        if(i == word.length()) {
            return newWord;
        }
        return reverse(word, i + 1, newWord + word.charAt(word.length() - 1 - i));
    }

    public static void firstLastIndex (String word, char target, int i, int j) {
        if(i >= word.length() || j < 0 || i > j) {
            return;
        }
        if(word.charAt(i) == target && word.charAt(j) == target) {
            System.out.println(i + " " + j);
            return;
        }
        if (word.charAt(i) == target){
            firstLastIndex(word, target, i, j - 1);
        }else if (word.charAt(j) == target){
            firstLastIndex(word, target, i + 1, j);
        }
        else{
            firstLastIndex(word,target, i+1, j-1);
        }
    }
    
    public static int first = -1;
    public static int last = -1;
    public static void lastFirstIndex (String word, int i, int target) {
        if(i >= word.length()) {
            System.out.println(first + " " + last);
            return;
        }
        if(word.charAt(i) == target) {
            if(first == -1) {
                first = i;
            }
            else{
                last = i;
            }
        }
        lastFirstIndex(word, i+1, target);
    }

    public static int binarySearch ( int[] arr, int target, int si, int ei) {
        if(si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;
        if(target == arr[mid]) {
            return mid;
        }
        if(target > arr[mid]) {
            return binarySearch(arr, target, mid+1, ei);
        }else{
            return binarySearch(arr, target, si, mid-1);
        }
    }

    public static int rotatedBinarySearch (int[] array, int target, int si, int ei) {
        if(si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;
        if(target == array[mid]) {
            return mid;
        }
        if(array[si] < array[mid]) {
            if(target > array[si] && target < array[mid]) {
                return rotatedBinarySearch(array, target, si, mid-1);
            }
            else{
                return rotatedBinarySearch(array, target, mid+1, ei); 
            }
        }
        else{
            if(target < array[ei] && target > array[mid]) {
                return rotatedBinarySearch(array, target, mid+1, ei);
            }
            else{
                return rotatedBinarySearch(array, target, si, mid-1);
            }
        }
    }

    //homework
    public class MajorityNumberFinder {
        private int[] numberfreq = new int[219];
        public int majorityElement (int[] arr, int i) {
        if(i == arr.length) {
            int max = 0;
            for(int j = 0; j < numberfreq.length; j++) {
                max = Math.max(max, numberfreq[j]);
            }
            for(int j = 0; j < numberfreq.length; j++) {
                if(max == numberfreq[j] && numberfreq[j] > arr.length / 2) {
                    return (j - 109);
                }
            }
            return -110;
    }
        numberfreq[arr[i] + 109]++;
        return majorityElement(arr, i+1);
        }
    }

    public static int mooresVotingAlgorithm(int[] array) {
        int candidate = array[0];
        int votes = 0;
        for(int i = 0; i < array.length; i++) {
            if(votes == 0) {
                candidate = array[i];
            }
            if(i == candidate) {
                votes++;
            }
            else{
                votes--;
            }
        }
        return candidate;
    }

    public static int inversionCount (int [] array) {
        int inversions = 0;
        for(int i = 0; i < array.length; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i] > array[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    public static int inversionCountRecursion (int [] array, int i, int j) {
        if(i >= array.length - 1) {
            return 0;
        }
        if(j >= array.length)  {
            return inversionCountRecursion(array, i+1, i+2);
        }
        int inversions = 0;  
        if(array[i] > array[j]) {
            inversions++;
        }
        return inversions + inversionCountRecursion(array, i, j+1);
    }

    public static int tribonacci (int n) {
        if(n == 0 | n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
    }

    //main function
        public static void main ( String[] args) {
        int[] arr = {1,2,3,4,5,6,5};
        boolean[] checker = new boolean[26];
        int[] freq = new int[26];
        StringBuilder newWord = new StringBuilder("");
        //practice1("mnanngdh", 0, freq);
        //System.out.println(practice("hello", 0, freq));
        //practice3("hello", 0, freq);
        //System.out.println(practice4("listen", "silent", freq, 0));
        //practice5("word", freq, 0);
        //System.out.println(practice6("heyy", freq, 0));
        //System.out.println(practice7("helllllloool", freq, 0));
        //practice8("aaaxbbbbxcccccxddddddx", freq, 0);
        //System.out.println(practice9("bellab", freq, 0));
        //System.out.println(practice10("bellab", freq, 0));
        //practice11("zeroo", 0, "");
        //practice12(3, 0, "");
        //practice13("ABABBA", 0, "");
        //live(arr, 0, 5);
        //printNumber(589);
        //substringwithsamefrontandlast("abcab", "", 0);
        //System.out.println(conmigious("abcab", freq, 0));
        System.out.println(tower(1));
        printTower(1, "A", "B", "C");
        System.out.println(reverse("zero two", 0, ""));
        lastFirstIndex("abcbaadefb", 0, 'c');
        int[] numbers = {2, 4, 1, 3, 5};
        
        System.out.println(mooresVotingAlgorithm(numbers));
        System.out.println(inversionCount(numbers));
        System.out.println(tribonacci(7));
    }
}
