package com.LabFisica.io;

import com.LabFisica.xyData.XYData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvXYReader {

    public static XYData read(Path csvPath) throws IOException {
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();
        String xName = "X";
        String yName = "Y";

        try (CSVReader reader = new CSVReader(new FileReader(csvPath.toFile()))) {
            String[] row;

            // header 
            row = reader.readNext();
            if (row != null && row.length >= 2) {
                xName = row[0] != null && !row[0].isBlank() ? row[0].trim() : "X";
                yName = row[1] != null && !row[1].isBlank() ? row[1].trim() : "Y";
            }

            // data rows
            while ((row = reader.readNext()) != null) {
                if (row.length < 2) continue;
                String sx = row[0] == null ? "" : row[0].trim();
                String sy = row[1] == null ? "" : row[1].trim();
                if (sx.isEmpty() || sy.isEmpty()) continue;
                try {
                    xs.add(Double.parseDouble(sx));
                    ys.add(Double.parseDouble(sy));
                } catch (NumberFormatException ignored) {
                    // skip malformed line
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException("Invalid CSV format: " + e.getMessage(), e);
        }

        return new XYData(toArray(xs), toArray(ys), xName, yName);
    }

    private static double[] toArray(List<Double> list) {
        double[] a = new double[list.size()];
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
        return a;
    }
}
