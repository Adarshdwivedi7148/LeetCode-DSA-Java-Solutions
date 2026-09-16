import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map1 = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();

        for(int i=0; i<s.length(); i++) {
          
          char sChar = s.charAt(i);
          char tChar = t.charAt(i);

          if(map1.containsKey(sChar) && map1.get(sChar) != tChar) {
            return false;
          }

          if(map2.containsKey(tChar) && map2.get(tChar) != sChar) {
            return false;
          }

          map1.put(sChar,tChar);
          map2.put(tChar,sChar);  
        }
       return true;
    }
}