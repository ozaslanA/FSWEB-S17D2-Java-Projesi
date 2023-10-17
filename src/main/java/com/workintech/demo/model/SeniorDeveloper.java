package com.workintech.demo.model;


public class SeniorDeveloper extends Developer {
    public SeniorDeveloper(long id, String name, double salary) {
        super(id, name, salary, Experience.SENIOR);
    }   }