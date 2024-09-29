package com.wissen;

public class DSAlgoProblem {
    //Q.1 maximum subarray problem
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int n = nums.length;
        if(n == 0) {
            return 1;
        }
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += nums[i];
            maxi = Math.max(maxi, sum);
            if(sum < 0) {
                sum = 0;
            }
        }
        return maxi;
    }

    //Q.2 merge two sorted arrays
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for nums1
        int j = n - 1; // Pointer for nums2
        int k = m + n - 1; // Pointer for the merged array
        if(n == 0) {
            return;
        }

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // If there are remaining elements in nums2
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
    //Q.3 Product of array except itself
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        //edge case
        if(n == 0) {
            return new int[0];
        }
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] res = new int[n];
        prefix[0] = 1;
        suffix[n - 1] = 1;
        // prefix product calculation
        for (int i = 1; i < n; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }
        // suffix product calculation
        for (int j = n - 2; j >= 0; j--) {
            suffix[j] = nums[j + 1] * suffix[j + 1];
        }

        // final product
        for (int i = 0; i < n; i++) {
            res[i] = prefix[i] * suffix[i];
        }
        return res;
    }
}
