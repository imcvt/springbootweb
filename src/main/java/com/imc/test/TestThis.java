package com.imc.test;

import java.lang.reflect.ParameterizedType;

public class TestThis {
    public static void main(String[] args) {
        C c = new C();
    }
}

class P<T> {
    public P() {
        System.out.println("type is parent");
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.println("realclass--" + parameterizedType.getActualTypeArguments()[0]);

    }
}

class C extends P<C>{
    public C() {
        System.out.println("c--created"+ this.getClass());
    }
}
