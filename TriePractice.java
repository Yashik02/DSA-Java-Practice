public class TriePractice {

    //TrieNode - Trie
    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int frequency;
        boolean isEnd;
    }

    static class Trie{
        TrieNode root = new TrieNode();

        //make sure to correctly increase the frequncy, as the trie starts from root. we increase the frequency of it's children.
        void insert(String s) {
            TrieNode curr = root;
            for(int i = 0; i< s.length(); i++) {
                int idx = s.charAt(i)-'a';  
                if(curr.children[idx] == null){
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
                curr.frequency++;
            }
            curr.isEnd = true;
        }

        boolean search(String s){
            TrieNode curr = root;
            for(int i = 0; i< s.length(); i++) {
                int idx = s.charAt(i)-'a';  
                if(curr.children[idx] == null){
                    return false;
                }
                curr = curr.children[idx];
            }
            return curr.isEnd;
        }

        boolean StartsWith(String s) {
            TrieNode curr = root;
            for(int i = 0; i< s.length(); i++) {
                int idx = s.charAt(i)-'a';  
                if(curr.children[idx] == null){
                    return false;
                }
                curr = curr.children[idx];
            }
            return true;
        }
    }

    //meathods

    //word-break using trie
    //recursive
    public static boolean wordBreak(String s, Trie trie){
        //base case
        if(s.length()==0)return true;

        //code
        for(int i = 1; i <= s.length(); i++){
            if(trie.search(s.substring(0, i)) && wordBreak(s.substring(i), trie)){
                return true;
            }
        }
        return false;
    }

    //smallest Unique prefix
    public static String smallestUniquePrefix(String s, Trie trie){
        TrieNode curr = trie.root;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int idx = s.charAt(i)-'a';
            sb.append(s.charAt(i));
            curr = curr.children[idx];
            if(curr.frequency == 1) return sb.toString(); 
        }
        return "";
    }
    //using recursion
    public static String smallestUniquePrefixRecursion(String s, TrieNode trie, int i, StringBuilder sb){
        int idx = s.charAt(i)-'a';

        //base case
        if(i == s.length()) return "";

        sb.append(s.charAt(i));
        if(trie.children[idx].frequency == 1){
            return sb.toString();
        }

        return smallestUniquePrefixRecursion(s, trie.children[idx], i+1, sb);
    }

    //total unique substrings
    //make a trie containing all suffixes of a string. then count the total nodes in that trie to calculate prefixes
    public static int uniqueSubstrings(String s){

        Trie trie = new Trie();

        for(int i = 0; i < s.length(); i++){
            trie.insert(s.substring(i,s.length()));
        }

        return countNodes(trie.root);

    }

    //count total TrieNodes
    //recursive
    public static int countNodes(TrieNode trie){
        
        //base case
        if(trie == null) return 0;

        int count = 0;
        for(int i = 0; i < 26; i++){
            if(trie.children[i] != null){
                count += countNodes(trie.children[i]);
            }
        }

        return count+1;

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words1= {"the", "there", "their", "thee", "any", "a"};
        String[] words = {"zebra", "dog", "dove", "duck"};
        for(String word : words){
            trie.insert(word);
        } 

        for(String word: words){
            System.out.println(smallestUniquePrefixRecursion(word, trie.root, 0, new StringBuilder()));
        }

        System.out.println(uniqueSubstrings("ababa"));
    }
}
