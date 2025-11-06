# LabFisica General

## Como usar

1. Compilar proyecto usando maven  
`mvn compile`

1. Test que todo va bien
`mvn test`

1. Ver un grafico
`mvn -q -DskipTests exec:java -Dexec.mainClass=com.LabFisica.plots.CsvScatterPlot -Dexec.args=src/test/resources/data.csv`
