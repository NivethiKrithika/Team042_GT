package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompareSettingsTest {
    SettingsActivity settings = new SettingsActivity();

    @Test
    public void testDefaultComparisonSettings() {
        ComparisonSettings comparisonSettings = ComparisonSettings.defaultSettings();
        assertEquals(true, settings.validateComparisonSettings(String.valueOf(comparisonSettings.getYearlySalaryWeight()),
                String.valueOf(comparisonSettings.getYearlyBonusWeight()), String.valueOf(comparisonSettings.getRetiremenetBenefitsWeight()),
                String.valueOf(comparisonSettings.getRelocationStipendWeight()),
                String.valueOf(comparisonSettings.getRestrictedStockAwardWeight()), true));
    }
    @Test
    public void testNullYsWeight() {
        assertEquals(false, settings.validateComparisonSettings("", "2", "1","0", "5", true));
    }
    @Test
    public void testNullYbWeight() {
        assertEquals(false, settings.validateComparisonSettings("0", "", "1", "0", "5", true));
    }
    @Test
    public void testNullRtBenefitWeight() {
        assertEquals(false, settings.validateComparisonSettings("0", "10", "", "0", "5", true));
    }
    @Test
    public void testNullRsWeight() {
        assertEquals(false, settings.validateComparisonSettings("0", "1", "90", "", "5", true));
    }
    @Test
    public void testNullRsuaWeight() {
        assertEquals(false, settings.validateComparisonSettings("0", "7", "1", "0", "", true));
    }
}
