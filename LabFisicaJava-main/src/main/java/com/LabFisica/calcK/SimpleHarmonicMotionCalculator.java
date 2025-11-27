import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SimpleHarmonicMotionCalculator {

    private static final String DATA_DIR = "C:/Users/arcan/OneDrive/Documentos/Proyecto Laboratorio Fisica 2025/LabFisicaJava-main/src/test/resources/";
    
    private static final List<ExperimentConfig> EXPERIMENT_CONFIGS = List.of(
        new ExperimentConfig(4, "tabla1.csv", "tabla1.2.csv"),
        new ExperimentConfig(3, "tabla2.csv", "tabla2.2.csv"),
        new ExperimentConfig(3, "tabla3.csv", "tabla3.2.csv"),
        new ExperimentConfig(2, "tabla4.csv", "tabla4.2.csv"),
        new ExperimentConfig(2, "tabla5.csv", "tabla5.2.csv"),
        new ExperimentConfig(1, "tabla6.csv", "tabla6.2.csv")
    );

    private static class ExperimentConfig {
        final int massFactor; final String xFileName; final String vFileName;
        public ExperimentConfig(int massFactor, String xFileName, String vFileName) {
            this.massFactor = massFactor; this.xFileName = xFileName; this.vFileName = vFileName;
        }
    }

    private static class ExperimentData {
        final int massFactor; final double[] x; final double[] v;
        public ExperimentData(int massFactor, double[] x, double[] v) {
            this.massFactor = massFactor; this.x = x; this.v = v;
        }
    }

    private static class MmcResult {
        double slope; double slopeError;
    }

    private static class KResult {
        final double kMRep; final double deltaKMRep;
        public KResult(double kMRep, double deltaKMRep) {
            this.kMRep = kMRep; this.deltaKMRep = deltaKMRep;
        }
    }
    
    private static Map<Double, Double> parseCsv(String fullPath) throws FileNotFoundException {
        Map<Double, Double> dataMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fullPath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) { 
                    double t = Double.parseDouble(parts[0].trim());
                    double value = Double.parseDouble(parts[1].trim());
                    dataMap.put(t, value);
                }
            }
        }
        return dataMap;
    }

    private static List<ExperimentData> loadAndMergeData() {
        List<ExperimentData> allData = new ArrayList<>();
        
        for (ExperimentConfig config : EXPERIMENT_CONFIGS) {
            try {
                Map<Double, Double> xData = parseCsv(DATA_DIR + config.xFileName);
                Map<Double, Double> vData = parseCsv(DATA_DIR + config.vFileName);
                
                List<Double> xList = new ArrayList<>();
                List<Double> vList = new ArrayList<>();
                
                List<Double> times = xData.keySet().stream().sorted().collect(Collectors.toList());

                for (double t : times) {
                    if (vData.containsKey(t)) {
                        xList.add(xData.get(t));
                        vList.add(vData.get(t));
                    }
                }
                
                if (xList.isEmpty()) {
                    System.err.printf("ADVERTENCIA: No se encontraron datos combinados para Masa %dm.\n", config.massFactor);
                    continue;
                }
                
                double[] xArray = xList.stream().mapToDouble(d -> d).toArray();
                double[] vArray = vList.stream().mapToDouble(d -> d).toArray();
                
                allData.add(new ExperimentData(config.massFactor, xArray, vArray));

            } catch (FileNotFoundException e) {
                System.err.println("ERROR: Archivo no encontrado. Verifique la ruta: " + DATA_DIR + config.xFileName + " y/o " + DATA_DIR + config.vFileName);
                return null; 
            }
        }
        return allData;
    }

    private static MmcResult calculateMMC(double[] X, double[] Y) {
        int N = X.length;
        if (N == 0 || X.length != Y.length) throw new IllegalArgumentException("Datos inválidos.");

        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        for (int i = 0; i < N; i++) {
            sumX += X[i]; sumY += Y[i]; sumXY += X[i] * Y[i]; sumX2 += X[i] * X[i];
        }

        double denom = (N * sumX2 - sumX * sumX);
        if (denom == 0) throw new IllegalStateException("Denominador del MMC es cero.");
        
        double A = (N * sumXY - sumX * sumY) / denom;
        double B = (sumY - A * sumX) / N;

        double e_y_squared = 0;
        for (int i = 0; i < N; i++) {
            double residual = Y[i] - (A * X[i] + B);
            e_y_squared += residual * residual;
        }
        
        double e_y_squared_adjusted = (N > 2) ? e_y_squared / (N - 2) : 0; 
        double Delta_A = Math.sqrt(e_y_squared_adjusted * N / denom);

        MmcResult result = new MmcResult();
        result.slope = A;
        result.slopeError = Delta_A;
        return result;
    }

    public static KResult calculateK() {
        List<ExperimentData> allData = loadAndMergeData();
        if (allData == null) return null;

        List<Double> kMBaseValues = new ArrayList<>();
        List<Double> deltaKMBaseValues = new ArrayList<>();

        System.out.println("--- 1. Cálculo de k para cada experimento (MMC) ---");
        for (int i = 0; i < allData.size(); i++) {
            ExperimentData data = allData.get(i);
            
            double[] X = Arrays.stream(data.x).map(val -> val * val).toArray();
            double[] Y = Arrays.stream(data.v).map(val -> val * val).toArray();

            MmcResult result = calculateMMC(X, Y);
            
            double kM = -result.slope;
            double deltaKM = result.slopeError;

            double kMBase = data.massFactor * kM;
            double deltaKMBase = data.massFactor * deltaKM;
            
            kMBaseValues.add(kMBase);
            deltaKMBaseValues.add(deltaKMBase);

            System.out.printf("Tabla %d (Masa %dm): k/m = %.4f ± %.4f -> k/m_base = %.4f ± %.4f\n", 
                                i + 1, data.massFactor, kM, deltaKM, kMBase, deltaKMBase);
        }

        double sumWeights = 0;
        double sumWeightedK = 0;
        for (int i = 0; i < kMBaseValues.size(); i++) {
            double deltaKMBase = deltaKMBaseValues.get(i);
            if (deltaKMBase > 0) {
                double weight = 1.0 / (deltaKMBase * deltaKMBase);
                sumWeights += weight;
                sumWeightedK += weight * kMBaseValues.get(i);
            }
        }

        double kMRep = sumWeightedK / sumWeights;
        double deltaKMRep = 1.0 / Math.sqrt(sumWeights);
        
        System.out.println("\n--- 2. Valor Representativo de k/m  ---");
        System.out.printf("k/m_rep: (%.4f ± %.4f) [1/s²]\n", kMRep, deltaKMRep);

        return new KResult(kMRep, deltaKMRep);
    }

    public static void calculatePeriod9m(double kMRep, double deltaKMRep) {
        int massFactor = 9;
        double TRep = 2 * Math.PI * Math.sqrt(massFactor / kMRep);
        double DeltaT = 0.5 * TRep * (deltaKMRep / kMRep);
        
        double TRepRounded = Math.round(TRep * 1000.0) / 1000.0;
        double DeltaTRounded = Math.round(DeltaT * 1000.0) / 1000.0;

        System.out.println("\n--- 3. Periodo de Oscilación para Masa 9m ---");
        System.out.printf("T_9m: (%.3f ± %.3f) [s]\n", TRepRounded, DeltaTRounded);
    }

    public static void generateOscillationData9m(double kMRep) {
        int massFactor = 9;
        double w = Math.sqrt(kMRep / massFactor);
        double A = 1.000;
        
        System.out.println("\n--- 4. Datos de Simulación para la Gráfica (Masa 9m) ---");
        System.out.printf("Frecuencia Angular w = %.4f rad/s\n", w);
        System.out.println("t[s],x[m],v[m/s]");

        for (double t = 0.0; t <= 6.0; t += 0.05) {
            double position = A * Math.cos(w * t);
            double velocity = -A * w * Math.sin(w * t);
            System.out.printf("%.3f,%.3f,%.3f\n", t, position, velocity);
        }
        System.out.println("FIN_DATOS_SIMULACION");
    }

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PROYECTO DE MOVIMIENTO ARMÓNICO SIMPLE ---");
        
        KResult kResult = calculateK();
        
        if (kResult != null) {
            calculatePeriod9m(kResult.kMRep, kResult.deltaKMRep);
            generateOscillationData9m(kResult.kMRep);
        } else {
            System.err.println("\nCálculos detenidos. Verifique la ruta y los 12 nombres de archivos.");
        }
    }
}