package com.imc.test;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TestExpression {
    private String name = "nn";
    private Integer age = 30;

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        TestExpression t = new TestExpression();
        System.out.println(parser.parseExpression("name").getValue(t, String.class));
        System.out.println(parser.parseExpression("age").getValue(t, Integer.class));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
