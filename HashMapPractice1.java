import java.util.*;

public class HashMapPractice1 {

    static class MyHashMap<K, V> {

        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; //total nodes
        private int N; //buckets
        private LinkedList<Node>[] buckets;

        @SuppressWarnings("unchecked")
        public MyHashMap(){
            N = 4;
            this.buckets = new LinkedList[4]; 
            fill();
        }

        private void fill () {
            for(int i = 0; i < buckets.length; i++){
                buckets[i] = new LinkedList<>();
            }
        }

        private int bucketIdx(K key){
            int h = key.hashCode();
            int bi = Math.abs(h) % N; //modulo to keep index generated <= buckets.length
            return bi;
        }

        private int searchll(int bi, K key){
            LinkedList<Node> ll = buckets[bi];
            for(int i = 0; i < ll.size(); i++){
                if(ll.get(i).key.equals(key)){
                    return i;
                }
            }
            return -1;
        }

        private void rehash(){
            System.out.println("rehashing");
            LinkedList<Node>[] oldBucket = buckets;
            N = N*2;
            buckets = new LinkedList[N];
            fill();

            n = 0; //resetting the count

            for(int i = 0; i < oldBucket.length; i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j = 0; j < ll.size(); j++){
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        //put
        public void put(K key, V value){
            int bi = bucketIdx(key);
            int di = searchll(bi, key);

            if(di != -1) {
                Node node = buckets[bi].get(di);
                node.value = value;
            } else {
                buckets[bi].add(new Node(key, value));
                n++;
            }

            double lambda = n/N;
            if(lambda > 2.0){
                rehash();
            }
        }

        //get
        public V get(K key){
            int bi = bucketIdx(key);
            int di = searchll(bi, key);

            if(di != -1) {
                return buckets[bi].get(di).value;
            } else {
                return null;
            }
        }

        //getOrDefault
        public V getOrDefault(K key, V value){
            int bi = bucketIdx(key);
            int di = searchll(bi, key);

            if(di != -1) {
                return buckets[bi].get(di).value;
            } else {
                return value;
            }
        }
        
        //contains
        public Boolean contains(K key){
            int bi = bucketIdx(key);
            int di = searchll(bi, key);

            if(di != -1) {
                return true;
            } else {
                return false;
            }
        }

        //remove
        public V remove(K key){
            int bi = bucketIdx(key);
            int di = searchll(bi, key);

            if(di != -1) {
                V value = buckets[bi].get(di).value;
                buckets[bi].remove(di);
                n--;
                return value;
            } else {
                return null;
            }
        }

        //size
        public int size(){
            return n;
        }

        //isEmpty
        public boolean isEmpty(){
            return n==0;
        }

        //keySet
        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i = 0; i < buckets.length; i++){
                LinkedList<Node> ll = buckets[i];
                for(int j = 0; j < ll.size(); j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

    }
    public static void main(String[] args){
        MyHashMap<String, Integer> mhm = new MyHashMap<>();
        mhm.put("first", 300);
        mhm.put("second", 150);
        mhm.put("third", 50);
        mhm.put("fourth", 225);
        mhm.put("fifth", 05);

        ArrayList<String> keys = mhm.keySet();
        System.out.println(mhm.size());

        for(String key: keys) {
            System.out.println(mhm.get(key));
        }

    }
}
