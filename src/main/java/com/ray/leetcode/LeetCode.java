package com.ray.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 简单 ——> 复杂
 * 想不出去来，就去看答案，理解答案，自己实现一遍。
 */
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
        String[] strs = {"flower","flow","flight"};

        System.out.println(leetCode.longestCommonPrefix(strs));
        String[] strs1 = {"flower","flow","","flight"};
        System.out.println(leetCode.longestCommonPrefix1(strs1));
        System.out.println(leetCode.isValid("{[}]"));

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        l1.next = l2;


        ListNode ll1 = new ListNode(2);
        ListNode ll2 = new ListNode(3);
        ll1.next = ll2;
        ListNode listNode = leetCode.mergeTwoLists(l1, ll1);
        System.out.println(JSON.toJSONString(listNode));

        int[] numsq = {1,1,2};
        leetCode.removeDuplicates(numsq);
        System.out.println(numsq);


    }

    //两数和
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


    //数反转
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

    //回转数
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


    //最长公共前缀
    public String longestCommonPrefix(String[] strs){
        if (strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length;i++){
            while (strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0,prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    //最长公共，二分查找
    public String longestCommonPrefix1(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        return longestCommonPrefix1(strs,0,strs.length - 1);
    }

    public String longestCommonPrefix1(String[] strs,int l,int r){
        if (l == r) {
            return strs[l];
        }else {
            int mid = (r + l) / 2;
            String left = longestCommonPrefix1(strs, l, mid);
            String right = longestCommonPrefix1(strs,mid + 1,r);
            return commonPrefix(left,right);
        }
    }

    public String commonPrefix(String left,String rigtht){
        int min = Math.min(left.length(),rigtht.length());
        for (int i = 0; i < min; i ++){
            if (left.charAt(i) != rigtht.charAt(i)){
                return left.substring(0,i);
            }
        }
        return left.substring(0,min);
    }

    //有效的括号,栈先进先出
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?');}};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)){
                stack.addLast(c);
            }
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

    //两个有序链表关联
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //删除数组中的重复数据,空间复杂度o(1)
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
