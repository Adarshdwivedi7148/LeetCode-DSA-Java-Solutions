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