package com.ray.algorithm;

/**
 * 最后一个数不断与前一个数比较，如果后面数小，就调换位置。时间复杂度是o(n2)
 * Created by yiqing on 2018/6/9.
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] array={78,32,344,83,98,83};
        int length=array.length;
        for (int i = 0; i < length; i++) {
            boolean isChange=false;
            for (int j = length-1; j >i ; j--) {
                int next=array[j];
                int prve=array[j-1];
                if(next<prve){
                    int target=array[j-1];
                    array[j-1]=array[j];
                    array[j]=target;
                    isChange=true;
                }
            }
            if(!isChange){
                return;
            }
        }

    }

}
