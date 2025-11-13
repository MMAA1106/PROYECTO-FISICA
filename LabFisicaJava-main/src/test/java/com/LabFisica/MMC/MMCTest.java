package com.LabFisica.MMC;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.LabFisica.io.CsvXYReader;
import com.LabFisica.xyData.XYData;

public class MMCTest {

    @Test
    void mmcCoeffAndErr() throws Exception {

        var url = getClass().getResource("/tabla1.csv");
        assertNotNull(url, "data.csv should be on test classpath");

        // Cargar el CSV
        var url = getClass().getResource("/tabla1.csv");
        assertNotNull(url, "El archivo CSV debe estar en la ruta de test classpath");
        f233d0b (Archivo Subido)
        var path = Paths.get(url.toURI());

        XYData xy = CsvXYReader.read(path);

        // Validar que los datos sean consistentes
        assertEquals(xy.x.length, xy.y.length, "X e Y deben tener la misma cantidad de datos");
        assertTrue(xy.x.length > 0, "Debe haber al menos un valor en X e Y");

        // Ejecutar MMC
        MMC mmc = new MMC(xy.x, xy.y);

        // Verificar que el objeto se creó correctamente
        assertNotNull(mmc, "El objeto MMC no debe ser nulo");

        // Opcional: verificar que los campos no sean NaN
        assertFalse(Double.isNaN(mmc.A), "MMC.A no debe ser NaN");
        assertFalse(Double.isNaN(mmc.B), "MMC.B no debe ser NaN");
        assertFalse(Double.isNaN(mmc.A_err), "MMC.A_err no debe ser NaN");
        assertFalse(Double.isNaN(mmc.B_err), "MMC.B_err no debe ser NaN");
    }
}
