package com.ray.j2se.thread.threadpool;

import java.io.Serializable;

public  class User implements Serializable {
    private static final long serialVersionUID = 2488663702267110932L;
        private String userName;
        private String age;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }