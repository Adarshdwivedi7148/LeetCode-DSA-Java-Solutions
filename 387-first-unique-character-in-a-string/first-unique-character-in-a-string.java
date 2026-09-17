import java.util.*;

class Solution {
    public int firstUniqChar(String s) {

        HashMap<Character,Integer> map = new HashMap<>();      // map is variable name
        
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < s.length(); i++) {         // another way to write ->    for (char ch : s.toCharArray()) {}
            char ch = s.charAt(i);

            if(map.get(ch) == 1) {
                return i;
            }
        }
        return -1;
    }
}