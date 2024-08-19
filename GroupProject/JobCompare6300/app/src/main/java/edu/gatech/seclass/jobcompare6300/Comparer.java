package edu.gatech.seclass.jobcompare6300;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Comparer {
    private ComparisonSettings comparisonSettings;
    private HashMap<Job, Double> rankedJobs = new HashMap<>();

    public Comparer(ComparisonSettings comparisonSettings){
        this.comparisonSettings = comparisonSettings;
    }

    public double computeScore(Job job) {
        double AYS = computeAdjustedYearlySalary(job).doubleValue();
        double AYB = computeAdjustedYearlyBonus(job).doubleValue();
        double RS = job.getRelocationStipend().doubleValue();
        int RPB = job.getRetirementBenefits();
        double RSUA = job.getRestrictedStockUnitAward().doubleValue();
        double score = (((double)this.comparisonSettings.getYearlySalaryWeight() / (double)this.comparisonSettings.getTotalWeight()) * AYS)+
                (((double)this.comparisonSettings.getYearlyBonusWeight()/(double)this.comparisonSettings.getTotalWeight())* AYB) +
                (((double)this.comparisonSettings.getRelocationStipendWeight() / (double)this.comparisonSettings.getTotalWeight())* RS) +
                (((double)this.comparisonSettings.getRetiremenetBenefitsWeight()/ (double)this.comparisonSettings.getTotalWeight()) *((double)((RPB*AYS)/100))) +
                (((double)this.comparisonSettings.getRestrictedStockAwardWeight()/(double)this.comparisonSettings.getTotalWeight()) * ((double)RSUA/4));
        return new BigDecimal(score).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static ArrayList<Job> compareTwoJobs(Job job1, Job job2) {
        ArrayList<Job> twoJobList = new ArrayList<>();
        //set job 1 and job 2 yearly and bonus to the adjusted yearly salary and bonus
        job1.setYearlySalary(computeAdjustedYearlySalary(job1));
        job1.setYearlyBonus(computeAdjustedYearlyBonus(job1));
        job2.setYearlySalary(computeAdjustedYearlySalary(job2));
        job2.setYearlyBonus(computeAdjustedYearlyBonus(job2));
        twoJobList.add(job1);
        twoJobList.add(job2);
        return twoJobList;
    }

    //takes in joblist, uses comparisonsettings to rank jobs then return
    public HashMap<Job, Double> rankJobs(ArrayList<Job> jobsList){
        for (int i = 0; i < jobsList.size(); i++) {
            double score = computeScore(jobsList.get(i));
            rankedJobs.put(jobsList.get(i), score);
        }
        //TODO: rank jobs here use computeScore()
        return rankedJobs;
    }

    //use weight to compute yearly salary based on cost of living
    public static BigDecimal computeAdjustedYearlySalary(Job job){
        BigDecimal ays= new BigDecimal(
                (job.getYearlySalary().doubleValue()*100 / job.getCostOfLiving()));
        return ays.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal computeAdjustedYearlyBonus(Job job){
        BigDecimal ayb = new BigDecimal(job.getYearlyBonus().doubleValue()*100
                /job.getCostOfLiving());
        return ayb.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
