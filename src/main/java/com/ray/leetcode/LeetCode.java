package com.ray.leetcode;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {

    public static void main(String[] args) {
        int[] nums = {2,11,7, 15};
        int target = 9;
        LeetCode leetCode = new LeetCode();
        int[] ints = leetCode.twoSum(nums, target);
        System.out.println(JSON.toJSONString(ints));

        System.out.println(leetCode.reverse1(1999999999));
        System.out.println(leetCode.reverse2(199999999));
        System.out.println(leetCode.isPalindrome(1221));

    }

    public int[] twoSum(int[] nums,int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public int reverse1(int x){
        int ans = 0;
        while (x != 0){
            if ( (ans * 10) / 10 != ans){
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public int reverse2(int x){
        long ans = 0l;
        while (x != 0){
            ans = ans * 10 + x % 10;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE){
                return 0;
            }
            x = x / 10;
        }
        return (int)ans;
    }

    public boolean isPalindrome(int x) {
        if ( x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber){
            revertedNumber = revertedNumber * 10 + x % 10;
            x = x / 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

}
