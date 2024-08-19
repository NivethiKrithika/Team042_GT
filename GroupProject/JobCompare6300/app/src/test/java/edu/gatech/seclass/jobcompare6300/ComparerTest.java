package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;

public class ComparerTest {
    ComparisonSettings comparisonSettings = new ComparisonSettings(2,1,1,2,1);
    Comparer comparer = new Comparer(comparisonSettings);
    Location location = new Location("Chicago", "Illinois");
    /*Job job = new Job("Microsoft Developer", "Microsoft", location, 135,
            BigDecimal.valueOf(100000.00), BigDecimal.valueOf(5000.00), 50,
            BigDecimal.valueOf(10000.00), BigDecimal.valueOf(3000.00), false);*/
    Job job1 = new Job("Microsoft Developer", "Microsoft", location, 100,
            BigDecimal.valueOf(200000), BigDecimal.valueOf(3000), 40,
            BigDecimal.valueOf(10000), BigDecimal.valueOf(400000), false);
    Job job2 = new Job("Microsoft Developer", "Microsoft", location, 101,
            BigDecimal.valueOf(210000), BigDecimal.valueOf(30000), 30,
            BigDecimal.valueOf(20000), BigDecimal.valueOf(600000), false);
    Job job3 = new Job("Microsoft Developer", "Microsoft", location, 110,
            BigDecimal.valueOf(230000), BigDecimal.valueOf(10000), 20,
            BigDecimal.valueOf(30000), BigDecimal.valueOf(534000), false);
    Job job4 = new Job("Microsoft Developer", "Microsoft", location, 103,
            BigDecimal.valueOf(130000), BigDecimal.valueOf(3000), 10,
            BigDecimal.valueOf(15000), BigDecimal.valueOf(300000), false);
    Job job5 = new Job("Microsoft Developer", "Microsoft", location, 104,
            BigDecimal.valueOf(200000), BigDecimal.valueOf(5000), 40,
            BigDecimal.valueOf(0), BigDecimal.valueOf(500000), false);

    /*@Test
    public void testAYS(){
        BigDecimal expected = new BigDecimal("74074.07");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job));
    }
    @Test
    public void testAYB(){
        BigDecimal expected = new BigDecimal("3703.70");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job));
    }
    @Test
    public void testComputeScore(){
        assertEquals(125564.80500000001, comparer.computeScore(job), 0.0001);
    }*/
    @Test
    public void testAYS1(){
        BigDecimal expected = new BigDecimal("200000");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job1));
    }
    @Test
    public void testAYB1(){
        BigDecimal expected = new BigDecimal("3000");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job1));
    }
    @Test
    public void testComputeScore1(){
        assertEquals(86142.85714, comparer.computeScore(job1), 0.1);
    }
    @Test
    public void testAYS2(){
        BigDecimal expected = new BigDecimal("207920.7921");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job2));
    }
    @Test
    public void testAYB2(){
        BigDecimal expected = new BigDecimal("29702.9703");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job2));
    }
    @Test
    public void testComputeScore2(){
        assertEquals(99702.9703, comparer.computeScore(job2), 0.1);
    }
    @Test
    public void testAYS3(){
        BigDecimal expected = new BigDecimal("209090.9091");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job3));
    }
    @Test
    public void testAYB3(){
        BigDecimal expected = new BigDecimal("9090.909091");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job3));
    }
    @Test
    public void testComputeScore3(){
        assertEquals(94655.84416, comparer.computeScore(job3), 0.1);
    }
    @Test
    public void testAYS4(){
        BigDecimal expected = new BigDecimal("126213.5922");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job4));
    }
    @Test
    public void testAYB4(){
        BigDecimal expected = new BigDecimal("2912.621359");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job4));
    }
    @Test
    public void testComputeScore4(){
        assertEquals(53280.16644, comparer.computeScore(job4), 0.1);
    }
    @Test
    public void testAYS5(){
        BigDecimal expected = new BigDecimal("192307.6923");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job5));
    }
    @Test
    public void testAYB5(){
        BigDecimal expected = new BigDecimal("4807.692308");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job5));
    }
    @Test
    public void testComputeScore5(){
        assertEquals(84478.02198, comparer.computeScore(job5), 0.1);
    }
    /*@Test
    public void testAYS6(){
        BigDecimal expected = new BigDecimal("74074.07");
        assertEquals(expected, comparer.computeAdjustedYearlySalary(job));
    }
    @Test
    public void testAYB6(){
        BigDecimal expected = new BigDecimal("3703.70");
        assertEquals(expected, comparer.computeAdjustedYearlyBonus(job));
    }
    @Test
    public void testComputeScore6(){
        assertEquals(125564.80500000001, comparer.computeScore(job), 0.0001);
    }*/
}
