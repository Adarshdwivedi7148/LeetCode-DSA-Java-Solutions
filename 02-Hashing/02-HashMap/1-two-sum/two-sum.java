class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> set = new HashMap<>();

        for(int i=0; i<nums.length; i++) {
            int need = target - nums[i];

            if(set.containsKey(need)) {
                return new int[] {set.get(need),i};
            }

            set.put(nums[i],i);
        }
        return new int[]{};
    }
}