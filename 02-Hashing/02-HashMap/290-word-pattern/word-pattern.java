import java.util.*;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if(pattern.length() != words.length) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> use = new HashMap<>();

        for(int i=0; i<pattern.length(); i++) {
            
            char ch = pattern.charAt(i);
            String word = words[i];

            if(map.containsKey(ch) && map.get(ch).equals(word) == false) {
               return false;
            }

            if(use.containsKey(word) && use.get(word) != ch) {
               return false;
            }

            map.put(ch,word);
            use.put(word,ch);
        }
        return true;
    }
}