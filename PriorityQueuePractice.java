import java.util.*;
public class PriorityQueuePractice {

    //heap
    static class Heap{
        //notes-
        //this is a min heap
        //to make it into a max heap, simply change: add logic(1 operator) and heapify logic(2 operators).
        ArrayList<Integer> list = new ArrayList<>();

        //is empty
        public boolean isEmpty(){
            return(list.size() == 0);
        }

        //size
        public int size(){
            return list.size();
        }

        //insert function
        public void add(int data){
            list.add(data);
            //fix
            int idx = list.size()-1;
            int parent = (idx-1)/2;
            while(list.get(parent) > list.get(idx) ){
                //swap
                int temp = list.get(idx);
                list.set(idx, list.get(parent));
                list.set(parent, temp);

                //update
                idx = parent;
                parent = (idx-1)/2;
            }
        }

        //peek
        public int peek(){
            return list.get(0);
        }

        //delete
        public int remove() {
            int data = list.get(0);

            //swap first and last element
            int temp = list.get(0);
            list.set(0, list.get(list.size()-1));
            list.set(list.size()-1, temp);

            //delete last element
            list.remove(list.size()-1);

            //heapify 
            heapify(0);

            //return data if needed
            return data;
        }

        //private functions-

        //heapify
        private void heapify(int i) {
            int left = (i*2) + 1;
            int right = (i*2) + 2;
            int minIdx = i;

            if(left < list.size() && list.get(left) < list.get(minIdx)) {
                minIdx = left;
            } 

            if(right < list.size() && list.get(right) < list.get(minIdx)) {
                minIdx = right;
            } 

            if(minIdx != i) {
                //swap
                int temp = list.get(i);
                list.set(i, list.get(minIdx));
                list.set(minIdx, temp);
                
                //recurse
                heapify(minIdx);
            } else {
                return;
            }
        }
    }


    //classes

    static class Student implements Comparable<Student>{

        String name;
        int rank;

        public Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student s1) {
            return this.rank - s1.rank;
        }
    }
    
    static class Coordinate implements Comparable<Coordinate> {
        int x;
        int y;
        int distance;
        int idx;

        Coordinate(int x, int y, int distance, int idx) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.idx = idx;
        }

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coordinate c1) {
            return this.distance - c1.distance;
        }
    }

    static class Rows implements Comparable<Rows> {
        int rows;
        int soldiers;

        Rows(int rows, int soldiers) {
            this.rows = rows;
            this.soldiers = soldiers;
        }

        @Override
        public int compareTo(Rows r1) {
            if(this.soldiers == r1.soldiers) {
                return this.rows - r1.rows;
            } else {
                return r1.soldiers - this.soldiers; 
            }
        }

    }
    public static void main (String[] args) {

        Student s1 = new Student("yashik", 1089);
        Student s2 = new Student("balkirat", 6969);
        Student s3 = new Student("zerotwo", 0002);
        Student s4 = new Student("kim dokja", 4951);
        
        //priority queue
        PriorityQueue<Student> pq = new PriorityQueue<>();
         
        pq.add(s1);
        pq.add(s2);
        pq.add(s3);
        pq.add(s4);

        while(!pq.isEmpty()){
            System.out.println(pq.peek().name + ' ' + pq.peek().rank);
            pq.remove();
        }

        System.out.println();

        Heap hp = new Heap();
        hp.add(4);
        hp.add(8);
        hp.add(5);
        hp.add(1);

        while(!hp.isEmpty()) {
            System.out.println(hp.peek());;
            hp.remove();
        }

        int[] arr = {3,7,12,54,8,4,2,1,6,8,5,3};
        printArr(arr);
        heapSortHW(arr);
        printArr(arr);

        //priority queues implementation
        

        int[][] cors = {{3,3},{5,-1},{-2, 4}};

        PriorityQueue<Coordinate> cords = new PriorityQueue<>();
        for(int i = 0; i < cors.length; i++) {
            int x = cors[i][0];
            int y = cors[i][1];
            Coordinate c = new Coordinate(x, y, ((x*x) + (y*y)), i);

            cords.add(c);
        }

        for (int i = 0; i < 2; i++) {
            System.out.println("C"+cords.remove().idx);
        }

        //n ropes
        int[] ropeLengths = {3,3,5,5,7};
        System.out.println(N_Ropes(ropeLengths));

        //weakest soldiers
        int[][] troops = {
            {1,0,0,0}, 
            {1,1,1,1}, 
            {1,0,0,0}, 
            {1,0,0,0}
        };
        

        PriorityQueue<Rows> pqRows = new PriorityQueue<>();
        for(int i = 0; i < troops.length; i++) {
            int soldiers = 0;
            for(int j = 0; j < troops[i].length; j++) {
                if(troops[i][j] == 1) {
                    soldiers++;
                }
            }
            Rows r1 = new Rows(i, soldiers);
            pqRows.add(r1);
        }

        while(!pqRows.isEmpty()) {
            System.out.print("R" + pqRows.peek().rows + " --> ");
            System.out.println("No of soldiers: " + pqRows.remove().soldiers);
        }
    }

    //functions

    //print
    public static void printArr (int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //heapsort
    public static void heapSort (int[] arr) {
        int n = arr.length;
        //heapify (n/2, 0)
        for(int i = n/2-1; i >= 0; i--) {
            heapify(i, arr, n);
        }

        //swap
        for(int i = n-1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(0 , arr, i);

        }
    }

    //heapify
    //change heapify to convert it into a min heap and do sorting in descending order
    public static void heapify (int i, int[] arr, int end) {
        //swap
        int left = i*2 + 1;
        int right = i*2 + 2;
        int minIdx = i;

        if(left < end && arr[left] > arr[minIdx]){
            minIdx = left;
        }

        if(right < end && arr[right] > arr[minIdx]){
            minIdx = right;
        }

        if(minIdx != i) {
            //swap
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

            heapify(minIdx, arr, end);
        } else {
            return;
        }
    }

    //heapsort hw
    public static void heapSortHW (int[] arr) {
        int n = arr.length;
        for(int i = (n/2)-1; i >= 0; i--) {
            heapifyHW(arr, i, n);
        }

        for(int i = n-1; i>=0; i--){
            //swap
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifyHW(arr, 0, i);
        }
    }

    public static void heapifyHW(int[] arr, int i, int end){
        int minIdx = i;
        int left = i*2 + 1;
        int right = i*2 + 2;

        if(left < end && arr[left] > arr[minIdx]) {
            minIdx = left;
        }
        
        if(right < end && arr[right] > arr[minIdx]) {
            minIdx = right;
        }

        if(minIdx != i) {
            //swap
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

            heapifyHW(arr, minIdx, end);
        } else {
            return;
        }
    }

    //N - Ropes
    public static int N_Ropes(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        
        int total = 0;
        while(pq.size() > 1) {
            int first = pq.remove();
            int second = pq.remove();
            int cost = first + second;
            total += cost;
            pq.add(cost);
        }
        return total;
    }
}
