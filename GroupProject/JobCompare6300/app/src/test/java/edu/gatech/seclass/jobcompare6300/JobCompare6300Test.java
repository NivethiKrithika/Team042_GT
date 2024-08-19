package edu.gatech.seclass.jobcompare6300;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JobCompare6300Test {
    CurrentJobActivity jobService = new CurrentJobActivity();
    @Test
    public void userInputValidationCorrectInput(){
        assertEquals(true, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120", "150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullCompany(){
        assertEquals(false, jobService.validateUserInput("","Developer","Illinois","Chicago","120","150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullTitle(){
        assertEquals(false, jobService.validateUserInput("Microsoft","","Illinois","Chicago","120","150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullState(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","","Chicago","120","150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullCity(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","","120","150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullCostOfLivingIndex(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","","150000","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullYearlySalary(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","","20000","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullYearlyBonus(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","","45","10000","25",true));
    }
    @Test
    public void userInputValidationNullRetirementBenefit(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","500","","10000","25",true));
    }
    @Test
    public void userInputValidationNullRelocationStipend(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","","25",true));
    }
    @Test
    public void userInputValidationNullRsua(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","100","",true));
    }

    @Test
    public void userInputValidationRetirementBenefitBelowRange(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","-5","10000","25",true));
    }
    @Test
    public void userInputValidationRetirementBenefitDecimal(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","4.0","10000","25",true));
    }
    @Test
    public void userInputValidationRetirementBenefitAboveRange(){
        assertEquals(false, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","105","10000","25",true));
    }
    @Test
    public void userInputValidationRetirementBenefitBorderRange1(){
        assertEquals(true, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","0","10000","25",true));
    }
    @Test
    public void userInputValidationRetirementBenefitBorderRange2(){
        assertEquals(true, jobService.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","100","10000","25",true));
    }
    /*@Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }
    @Test
    public void userInputValidationNullCity(){
        Job job = new Job();
        assertEquals(false, job.validateUserInput("Microsoft","Developer","Illinois","Chicago","120","150000","20000","45","10000",true));
    }*/
}