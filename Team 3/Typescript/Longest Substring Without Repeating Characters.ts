class Solution {
    lengthOfLongestSubstring(s: string): number {
        let index1 = 0, index2 = 0;
        const m = s.length;
        const charCount: Map<string, number> = new Map();

        if (m === 0) return 0;

        let ans = 1;

        while (index1 < m) {
            charCount.set(s[index1], (charCount.get(s[index1]) || 0) + 1);

            if (charCount.get(s[index1])! > 1) {
                ans = Math.max(ans, index1 - index2);
                while (charCount.get(s[index1])! > 1 && index2 < index1) {
                    charCount.set(s[index2], (charCount.get(s[index2])! - 1));
                    index2++;
                }
            }

            index1++;
        }

        ans = Math.max(ans, index1 - index2);

        return ans;
    }
}
