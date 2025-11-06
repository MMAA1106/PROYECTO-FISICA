package com.LabFisica.calcVol;

public class CalcVol {
    static double sphere(double r){
        return 4 * Math.PI * Math.pow(r,3) / 3;
    }

    static double sphereErrDeriv(double r, double r_err){
        return 4 * Math.PI * Math.pow(r,2) * r_err;
    }

    static double sphereErrDiff(double r, double r_err){
        return Math.abs((sphere(r+r_err) - sphere(r-r_err))/(2));
    }

    static double cilinder(double r, double h){
        return Math.PI * Math.pow(r,2) * h;
    }

    static double cilinderErrDeriv(double r, double h, double r_err, double h_err){
        var r_err_contribution = 2 * Math.PI * r * h * r_err;
        var h_err_contribution = Math.PI * Math.pow(r,2) * h_err;
        return Math.sqrt(Math.pow(r_err_contribution,2) + Math.pow(h_err_contribution,2));
    }

    static double cilinderErrDiff(double r, double h, double r_err, double h_err){
        var r_err_contribution = Math.abs((cilinder(r+r_err,h) - cilinder(r-r_err,h))/2);
        var h_err_contribution = Math.abs((cilinder(r,h+h_err) - cilinder(r,h-h_err))/2);
        return Math.sqrt(Math.pow(r_err_contribution,2) + Math.pow(h_err_contribution,2));
    }

    public static void mainSphere(String[] args){
        double r = 1.2;
        double r_err = 0.01;

        var vol_s = sphere(r);
        var err_vol_diff = sphereErrDiff(r, r_err);
        var err_vol_deriv = sphereErrDeriv(r, r_err);
    
        System.out.println("");
        System.out.println("Volumen esfera");
        System.out.println(Double.toString(vol_s));
        System.out.println("Error volumen diferencias finitas");
        System.out.println(Double.toString(err_vol_diff));
        System.out.println("Error volumen derivada");
        System.out.println(Double.toString(err_vol_deriv));
    }

    public static void mainCilinder(String[] args){
        double r = 1.2;
        double h = 1.2;
        double r_err = 0.01;
        double h_err = 0.01;

        var vol_c = cilinder(r, h);
        var err_vol_diff = cilinderErrDiff(r, h, r_err, h_err);
        var err_vol_deriv = cilinderErrDeriv(r, h, r_err, h_err);
    
        System.out.println("");
        System.out.println("Volumen cilindro");
        System.out.println(Double.toString(vol_c));
        System.out.println("Error volumen diferencias finitas");
        System.out.println(Double.toString(err_vol_diff));
        System.out.println("Error volumen derivada");
        System.out.println(Double.toString(err_vol_deriv));
    }

    public static void main(String[] args){
        mainSphere(args);
        mainCilinder(args);
    }
}

