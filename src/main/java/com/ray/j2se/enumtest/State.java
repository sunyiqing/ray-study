package com.ray.j2se.enumtest;

/**
 * Created by yiqing on 2017/9/16.
 */
public enum  State {
    Normal("正常",1),
    Update("更新",2),
    Deleted("删除",3);

    private String name;
    private int index;
    private State(String name,int index){
        this.name=name;
        this.index=index;
    }

    public static String getName(int index){
        for (State c:State.values()){
            if(c.index==index){
                return c.name;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
