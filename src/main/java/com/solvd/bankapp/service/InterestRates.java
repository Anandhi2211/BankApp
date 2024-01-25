package com.solvd.bankapp.service;

public enum InterestRates implements IRate {

    DEFAULT(0.004),

    HIGH_YIELD(0.04),

    CD(0.06);

    private final double rate;
    InterestRates(double rate) {
        this.rate = rate;
    }


    @Override
    public double getRate() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return name().toLowerCase().replace('_', ' ');
    }
}
