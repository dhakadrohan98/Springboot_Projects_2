package com.wissen;

public class DSAlgo {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        //base case
        if(n < 2) {
            return;
        }

        int i=0, j=1;

        while(i < n && j < n) {
            if(nums[i] == 0 && nums[j] != 0) {
                swap(nums, i ,j);
                i++;
                j++;
            }
            else if(nums[i] == 0 && nums[j] == 0) {
                j++;
            } else if(nums[i] != 0 && nums[j] == 0) {
                i++;
                j++;
            } else {
                i++;
                j++;
            }
        }

    }

    private void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
