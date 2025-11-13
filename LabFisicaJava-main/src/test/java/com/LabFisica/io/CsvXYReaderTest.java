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

        assertEquals("X", xy.xName);
        assertEquals("Y", xy.yName);
        assertEquals(5, xy.x.length);
        assertArrayEquals(new double[]{1,3,5,7,9}, xy.x);
        assertArrayEquals(new double[]{2,4,6,8,10}, xy.y);
    }
}
