package com.LabFisica.calcStats;

public class CalcStats {
    static double calcMean(double [] x ) {
        int n = 0;
        double aux = 0;
        for (double xx : x) {
            aux += xx;
            n ++;
        }
        return aux/(double) n;
    }
    
    static double [] calcMeanVar(double [] x){
        int n = 0;
        double aux1 = 0;
        double aux2 = 0;
        for (double xx : x){
            aux1 += xx ;
            aux2 += Math.pow(xx, 2);
            n ++;
        }
        var m = aux1/(double) n;
        var m2 = aux2/(double) n;
        double [] y = { m , m2-Math.pow(m,2)};
        return y;
    }

    static public void main(String[] args) { 
        double [] x = {0.1, 0.2, 0.3};
        System.out.println(Double.toString(calcMean(x)));
        var y = calcMeanVar(x);
        System.out.println("Mean value");
        System.out.println(Double.toString(y[0]));
        System.out.println("Variance");
        System.out.println(Double.toString(y[1]));
    }
}
