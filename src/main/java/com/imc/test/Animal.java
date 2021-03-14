package com.imc.test;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<? super Animal> list = new ArrayList<>();
        list.add(new Animal());

        Object object = list.get(0);

        List<? super Dog> list2 = new ArrayList<>();
        //报错
//        list2.add(new Animal());

        List<Animal> animals = new ArrayList<>();
//        test(animals);

    }

    static void test(List<? super Dog> list) {

    }
}

class Dog {

}
