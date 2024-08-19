package edu.gatech.seclass.jobcompare6300;

import java.io.Serializable;
import java.math.BigDecimal;

public class Job implements Serializable {
    private String title;
    private String company;
    private Location location;
    private int costOfLiving;
    private BigDecimal yearlySalary;
    private BigDecimal yearlyBonus;
    private int retirementBenefits;
    private BigDecimal relocationStipend;
    private BigDecimal restrictedStockUnitAward;
    private boolean isCurrentJob;
    private boolean isSelected = false;

    public Job (String title, String company, Location location, int costOfLiving, BigDecimal yearlySalary,
                BigDecimal yearlyBonus, int retirementBenefits, BigDecimal relocationStipend,
                BigDecimal restrictedStockUnitAward, boolean isCurrentJob){
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.restrictedStockUnitAward = restrictedStockUnitAward;
        this.isCurrentJob = isCurrentJob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BigDecimal getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(BigDecimal yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public BigDecimal getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(BigDecimal yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(int retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public BigDecimal getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(BigDecimal relocationStipend) {
        this.relocationStipend = relocationStipend;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public BigDecimal getRestrictedStockUnitAward() {
        return restrictedStockUnitAward;
    }

    public void setRestrictedStockUnitAward(BigDecimal restrictedStockUnitAward) {
        this.restrictedStockUnitAward = restrictedStockUnitAward;
    }

    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean selected){
        this.isSelected = selected;
    }
}
