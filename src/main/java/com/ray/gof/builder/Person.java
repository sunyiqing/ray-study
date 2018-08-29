package com.ray.gof.builder;

public class Person {
    private String id;
    private String name;
    private String sex;

    private Person(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.sex=builder.sex;
    }

    public static class Builder{
        private String id;
        private String name;
        private String sex;

        public Builder id(String id){
            this.id=id;
            return this;
        }

        public Builder name (String name){
            this.name=name;
            return this;
        }

        public Builder sex(String sex){
            this.sex=sex;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Person.Builder().id("18").sex("男").build();
        System.out.println(person.toString());
    }
}
