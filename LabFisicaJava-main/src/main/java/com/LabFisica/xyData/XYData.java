package com.LabFisica.xyData;

public class XYData {
    public final double[] x;
    public final double[] y;
    public final String xName;
    public final String yName;

    public XYData(double[] x, double[] y, String xName, String yName) {
        this.x = x;
        this.y = y;
        this.xName = xName;
        this.yName = yName;
    }
}