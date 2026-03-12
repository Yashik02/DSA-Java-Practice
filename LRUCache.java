import java.util.*;

class LRUCache { 

    //a node class for doubly linked list
    class Node {
        Node next;
        Node prev;
        int key;
        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    HashMap<Integer, Node> hm = new HashMap<>();
    int count = 0;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) {
            return -1;
        } else {
            Node n = hm.remove(key);
            delete(n);
            add(n);
            return n.val;
        }
    }

    public void add(Node n) {
        n.next = head.next;
        n.next.prev = n;
        n.prev = head;
        head.next = n;
    }

    public void delete(Node n) {
        n.next.prev = n.prev;
        n.prev.next = n.next;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)) {
            Node oldNode = hm.remove(key);
            delete(oldNode);

            Node n = new Node(key, value);
            hm.put(key, n);
            add(n);
            
        } else {
            if(count == capacity) {
                delete(tail.prev);
                count -= 1;
            }
            Node n = new Node(key, value);
            hm.put(key, n);
            add(n);

            count += 1;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */