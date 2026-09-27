# Maximum Product Subarray

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array  **arr[]**  that contains positive and negative integers (may contain 0 as well). Find the maximum product that we can get in a subarray of arr[].

 **Note:**  It is guaranteed that the answer fits in a 32-bit integer.

**Examples
**

```
Input: arr[] = [-2, 6, -3, -10, 0, 2]
Output: 180
Explanation: The subarray with maximum product is [6, -3, -10] with product = 6  *(-3)*  (-10) = 180.
```

```
Input: arr[] = [-1, -3, -10, 0, 6]
Output: 30
Explanation: The subarray with maximum product is [-3, -10] with product = (-3) * (-10) = 30.
```

```
Input: arr[] = [2, 3, 4] 
Output: 24 
Explanation: For an array with all positive elements, the result is product of all elements. 
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-27T07:02:51.317Z  

```java
class Solution {
    int maxProduct(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        int maxSoFar = arr[0];
        int minSoFar = arr[0];
        int overallMax = arr[0];

        for (int i = 1; i < n; i++) {
            int currentNum = arr[i];
            int tempMax = Math.max(currentNum, Math.max(maxSoFar * currentNum, minSoFar * currentNum));
            minSoFar = Math.min(currentNum, Math.min(maxSoFar * currentNum, minSoFar * currentNum));
            maxSoFar = tempMax;
            overallMax = Math.max(overallMax, maxSoFar);
        }

        return overallMax;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-product-subarray3604/1)