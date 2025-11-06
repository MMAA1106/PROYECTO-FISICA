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

        // Leer ambos archivos CSV
        XYData data1 = CsvXYReader.read(Path.of(args[0]));
        XYData data2 = CsvXYReader.read(Path.of(args[1]));

        // Crear gráficos con títulos personalizados
        XYChart chart1 = makePlot(data1, "Posición en función del tiempo");
        XYChart chart2 = makePlot(data2, "Velocidad en función del tiempo");

        // Mostrar ambos gráficos en una ventana
        showCharts(chart1, chart2);
    }

    // Crea un gráfico con título personalizado
    public static XYChart makePlot(XYData data, String titulo) {
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title(titulo)
                .xAxisTitle(data.xName)
                .yAxisTitle(data.yName)
                .build();

        XYSeries series = chart.addSeries("Datos", data.x, data.y);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        series.setMarker(SeriesMarkers.CIRCLE);

        return chart;
    }

    // Muestra varios gráficos en la misma ventana
    public static void showCharts(XYChart chart1, XYChart chart2) {
        List<XYChart> charts = Arrays.asList(chart1, chart2);
        new SwingWrapper<>(charts).displayChartMatrix();
    }

    // Guarda un gráfico como imagen PNG
    public static void saveChart(XYChart chart, String filename) throws Exception {
        BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
    }
}
