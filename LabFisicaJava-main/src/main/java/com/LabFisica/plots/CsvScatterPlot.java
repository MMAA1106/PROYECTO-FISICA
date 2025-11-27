package com.LabFisica.plots;

import com.LabFisica.xyData.XYData;
import com.LabFisica.io.CsvXYReader;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CsvScatterPlot {

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.err.println("Uso: CsvScatterPlot <archivo1.csv> <archivo2.csv>");
            System.exit(2);
        }

        XYData data1 = CsvXYReader.read(Path.of(args[0]));
        XYData data2 = CsvXYReader.read(Path.of(args[1]));

        
        XYChart chart1 = makePlot(data1, "Gráfico 1: " + data1.yName + " vs " + data1.xName);
        XYChart chart2 = makePlot(data2, "Gráfico 2: " + data2.yName + " vs " + data2.xName);

       
        XYData lin1 = linearizeAxB(data1);   
        XYData lin2 = linearizeAxB(data2);   

        XYChart chart1Lin = makePlot(
                lin1.x,
                lin1.y,
                "Linealización 1 (Ax + B)",
                data1.xName,
                data1.yName
        );

        XYChart chart2Lin = makePlot(
                lin2.x,
                lin2.y,
                "Linealización 2 (Ax + B)",
                data2.xName,
                data2.yName
        );

        
        showCharts(chart1, chart2, chart1Lin, chart2Lin);
    }


    public static XYData linearizeAxB(XYData data) {

        double[] x = data.x;
        double[] y = data.y;
        int n = x.length;

        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXX += x[i] * x[i];
        }

        
        double A = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double B = (sumY - A * sumX) / n;

        
        double[] yLine = new double[n];
        for (int i = 0; i < n; i++) {
            yLine[i] = A * x[i] + B;
        }

        return new XYData(x, yLine, data.xName, data.yName);
    }

    
    public static XYChart makePlot(XYData data, String titulo) {
        return makePlot(data.x, data.y, titulo, data.xName, data.yName);
    }

    
    public static XYChart makePlot(double[] xData, double[] yData,
                                   String title, String xTitle, String yTitle) {

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle(xTitle)
                .yAxisTitle(yTitle)
                .build();

        XYSeries series = chart.addSeries("Datos", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }

    
    public static void showCharts(XYChart c1, XYChart c2, XYChart c3, XYChart c4) {
        List<XYChart> charts = Arrays.asList(c1, c2, c3, c4);
        new SwingWrapper<>(charts).displayChartMatrix();
    }
}
