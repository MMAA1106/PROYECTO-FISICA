package com.LabFisica.io;

import static org.junit.jupiter.api.Assertions.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class CsvXYReaderTest {
    String filename = "/tabla1.csv";

    @Test
    void readsTwoColumnsWithHeader() throws Exception {
        var url = getClass().getResource("/tabla1.csv");
        assertNotNull(url, "data.csv should be on test classpath");
        var path = Paths.get(url.toURI());

        var xy = CsvXYReader.read(path);

        assertNotNull(xy.xName, "La columna X debe tener un nombre");
        assertNotNull(xy.yName, "La columna Y debe tener un nombre");

        assertEquals(6, xy.x.length, "La columna X debe tener 6 valores");
        assertArrayEquals(new double[]{0.0, 0.2, 0.4, 0.6, 0.8, 1.0}, xy.x, 1e-9);
        assertArrayEquals(new double[]{1.0, 0.951, 0.809, 0.588, 0.309, 0.0}, xy.y, 1e-9);
}

}
