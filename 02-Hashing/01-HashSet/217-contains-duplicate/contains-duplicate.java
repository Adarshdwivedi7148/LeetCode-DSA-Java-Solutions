class Solution {
    public boolean containsDuplicate(int[] nums) {
        // using Hashset because of dulplicate said only

        HashSet<Integer> set = new HashSet<>();

        for(int num : nums) {
            
            if(set.contains(num)){
                return true;
            }
            set.add(num);
        }
        return false;
    }
}