package com.workintech.demo.model;

import com.workintech.demo.tax.Taxable;

public class DeveloperFactory {

    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer savedDeveloper = null;
        if(developer.getExperience().name().equalsIgnoreCase("junior")){
            savedDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary()*taxable.getSimpleTaxRate()));
        } else if(developer.getExperience().name().equalsIgnoreCase("mid")){
            savedDeveloper = new MidDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary()*taxable.getMiddleTaxRate()));
        } else if(developer.getExperience().name().equalsIgnoreCase("senior")){
            savedDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary()*taxable.getUpperTaxRate()));
        }
        return savedDeveloper;
    }}
