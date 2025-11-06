package com.LabFisica.MMC;

public class MMC {
    public final double[] x;
    public final double[] y;

    public int n;
    public double sum_x;
    public double sum_y;
    public double sum_x2;
    public double sum_xy;
    public double delta;
    public double A;
    public double B;
    public double sigma2;
    public double A_err;
    public double B_err;
    
    public MMC(double[] x, double[] y){
        this.x = x;
        this.y = y;
        this.n = x.length;
        _sums();
        _delta();
        _coeffA();
        _coeffB();
        _coeffA_err();
        _coeffB_err();
    }

    private void _sums(){
        double sum_x = 0;
        double sum_y = 0;
        double sum_x2 = 0;
        double sum_xy = 0;
        for(int i = 0; i<this.n; i++){
            sum_x += this.x[i];
            sum_y += this.y[i];
            sum_x2 += this.x[i] * this.x[i];
            sum_xy += this.x[i] * this.y[i];
        }
        this.sum_x = sum_x;
        this.sum_y = sum_y;
        this.sum_x2 = sum_x2;
        this.sum_xy = sum_xy;
    }

    private void _delta(){
        this.delta = 0;
    }

    private void _coeffA(){
        this.A = 0;
    }

    private void  _coeffB(){
        this.B = 0;
    }

    private void _coeffA_err(){
        this.A_err = 0;
    }

    private void _coeffB_err(){
        this.B_err = 0;
    }
}
