
import java.util.*;

public class ArrayListPractice {

    //swap fuction
    public static void swap ( ArrayList<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    //XOR swap
    public static void swapXOR(ArrayList<Integer> list, int index1, int index2) {
        list.set(index1, list.get(index1) ^ list.get(index2));
        list.set(index2, list.get(index1) ^ list.get(index2));
        list.set(index1, list.get(index1) ^ list.get(index2));
    }

    //two sum
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int sum = nums[left] + nums [right];
            if(sum == target) {
                int[] ans = {left, right};
                return ans;
            }
            if(sum < target) {
                left++;
            }
            if(sum > target) {
                right--;
            }
        }
        return null;
    }

    ///two sum rotated array
    public static int[] twoSumRotated (int[] nums, int target) {
        int n = nums.length; 
        int pivot = -1;
        for(int i = 0; i < n-1; i++) {
            if(nums[i] > nums[i+1]) {
                pivot = i;
                break;
            }
        }
        int lp = pivot+1;
        int rp = (pivot + n) % n;

        while(lp != rp) {
            int sum = nums[lp] + nums[rp];
            if(sum == target) {
                int[] ans = {lp, rp};
                return ans;
            }
            if(sum < target) {
                lp = (lp + 1) % n;
            }
            if(sum > target) {
                rp = (rp + n - 1) % n;
            }
        }
        return null;
    }

    //water bucket container
    public static int largestWaterContainer (int[] walls) {
        int n = walls.length;
        int left = 0, right = n-1;
        int maxArea = Integer.MIN_VALUE;
        int currArea;
        while(left < right) {
            if(walls[left] < walls[right]) {
                currArea = (right - left) * walls[left];
                left++;
            }
            else {
                currArea = (right - left) * walls[right];
                right--;
            }
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    //monotonic checker
    public static boolean isMonotonic1(ArrayList<Integer> list) {
        int n = list.size();
        // for ascending order or ArrayList with same elements
        if(list.get(0) <= list.get(n-1)) {
            for(int i = 0; i < n-1 ; i++) {
                if(list.get(i) > list.get(i+1)) {
                    return false;
                }
            }
        }
        //for decending order ArrayList
        else{
            for(int i = 0; i < n-1 ; i++) {
                if(list.get(i) < list.get(i+1)) {
                    return false;
                }
            }
        }
        return true;
    }

    //monotonic cheker optimized
    public static boolean isMonotonic2 (ArrayList<Integer> list) {
        boolean isDec = false, isInc = false;
        for(int i = 0; i < list.size() - 1; i++) {
            if(list.get(i) < list.get(i+1)) {
                isInc = true;
            }
            if(list.get(i) > list.get(i+1)) {
                isDec = true;
            }
        }
        return !(isInc && isDec);
    }
    //lonely number 
    //WRONG!!! deleting pairs is risky and odd no of pairs can be left behind!
    public static ArrayList<Integer> lonelyNumWrong(ArrayList<Integer> list) {
        for(int i = 0; i < list.size()-1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                if(list.get(i) + 1 == list.get(j) || list.get(i) - 1 == list.get(j)) {
                    list.remove(i);
                    list.remove(j);
                    i--; // update i's value to restart
                }
            }
        }
        return list;
    }

    //lonely numbers
    public static ArrayList<Integer> lonelyNumbers (ArrayList<Integer> nums) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i : nums) {
            if(!(nums.contains(i + 1) || nums.contains(i - 1))) {
                result.add(i);
            }
        }
        return result;
    } 

    

    //DOING EVERYTHING AGAIN CLEARLY!!!
    //assingment questions
    public static boolean ismonotonic (ArrayList<Integer> list) {
        boolean isAscending = true;
        boolean isDecending = true;
        for(int i = 0; i < list.size()-1; i++) {
            if(list.get(i) > list.get(i+1)) {
                isAscending = false;
            }
            if(list.get(i) < list.get(i+1)){
                isDecending = false;
            }
        }
        return isAscending || isDecending;
    }

    public static ArrayList<Integer> lonely (ArrayList<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        ArrayList<Integer> elements = new ArrayList<>();
        for(int i = 1; i < n-1; i++) {
            if(nums.get(i - 1) + 1 < nums.get(i) && nums.get(i) + 1 < nums.get(i+1)) {
                elements.add(nums.get(i));
            }
        }
        //edge cases
        if(n == 1 || nums.get(0) + 1 < nums.get(1)) {
            elements.add(nums.get(0));
        }
        if(n > 1 && nums.get(n-2) + 1 < nums.get(n-1)) {
            elements.add(nums.get(n-1));
        }
        return elements;
    }

    //mist freq following key
    public static int maxKeyFollow (ArrayList<Integer> list, int key) {
        //freq array
        int[] freq = new int[1000]; // the value range is 1 to 1000 inclusive
        int n = list.size();
        for(int i = 0; i < n-1; i++) {
            if(list.get(i) == key) {
                freq[list.get(i+1)-1]++;
            }
        }
        int maxFreq = 0;
        int maxElement = -1;
        for(int i = 0; i < freq.length; i++) {
            if(freq[i] > maxFreq) {
                maxFreq = i;
                maxElement = i+1;
            }
        }
        return maxElement;
    }

    //beautiful array
    //permutations, and no arithematic series
    // 0 - 1000
    
    

    public static void main (String[] args) {

        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(2);
        testList.add(2);
        testList.add(2);
        testList.add(3);
        System.out.println(testList);

        System.out.println(maxKeyFollow(testList, 2));

        //notes

        //java collection framework
        //we need to use classes to stored data type in array lis
        ArrayList<Integer> list = new ArrayList<>();

        //1. time complexity : O(1)
        list.add(0);
        list.add(1); 
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // special add function 
        list.add(6, 9); // O(n)

        //2. get operation - O(1)
        int element = list.get(2);
        

        //3. delete - O(n)
        list.remove(0);
        
        //4. set element at index - O(n)
        list.set(5, 6);
        
        //5. contains element O(n)
        list.contains(15); // returns true or false

        //6. size function
        list.size(); // it is a meathod

            //used for loop iterations:
                // for(int i = 0; i < list.size(); i++) {
                //     System.out.println(list.get(i));
                // }
            //reverse print
                // for(int i = list.size() - 1; i >= 0; i--) {
                //     System.out.print(list.get(i) + " ");
                // }
            //get max
                // int max = Integer.MIN_VALUE;
                // for(int i : list) {
                //     max = Math.max(max, i);
                // }


        //7. in-built sorting:
            //ascending order
                Collections.sort(list);
            //decending order
                Collections.sort(list, Collections.reverseOrder());
                //function logic
                //Collections.reverseOrder() : comparators, are functions in java that define logic
                //also applicable on other classes or objects 
                //(for ex normally 9 > 7, but with comparators we can change that base logic and maybe even make them 9 < 7 or 9 = 7)
        
        //8. Multi - dimentional ArrayList
            ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            ArrayList<Integer> list3 = new ArrayList<>();
            mainList.add(list1);
            mainList.add(list2);
            mainList.add(list3);
            //creating multiple ArrayLists inside main list
                // for(int i = 0; i < mainList.size(); i++) {
                //     ArrayList<Integer> currList = mainList.get(i);
                //     for(int j = 1; j <= 5; j++) {
                //         currList.add((i+1)*j);
                //     }
                //     System.out.println();
                // }
            //printing multi dimentional lists
                // System.out.println(mainList);
                // for(int i = 0; i < mainList.size(); i++) {
                //     ArrayList<Integer> currList = mainList.get(i);
                //     for(int j = 0; j < currList.size(); j++) {
                //         System.out.print(currList.get(j) + " ");
                //     }
                //     System.out.println();
                // }
    }

    
}