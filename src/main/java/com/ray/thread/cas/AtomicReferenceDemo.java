package com.ray.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by yiqing on 2021/3/3.
 */
class User{
    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    private String userName;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User u1 = new User("HAHA",11);
        User u2 = new User("HAHAha",12);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1,u2) + "--" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1,u2) + "--" + atomicReference.get().toString());

    }
}
