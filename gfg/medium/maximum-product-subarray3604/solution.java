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
