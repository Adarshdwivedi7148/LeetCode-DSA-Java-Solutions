import java.util.*;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
       
       // using hashmap 
        HashMap<Integer,Integer> set = new HashMap<>();
        
        int i = 0;
        for(int num : nums) {
           
           if(set.containsKey(num)) {
              
              if(i - set.get(num) <= k) {
                return true;
              }
           }
            set.put(num, i);
            i++;
        }
        return false;
    }
}

/* import java.util.*;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {

                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }

            map.put(nums[i], i);
        }

        return false;
    }
}*/