class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map = new HashMap<>();
        
        // Magazine ke characters ka count
        for(char ch : magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // RansomNote ke characters check karo
        for(char ch : ransomNote.toCharArray()) {
            if( map.containsKey(ch) == false || map.get(ch) == 0) {
                return false;
            }
            map.put(ch, map.get(ch) - 1 );
        }
        return true;
    }
}