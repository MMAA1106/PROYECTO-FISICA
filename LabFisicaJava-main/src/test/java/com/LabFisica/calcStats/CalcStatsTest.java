package com.LabFisica.calcStats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalcStatsTest {
    double eps = 1e-10; 
    double [] x = {0.1, 0.2, 0.3}; 

    @Test
    public void calcMeanTest() {
        double x_mean = 0.2;
        double x_mean_calc = CalcStats.calcMean(x);

        assertEquals(x_mean, x_mean_calc, eps);
    }

    @Test
    public void calcMeanVarTest() {
        double x_mean = 0.2;
        double x_var = 2*0.01/3.0;
        var y = CalcStats.calcMeanVar(x);

        assertEquals(x_mean, y[0], eps);
        assertEquals(x_var, y[1], eps);
    }
}
