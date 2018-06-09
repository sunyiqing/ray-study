package com.ray.algorithm;

/**
 *
 有三个数分别是2，1，3
 如果按照插入排序，类似于打牌抽牌，抓到的第一个数2放到第一位。抓到1要放到2前面。
 核心就是让当前新加入的数，和已经排序好的数据，全部进行对比，直到遇到比他小的数据。
 如果数据按照升序的数据进行排序，则需要执行o(n)，
 如果按照降序数据进行排序，则需要o(n2)

 * Created by yiqing on 2018/6/9.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a={12,6,423,3,2,121,56};
        int length=a.length;
        for (int i = 0; i < length; i++) {
            System.out.println("新来的数据:"+a[i]);
            //新来的数据与之前的数据进行对比
            for (int j = i; j >0 ; j--) {
                //如果小就交换
                if(a[j]<a[j-1]){
                    int target=a[j-1];
                    a[j-1]=a[j];
                    a[j]=target;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.println(a[i]);
        }
    }
}
