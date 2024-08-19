package edu.gatech.seclass.jobcompare6300;

import java.io.Serializable;

public class ComparisonSettings implements Serializable {
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int retirementBenefitsWeight;
    private int relocationStipendWeight;
    private int restrictedStockAwardWeight;

    public ComparisonSettings(int yearlySalaryWeight, int yearlyBonusWeight,
                              int retirementBenefitsWeight, int relocationStipendWeight,
                              int restrictedStockAwardWeight){
        this.yearlySalaryWeight = yearlySalaryWeight;
        this.yearlyBonusWeight = yearlyBonusWeight;
        this.retirementBenefitsWeight = retirementBenefitsWeight;
        this.relocationStipendWeight = relocationStipendWeight;
        this.restrictedStockAwardWeight = restrictedStockAwardWeight;

    }
    public int getTotalWeight()
    {
        return this.yearlySalaryWeight + this.yearlyBonusWeight + this.retirementBenefitsWeight + this.relocationStipendWeight + this.restrictedStockAwardWeight;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getRetiremenetBenefitsWeight() {
        return retirementBenefitsWeight;
    }

    public void setRetiremenetBenefitsWeight(int retiremenetBenefitsWeight) {
        this.retirementBenefitsWeight = retiremenetBenefitsWeight;
    }

    public int getRelocationStipendWeight() {
        return relocationStipendWeight;
    }

    public void setRelocationStipendWeight(int relocationStipendWeight) {
        this.relocationStipendWeight = relocationStipendWeight;
    }

    public int getRestrictedStockAwardWeight() {
        return restrictedStockAwardWeight;
    }

    public void setRestrictedStockAwardWeight(int restrictedStockAwardWeight) {
        this.restrictedStockAwardWeight = restrictedStockAwardWeight;
    }

    public static ComparisonSettings defaultSettings() {
        return new ComparisonSettings(1,
                1, 1, 1,
                1);
    }
}
