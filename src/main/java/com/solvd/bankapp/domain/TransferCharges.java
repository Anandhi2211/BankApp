package com.solvd.bankapp.domain;

public enum TransferCharges implements IRate {

    UNDER_500(2.99),

    BETWEEN_500_AND_1000(4.99),

    OVER_1000(9.99);

    private final double charge;
    TransferCharges(double charge) {
        this.charge = charge;
    }

    @Override
    public double getRate() {
        return charge;
    }

    @Override
    public String getDisplayName() {
        return name().toLowerCase().replace('_', ' ');
    }
}
