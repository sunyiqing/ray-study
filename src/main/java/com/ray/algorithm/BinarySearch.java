package com.ray.algorithm;

/**
 * 二分查找
 *
又称为 折半查找，二分查找，适合对已经排序好的数据集合进行查找，时间复杂度O（log2n）,效率高。
 假设有一升序的数据集合，先找出升序集合中最中间的元素，将数据集合划分为两个子集，将最中间的元素和关键字key进行比较，
 如果等于key则返回，如果大于关键字key，则在前一个数据集合中查找，否则在后一个子集中查找，直到找到为止，如果没找到则返回-1；

 1.首先确定整个查找区间的中间位置mid=(low+high)/2;
 2.用待查关键字值与中间位置关键字值进行比较；
    若相等，则查找成功；
    若大于，则在后半个区域中继续进行折半查找。
    若小于，则在前半个区域中继续进行折半查找。
    查找成功，返回关键字所在数组下标，没找到返回-1；

 * Created by yiqing on 2018/6/9.
 */
public class BinarySearch {


    public static void main(String[] args) {
        int[] a={11,22,24,234,250,280};
        System.out.println(binarySearch(a,11,0,a.length-1));
    }
    private static int binarySearch(int[] datas,int target,int startIndex,int endIndex){
        if(target<datas[startIndex]||startIndex>endIndex|| target>datas[endIndex]){
            return -1;
        }
        int mobileIndex=(endIndex-startIndex)/2;
        int mobileEle=datas[mobileIndex];
        if(target==mobileEle){
            return mobileIndex;
        }
        if(target>mobileEle){
            return binarySearch(datas,target,mobileIndex+1,endIndex);
        }
        if(target<mobileEle){
            return binarySearch(datas,target,0,mobileIndex-1);
        }
        return -1;

    }
}
