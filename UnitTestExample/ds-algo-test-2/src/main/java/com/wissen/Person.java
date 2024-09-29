package com.wissen;

import java.util.Objects;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        //convert current name String into lowercase always so that equals() & hashCode() contract will be valid
        name = name.toLowerCase();
        System.out.println("Lowercase name: " + name);
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
//        if(this != obj) return false;
        if(this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return this.name.equalsIgnoreCase(person.name) && this.age == person.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
