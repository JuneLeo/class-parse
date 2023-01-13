package org.example;

public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void play(String name) {
        if (name.equals("basket")) {
            System.out.println("篮球");
        }

    }
}