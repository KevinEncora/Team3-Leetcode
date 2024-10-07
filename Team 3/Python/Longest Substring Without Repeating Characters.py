class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        index1 = 0
        index2 = 0
        m = len(s)
        char_count = {}

        if m == 0:
            return 0

        ans = 1

        while index1 < m:
            char_count[s[index1]] = char_count.get(s[index1], 0) + 1

            if char_count[s[index1]] > 1:
                ans = max(ans, index1 - index2)
                while char_count[s[index1]] > 1 and index2 < index1:
                    char_count[s[index2]] -= 1
                    index2 += 1

            index1 += 1

        ans = max(ans, index1 - index2)

        return ans
