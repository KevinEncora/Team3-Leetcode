class Solution {
    public int lengthOfLongestSubstring(String s) {
        int index1 = 0, index2 = 0;
        int m = s.length();
        HashMap<Character, Integer> charCount = new HashMap<>();

        if (m == 0) return 0;

        int ans = 1;

        while (index1 < m) {
            charCount.put(s.charAt(index1), charCount.getOrDefault(s.charAt(index1), 0) + 1);

            if (charCount.get(s.charAt(index1)) > 1) {
                ans = Math.max(ans, index1 - index2);
                while (charCount.get(s.charAt(index1)) > 1 && index2 < index1) {
                    charCount.put(s.charAt(index2), charCount.get(s.charAt(index2)) - 1);
                    index2++;
                }
            }

            index1++;
        }

        ans = Math.max(ans, index1 - index2);

        return ans;
    }
}
