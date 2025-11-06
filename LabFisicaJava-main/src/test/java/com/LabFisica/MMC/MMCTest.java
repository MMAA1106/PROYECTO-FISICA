package com.LabFisica.MMC;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.LabFisica.io.CsvXYReader;
import com.LabFisica.xyData.XYData;

public class MMCTest {
    double eps = 1e-9;

    @Test
    void mmcCoeffAndErr() throws Exception {
        var url = getClass().getResource("/data.csv");
        assertNotNull(url, "data.csv should be on test classpath");
        var path = Paths.get(url.toURI());

        XYData xy = CsvXYReader.read(path);

        MMC mmc = new MMC(xy.x, xy.y);

        assertEquals(0.0, mmc.A, eps);
        assertEquals(0.0, mmc.B, eps);
        assertEquals(0.0, mmc.A_err, eps);
        assertEquals(0.0, mmc.B_err, eps);
    }
}

