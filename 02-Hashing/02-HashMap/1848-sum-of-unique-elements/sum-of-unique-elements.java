class Solution {
    public int sumOfUnique(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
         
        int sum = 0;

        for(int num : nums) {
            if (map.get(num) == 1) {
                sum = sum + num;
            } 
        }
            return sum;
    }
}