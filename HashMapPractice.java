import java.util.*;

public class HashMapPractice {

    //functions
    public static HashSet<Integer> union (int[] arr1, int[] arr2){
        HashSet<Integer> union = new HashSet<>();
        for(int i: arr1) {
            union.add(i);
        }
        for(int i: arr2) {
            union.add(i);
        }
        return union;
    }

    public static HashSet<Integer> intersection (int[] arr1, int[] arr2){
        HashSet<Integer> temp = new HashSet<>();
        HashSet<Integer> intersection = new HashSet<>();
        for(int i: arr1) {
            temp.add(i);
        }
        for(int i: arr2) {
            if(temp.contains(i)){
                intersection.add(i);
            } 
        }
        return intersection;
    }


    public static void main (String[] args) {

        int[]  arr1 = {7, 3, 9};
        int[]  arr2 = {6, 3, 9, 2, 9, 4};

        System.out.println(union(arr1, arr2));
        System.out.println(intersection(arr1, arr2));



        //hashing~
        //topics - 
        // HashMap, LinkedHashMap, TreeMap
        // HashSet, LinkedHashSet, TreeSet

        HashMap<String, Integer> hm = new HashMap<>();

        //put function
        hm.put("h1", 1);
        hm.put("h2", 2);
        hm.put("h3", 3);
        hm.put("h4", 4);
        hm.put("h5", 5);
        hm.put("h6", 6);

        System.out.println(hm);
        //get function
        System.out.println(hm.get("h1"));
        System.out.println(hm.get("h2"));
        
        //get Or Default
        System.out.println(hm.getOrDefault("h6", -1));
        System.out.println(hm.getOrDefault("h7", -1));

        //contains true/false
        System.out.println(hm.containsKey("h1"));
        System.out.println(hm.containsKey("h7"));

        //removes and returns the value
        System.out.println(hm.remove("h6"));
         
        //size
        System.out.println(hm.size());

        //isEmpty
        //hm.clear();
        System.out.println(hm.isEmpty());

        //Iterate
        Set<String> keys = hm.keySet();
        System.out.println(keys);

        for(String key : keys){
            System.out.println(key + "->" + hm.get(key));
        }

        //entrySet() give both
        for(Map.Entry<String, Integer> entry : hm.entrySet()){
            String studentName = entry.getKey();
            Integer grade = entry.getValue();
            System.out.println(studentName + ": " + grade);
        }

        //Linked Hashmap - items are ordered in the sequence that they are entered.
        //this is possible with the help of a Doubly linked list
        //Performance: LHM < HM
        LinkedHashMap<String, Integer> hm1 = new LinkedHashMap<>();
        

        //treemap - it is basically a self-balancing binary search tree. made using red-black trees.
        TreeMap<Character, Integer> tm = new TreeMap<>();

        //hashset - a collection of unique elements, implimented via hashmap with dummy values.
        //it is unordered 
        //NULL values are allowed
        HashSet<String> cities = new HashSet<>();
        //iterators are used to traverse over sets.
        //it points to a null/garbage value before the first set element.
        //if iterator.next exists, we print or use that element
        cities.add("mumbai");
        cities.add("ahemdabad");
        cities.add("patiala");
        cities.add("uttrakhand");
        cities.add("assam");
        
        Iterator<String> it = cities.iterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
            //automatically updates +1
        }

        System.out.println();

        for(String s: cities){
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println(cities);

        //linkedHashSet is a data structure like linkedHashMap. 
        //NULL valuse is allowed
        //also implimented with doubly linked list
        //it preserves the order of it's entries
        //Performance: LHS < HS
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.add("mumbai");
        lhs.add("ahemdabad");
        lhs.add("patiala");
        lhs.add("uttrakhand");
        lhs.add("assam");
        
        System.out.println(lhs);

        //treeSet similar to treeMap but unique values only
        //NULL valuse are NOT❌ allowed. because we need real values to compare and balance a BST
        TreeSet<String> ths = new TreeSet<>();
        ths.add("mumbai");
        ths.add("ahemdabad");
        ths.add("patiala");
        ths.add("uttrakhand");
        ths.add("assam");

        System.out.println(ths);
    }
}
