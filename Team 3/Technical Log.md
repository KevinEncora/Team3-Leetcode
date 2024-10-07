# Technical Log 

## Problem : Median of Two Sorted Arrays

### Overview
This problem asks for an efficient solution to find the median of two sorted arrays. The requirement is to solve it in O(log(m+n)) time complexity, as opposed to simply merging and sorting the arrays, which would take linear time.

### Context:
Given two sorted arrays nums1 and nums2, the goal is to return the median of the two arrays combined. The median is defined as the middle element of the sorted array if the length is odd, or the average of the two middle elements if the length is even. The challenge is to find the median without merging the arrays, as merging would not meet the O(log(m+n)) time complexity requirement.

### Solution:
1. we ensure that nums1 is the smaller of the two arrays. This allows us to perform binary search on the smaller array, reducing the search space logarithmically.
2. The key idea is to partition the two arrays into two halves such that the left half contains the smaller elements and the right half contains the larger ones. We perform binary search on nums1 to find the correct partition point.
3. For each midpoint in nums1, we compute the corresponding partition point in nums2.
4. We then compare the largest element on the left side of the partition with the smallest element on the right side. If the partition is correct, we compute the median:

5. If the total number of elements is odd, the median is the smallest element on the right side.
6. If the total number of elements is even, the median is the average of the largest element on the left and the smallest element on the right.
7. This approach ensures that the median is found in O(log(m+n)) time by focusing on binary search rather than merging and sorting.

### Alternative Solutions:

-A simpler solution would be to merge the two arrays and compute the median directly. However, this would take O(m+n) time, which does not satisfy the problem's constraints.
-Another alternative could involve simulating the merging of two arrays using two pointers, but this still results in linear time complexity and does not use the more efficient binary search technique.

## Problem : LRUCache

### Overview 

Design a data structure that follows the constrains of a Least Recent Used (LRU) cache.

Where we have to implement different functions like :

- Initialize the LRU cache 
- Retrieve the value given a key if this exist
- Update the value of a key and if its the case insert it into the data structure
- Delete the least recent one if the we try to insert a value in which exceed the capacity of the data structure

### Context

What is an LRU cache? Is a data structure that stores a limited number of items. When the cache reaches its capacity, it evicts the least recently used item before adding a new one. 

The LRU cache should support two main operations:

- Get: Retrieve the value associated with a key if it exists in the cache. If the key is not found, return -1.
- Put: Add a new key-value pair to the cache. If the key already exists, update its value and move it to the front of the cache. If adding the new key exceeds the cache's capacity, the least recently used item should be removed.


**Example 1**

LRUCache cache = new LRUCache(2); // capacity is 2
cache.put(1, 1); // cache is {1=1}
cache.put(2, 2); // cache is {1=1, 2=2}
cache.get(1);    // returns 1 and moves key 1 to the front
cache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
cache.get(2);    // returns -1 (not found)
cache.put(4, 4); // evicts key 1, cache is {3=3, 4=4}
cache.get(1);    // returns -1 (not found)
cache.get(3);    // returns 3
cache.get(4);    // returns 4


### Solution 

The implementation uses a dictionary to maintain the order of the keys based on their usage and a linked list to keep track of the most used and least used one.
We use a dictionary  for efficient retrieval, updates.


1. Initialization:
We create and sotre the key-value pairs to maintain the order of kets based on the usage of it.
And we set the maximum capacity for the cache to delete the items when we pass the capacity.

2. Get Method:

We check wheter the key exist in the dictionary. 
If it does, we update the value, i.e, we move the key to the end, saying that it has been used recently and we return its value.
If it doesn't we return the default value, i.e., -1

3. Put Method:

If the key is already in the map, we update the value and move it to the end to say that is has been recently used.
In the case where it's new and we have reached it's capacity, we remove the least recently used item. And we add the newest value which is going to be our most used one.

### Alternative Solution

For this problem I wasn't hable to found an alternative solution, but i belive that using a deque and a dictionary to keep track of the address of the elements of the deque.


## Problem : Longest Substring Without Repeating Characters

### Overview
Given a string s, we need to find the lenght of the longest substring in a given string that does not contain any repeating characters. 

### Context

What is a substring? A substring is a contiguous sequence (we can't skip any character) of characters within a string. 
So, we need to identify the longest contiguous substring that meets the following conditions:

- All characters within the substring must be distinct.
- The characters must maintain their original order in the string.

**Example 1**

s = "abcabcbb"

Substring withput repeting characters :
- "abc"
- "bca"
- "cab"
- "bc"
- "b"
- "a"
- "c"

The longest substring without repeating characters is "abc" or "bca" or "cab", with the length of the three are the same, so the answer is 3.

**Example 2**

s = "bbbbb"

Substring withput repeting characters :
- "b"

In this case, the longest substring without repeating characters is any of the "b" characters, so the answer is 1.

### Solution

By reviewing the problem statement, the first solution that came to mind was using a brute-force approach, where I would search for and store all substrings without repeating 
characters. However, I first needed to check the constraints to see if this possible solution was even valid. The length of the string can reach 
$5×10^4$, so a brute-force solution would not be feasible. My next idea was to use a sliding window, where I could apply binary search to determine the size of the window. 
However, this approach would involve many extra steps. Instead, the concept of a sliding window led me to consider the two-pointers technique, which would be something like :


1. Have a pointer that will continue advancing in the string 
2. Having a frequency count of the characters I have found
3. Check wheter this caracter is already in the count or not
4. In the case that I have seen it, Know what was is the distance of the two pointers, with this giving me the length and keep the biggest one
5. Move the second pointer that is in the beggining or the last position, whatever is the case
6. Cotinue moving forward the second pointer until the counter of the character repeated is only one
7. Do this until we reach the end of the string
8. Return the length

### Alternative Solution

An alternative solution to this is using a sliding window, where I can use binary search to determine the size of the window. This approach offers a simpler method for checking whether that length of the substring can be satisfied.

## Problem : Regular Expression Matching 

### Overview:
This problem focuses on implementing a pattern matching function that can handle two special characters: . which matches any single character, and * which represents zero or more of the preceding character. Our solution aims to simulate the behavior of these characters and efficiently determine if a string matches a given pattern.

### Context:
We want to clarify our interpretation of the problem: the characters * and . are independent. Specifically, * can appear after any character and represents zero or more occurrences of the preceding character, but that character is independent of the next . For example, the pattern a represents an "a" followed by zero or more "a"s, but it cannot represent an empty string alone. The main challenge is determining when * represents a character or none, and efficiently handling all possible combinations.

### Solution:

The first validation checks whether the pattern p starts with a *. If it does, the function immediately returns False, as the pattern cannot begin with this symbol.

To handle consecutive * characters, we reduce multiple consecutive * into a single one. This simplifies the comparison process and avoids redundant pattern evaluations.

Our approach involves iterating through both s and p:

When encountering a . in p, it matches any character in s.
When encountering a *, we explore two paths: one where * represents no character (empty), and another where * matches one or more of the preceding character. This allows us to explore both possibilities simultaneously.
We use auxiliary lists (aux1 and aux2) to track partial match states. These lists help manage different paths of matching when * is encountered.

After processing both s and p, the function checks if any valid match exists in aux1. If so, it returns True; otherwise, it returns False.

### Alternative Solutions:

A brute-force approach could involve generating all possible combinations of patterns with * treated as zero or more characters and comparing each to s. However, this approach would be highly inefficient due to the exponential growth in possible combinations.
Another alternative would involve using dynamic programming. This approach would involve building a 2D table to store match states for each index of s and p. While this would be more efficient, it would also be more complex to implement.
