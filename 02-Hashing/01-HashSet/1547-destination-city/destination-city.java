import java.util.*;

class Solution {
    public String destCity(List<List<String>> paths) {
        
        HashSet<String> set = new HashSet<>();

         // Source cities ko Set mein store karo
         for(List<String> path : paths) {
            set.add(path.get(0));
         }

         for(List<String> path : paths) {
            String city = path.get(1);

            if(set.contains(city) == false) {
                return city;
            }
        }
        return "";              
    }
}