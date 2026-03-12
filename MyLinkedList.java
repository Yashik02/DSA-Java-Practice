public class MyLinkedList {

    //a LinkedList object class
    public static class Node {
        int data;
        Node next; //class calling itself creating a link

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //mentioning or assinging a head node that will be static for  all the other nodes of that object
    public static Node head;
    public static Node tail;
    public static int size; //WARNING: only applicable when there's a single linked list

    //making non-static funtions that can be called for each object(node)

    //add a node at the start of a Linked List
    //Time complexity = O(n)
    public void addFirst (int data) {
        //step-1 = create a new Node
        Node newNode = new Node(data);
        size++;

        //for empty Linked List
        //if no Node value is assigned to head
        if(head == null) {
            head = tail = newNode;
            return;
        }
        //step-2 = assinginwwg head to it's 'next' index
        newNode.next = head;
        //step-3 = making newNode the head of linked list
        head = newNode;
    }

    public void addLast (int data) {
        //step-1 = create a new node
        Node newNode = new Node(data);
        size++;
        //in case of null array list
        //by convention if head is null we take it as a null linked list. can also be done by checking if tail is null
        if(head == null) {
            head = tail = newNode;
            return;
        }
        //step-2 = making tail point towards new node
        tail.next = newNode;
        //step-3 = making newNode the tail
        tail = newNode;
    }
    
    //Time complexily O(n)
    public void print ()  {
        if(head == null) {
            System.out.println("Linked List is empty");
            return;
        }
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    //add in the middle
    public void add (int index, int data) {
        if(index == 0) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;

        while(i < index) {
            temp = head.next;
            i++;
        }

        newNode.next = temp.next ;
        temp.next = newNode; //THINK MARK THINK!!!
    }

    //removing elements
    public int removeFirst () {
        if(size == 0) {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }
        else if(size == 1) {
            int val = head.data;
            head = tail = null;
            size=0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    //removing elements
    public int removeLast() {
        if(size == 0) {
            System.out.println("Linked List is already empty");
            return Integer.MIN_VALUE;
        }
        else if(size == 1) {
            int val = head.data;
            head = tail = null;
            size=0;
            return val;
        }
        // prev : i = size-
        Node prev = head;
        for(int i = 0; i < size-2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    //liner search
    public int itrSearch (int key) {
        Node temp = head;
        int i = 0;

        while(temp != null) {
            if(temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }

        return-1;
    }

    //recursion search
    public int itrSearch (int key, int Idx, Node temp) {
        if(temp == null) {
            return -1;
        }
        if(temp.data == key) {
            return Idx;
        }
        return itrSearch(key, Idx+1, temp.next);
    }

    //linked list revese recursion backtracking
    public void rotateWrapper () {
        rotate(head, head.next);
    }
    public void rotate (Node pretemp,Node temp) {
        if(temp == null) {
            head.next = null; //edge case while reversing
            tail = head;
            head = pretemp;
            return;
        }
        rotate(temp, temp.next);
        //backtracking reversing
        temp.next = pretemp;
    }

    //linked list reverse
    public void revers() {
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    //wrong! attempt, i think i should not use recursion on topics like these just to be cool
    public int removeNth (int n, Node temp) {
        if(temp.next == null) {
            return 0;
        }
        removeNth(n, temp.next);
        //backtracking
        if(n <= 0) {
            int val = temp.next.data;
            temp.next = temp.next.next;
            return val;
        }
        n--;
        return 0;
    }

    //remove nth
    public void removeNTH (int n) {
        if(head == null) {
            return;
        }
        Node temp = head;
        int sz = 0;
        while(temp != null) {
            temp = temp.next;
            sz++;
        }

        if(sz == n) {
            head = head.next;
            size--;
            return;
        }
        
        Node prev = head;
        for(int i = 1; i < sz-n; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;

        if(n == 1) {
            tail = prev;
        }

        size--;//update for the whole class
    }

    public boolean isPalindromePractice() {
        int sz = 0;
        Node test = head;
        while(test != null) {
            test=test.next;
            sz++;
        }
        for(int i = 0; i < sz/2; i++) {
            //front value loop
            Node temp1 = head;
            for(int j = 0; j < i; j++) {
                temp1 = temp1.next;
            }
            //back value loop
            Node temp2 = head;
            for(int j = 0; j < sz-i-1; j++) {
                temp2 = temp2.next;
            }
            if(temp1.data != temp2.data) {
                return false;
            }
        }
        return true;
    }

    public Node findMid (Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //is palindromw optimozed with O(n) complexity
    public boolean isPalindrome()  {
        if(head == null || head.next == null) {
            return true;
        }

        Node mid = findMid(head);

        //reverse second half
        Node prev = null;
        Node curr = mid;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node fHalf = head;
        Node sHalf = prev;
        //check
        boolean isPalindrome = true;
        while(sHalf != null) {
            if(fHalf.data != sHalf.data) {
                isPalindrome = false;
                break;
            }
            fHalf = fHalf.next;
            sHalf = sHalf.next;
        }
        
        //restore the flipped list
        curr = prev;
        prev = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return isPalindrome; 
    }

    public static boolean isCycle (Node head) {
        if(head == null || head.next == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void removeCycle(Node head) {
        if(head == null || head.next == null) {
            return;
        }
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                break;
            }
        }
        if(fast == null || fast.next == null) {
            return;
        }
        slow = head;
        if(slow == fast) {
            while(fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null;
            return;
        }
        Node prev = null;
        while(slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next; 
        }
        prev.next = null;
    }

    public Node findMid1 (Node head) {
        Node slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public Node mergeSortRecursive (Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node firstH = head;
        Node mid = findMid1(firstH);
        Node secondH = mid.next;
        mid.next = null;

        firstH = mergeSortRecursive(firstH);
        secondH = mergeSortRecursive(secondH);
        merge(firstH, secondH);
        return head;
    
    }
    public Node merge (Node head1, Node head2) {
        Node temp = new Node(Integer.MIN_VALUE);
        Node temphead = temp;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return temphead.next;
    }

    //zig zag code
    public Node zigZag (Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        //find middle
        Node mid = findMid1(head);
        Node left = head;
        Node right = mid.next;
        mid.next = null;

        //reverse the second array
        Node prev = null;
        Node curr = right;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //prev is head of secont lisd
        right = prev;
        //merging
        while(right != null) {
            Node nextl = left.next;
            Node nextr = right.next;
            left.next = right;
            if (nextl == null) {
                break;
            } 
            right.next = nextl;

            //update
            left = nextl;
            right = nextr;
        }
        return head;
    }
    public int size (Node head) { 
        if(head == null) {
            return 0;
        }
        Node curr = head;
        int size = 1;
        while(curr == null) {
            size++;
            curr = curr.next;
        }
        return size;
    }
    public Node deleteMN (Node head, int m, int n) {
        if(n == 0) {
            return head;
        }
        Node temp = head;

        for(int i = 1; i < m; i++) {
                temp = temp.next;
        }
        Node temp1 = temp;
        for (int i = 0; i <= n; i++) {
            temp1 = temp1.next;
        }
        temp.next = temp1;
        return head;
    }

    //Main class
    public static void main (String[] args) {
        MyLinkedList ll = new MyLinkedList();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.addLast(6);
        ll.addLast(7);
        ll.addLast(8);
        ll.print();
        ll.deleteMN(head, 0, 2);
        ll.print();
    }   

}