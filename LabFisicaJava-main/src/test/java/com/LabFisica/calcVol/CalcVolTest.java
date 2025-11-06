package com.LabFisica.calcVol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalcVolTest {
    double eps = 1e-5; 
    double r = 1.2;
    double h = 1.2;
    double r_err = 0.01;
    double h_err = 0.01;

    @Test
    public void sphereErrTest() {
        var err_vol_diff = CalcVol.sphereErrDiff(r, r_err);
        var err_vol_deriv = CalcVol.sphereErrDeriv(r, r_err);

        assertEquals(err_vol_diff, err_vol_deriv, eps);
    }

    @Test
    public void cilinderErrTest() {
        var err_vol_diff = CalcVol.cilinderErrDiff(r, h, r_err, h_err);
        var err_vol_deriv = CalcVol.cilinderErrDeriv(r, h, r_err, h_err);

        assertEquals(err_vol_diff, err_vol_deriv, eps);
    }
}
