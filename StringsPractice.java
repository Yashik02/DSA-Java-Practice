import java.util.*;

public class StringsPractice {

    //palindrome checker for strings
    public static boolean isPalindrome(String word) {
        for(int i = 0; i < word.length() / 2; i++) {
            if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    //shortest path
    public static float shortestDistance (String directions) {
        int length = directions.length();
        int x = 0;
        int y = 0;
        char character;
        for(int i = 0; i < length; i++) {
            character = directions.charAt(i);
            switch (character) {
                case 'N': 
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'W':
                    x--;
                    break;
                case 'E':
                    x++;
                    break;
                default:
            }
        }
        float shortestDistance = (float)Math.sqrt((x*x) + (y*y));
        return shortestDistance;
    }

    //upper case
    public static StringBuilder upperCase (StringBuilder line) {
        Character.toUpperCase(line.charAt(0));
        for( int i = 1; i < line.length();i++) {
            if(line.charAt(i) == ' ') {
                Character.toUpperCase(line.charAt(i + 1));
            }
        }
        return line;
    }

    public static String toUpperCase (String str) {
        StringBuilder sb = new StringBuilder ("");
        //do it everytime for the first charatcter
        sb.append(Character.toUpperCase(str.charAt(0)));

        for(int i = 1 ; i < str.length(); i++) {
            char letter;
            if(str.charAt(i - 1) == ' ') {
                letter = Character.toUpperCase(str.charAt(i));
            }
            else {
                letter = str.charAt(i);
            }
            sb.append(letter);
        }
        return sb.toString();
    }

    //compression
    //Time complexity : O(n) 
    public static String compression (String str) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            int count = 1;
            while(i < str.length() - 1 && currentChar == str.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(currentChar);
            if(count > 1 ){
                sb.append(count);
            }
        }
        return sb.toString();
    }

    //decompression later

    //homework
    
    //question 1 vowels : 
    public static int vowelsCounter (String str) {
        int count = 0;
        for(int loop = 0; loop < str.length(); loop++) {
            if(str.charAt(loop) == 'a' || str.charAt(loop) == 'e' ||str.charAt(loop) == 'i' || str.charAt(loop) == 'o' || str.charAt(loop) == 'u') {
                count++;
            }
        }
        return count;
    }

    //false true
    //apnacoage

    //ANAGRAM

    public static boolean isAnagram(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }
        int[] freq = new int[26];

        for(int i = 0 ; i < str1.length(); i++ ) {
            freq[str1.charAt(i) - 97]++;
            freq[str2.charAt(i) - 97]--;
        }

        for( int i : freq) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = "helloworld";
        String word2 = "worldhello";
        System.out.println(isAnagram(word1,word2));
    }
}
