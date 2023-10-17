package com.workintech.demo.model;

public class JuniorDeveloper extends Developer {
    public JuniorDeveloper(long id, String name, double salary) {
        super(id, name, salary, Experience.JUNIOR);
    }
}