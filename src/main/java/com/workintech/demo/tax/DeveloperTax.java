package com.workintech.demo.tax;

public class DeveloperTax implements Taxable {

    @Override
    public double getSimpleTaxRate() {
        return 0.25;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.3;
    }

    @Override
    public double getUpperTaxRate() {
        return 0.4;
    }
}
