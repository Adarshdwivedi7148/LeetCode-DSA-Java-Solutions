import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : strs) {
            char[] a = s.toCharArray();
            Arrays.sort(a);

            String key = new String(a);

            if(map.containsKey(key) == false) {
                map.put(key, new ArrayList<>());
            }
             map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}