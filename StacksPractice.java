import java.util.*;
public class StacksPractice {

    // public static class ListNode {
        
    //     int val;
    //     ListNode next;

    //     ListNode (int val, ListNode next) {
    //         this.val = val;
    //         this.next = next;
    //     }

    //     ListNode (int val) {
    //         this.val = val;
    //         this.next = null;
    //     }
    // }

    // static class Stack {
    //     // //using LinkedList
    //     // ListNode head = null;

    //     // //empty
    //     // public boolean isEmpty () {
    //     //     return head == null;
    //     // }

    //     // //push
    //     // public void push (int val) {
    //     //     head = new ListNode(val, head);
    //     // }

    //     // //pop
    //     // public int pop () {
    //     //     if (isEmpty()) {
    //     //         return Integer.MIN_VALUE;
    //     //     }
    //     //     int val = head.val;
    //     //     head = head.next;
    //     //     return val;
    //     // }

    //     // //peek
    //     // public int peek () {
    //     //     if (isEmpty()) {
    //     //         return Integer.MIN_VALUE;
    //     //     }
    //     //     return head.val; 
    //     // }

    //     //using Arraylist
    //     // ArrayList<Integer> list = new ArrayList<>();

    //     // //empty
    //     // public boolean isEmpty () {
    //     //     return list.isEmpty();
    //     // }

    //     // //push
    //     // public void push (int data) {
    //     //     list.add(data);
    //     // }

    //     // //pop
    //     // public int pop () {
    //     //     if (isEmpty()) {
    //     //         return Integer.MIN_VALUE;
    //     //     }
    //     //     return list.remove(list.size() - 1);
    //     // }

    //     // //peek
    //     // public int peek () {
    //     //     if (isEmpty()) {
    //     //         return Integer.MIN_VALUE;
    //     //     }
    //     //     return list.get(list.size() - 1);
    //     // }
    // }

    //intresting questions
    //to push something at the last of a stack
    public static void pushLast (Stack<Integer> s, int val) {
        if (s.isEmpty()) {
            s.push(val);
            return;
        }
        int data = s.pop();
        pushLast(s, val);
        s.push(data);
    }

    //general print
    public static void print (Stack s) {
        while (!s.isEmpty()) {
            System.out.print(s.pop() + "-> ");
        }
        System.out.println("end");
    }
    public static void print (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //reverse a string
    public static String reverseString (String word) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < word.length(); i++) {
            s.push(word.charAt(i));
        }
        StringBuilder str = new StringBuilder("");
        while (!s.isEmpty()) {
            str.append(s.pop());
        }
        return str.toString();
    }


    //reversing a stack
    public static void reverseStack (Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int data = s.pop();
        reverseStack(s);
        pushLast(s, data);
        return;
    }

    //a really basic application of stacks in questions
    //calculating stock span
    public static int[] stockSpan (int[] prices) {
        int[] span = new int[prices.length];
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);

        for (int i = 1; i < prices.length; i++) {
            while (!s.isEmpty() && prices[i] > prices[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - s.peek();
            }
            s.push(i);
        }
        return span;
    }

    //calculationg the next greatest element in an array for each element
    public static int[] nextGreatest (int[] array) {
        int[] sol = new int[array.length];
        Stack<Integer> s = new Stack<>();
        sol[sol.length-1] = -1;
        s.push(array[array.length-1]);
        for (int i = array.length - 2; i >= 0; i--) {
            while(!s.isEmpty() && array[i] >= s.peek()) {
                s.pop();
            }
            if (s.isEmpty()) {
                sol[i] = -1;
            } else {
                sol[i] = s.peek();
            }
            s.push(array[i]);
        }
        return sol;
    }
    //version 2 with indexes
    public static int[] nextGreatestIDX (int[] array) {
        int[] sol = new int[array.length];
        Stack<Integer> s = new Stack<>();
        for (int i = array.length - 1; i >= 0; i--) {
            while(!s.isEmpty() && array[i] >= array[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                sol[i] = -1;
            } else {
                sol[i] = array[s.peek()];
            }
            s.push(i);
        }
        return sol;
    }

    public static boolean duplicateBrackets (String str) {
        Stack<Character> s = new Stack<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i); 
            if (c != ')') {
                s.push(c);
            }
            else {
                if (s.isEmpty()) {
                    System.out.println("invalid");
                    return false;
                }

                if (s.peek() == '(') {
                    return true;
                }
                else {
                    while (s.peek() != '(') {
                         s.pop();
                    }
                    s.pop();
                }
            }
        }
        return false;
    }
    
    //VERSION2 
    public static boolean duplicateBrackets1 (String str) {
        Stack<Character> s = new Stack<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i); 
            if (c != ')') {
                s.push(c);
            }
            else {
                int count = 0;
                while (s.peek() != '(') {
                    count++;
                    s.pop();
                }
                if (count < 1) { //duplicate
                    return true;
                }
                s.pop();
            }
        }
        return false;
    }

    public static void main (String[] args) {
        int[] stocks = {100, 80, 60, 70, 60, 85, 100};
        int[] span = nextGreatestIDX(stocks);
        for (int i :span ) {
            System.out.print(i + " ");
        }
        System.out.println(" v");

        //HTML, css, javascript then mongo or mysql
        System.out.println("hello world");
        
    } 
}