# **UNITTEST**
## **DATOS**
**Nombre:** Arcani Alvarez Miguel Mateo
**Grupo:** D6

## **TEORIA**
El **Unit Testing** o **pruebas unitarias** es una técnica de pruebas de software que consiste en verificar de manera automática y aislada el comportamiento de las unidades más pequeñas de un programa, generalmente métodos o funciones.  

En Java, la herramienta más usada para Unit Testing es **JUnit**, un framework que permite escribir y ejecutar pruebas de forma estructurada. Las pruebas unitarias ayudan a:

- Detectar errores de forma temprana.
- Garantizar que los cambios en el código no rompan funcionalidades existentes.
- Documentar el comportamiento esperado de las funciones.
- Mejorar la calidad y mantenibilidad del código.

---

## **¿CÓMO USARLO?**
```bash
# 1. Abrir Git Bash en la carpeta del proyecto

# 2. Verificar instalación de Java y Maven
```bash
java -version
mvn -v
```
# 3. Crear un proyecto Maven (si no tienes uno)

mvn archetype:generate -DgroupId=com.miempresa -DartifactId=mi-proyecto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd mi-proyecto

# 4. Agregar JUnit al proyecto

 Editar pom.xml y añadir dentro de <dependencies>:
 <dependency>
   <groupId>junit</groupId>
     <artifactId>junit</artifactId>
     <version>4.13.2</version>
    <scope>test</scope>
 </dependency>

# 5. Crear clase de prueba en src/test/java/CalculadoraTest.java
```bash
# Ejemplo de contenido de CalculadoraTest.java:
# import org.junit.Test;
# import static org.junit.Assert.assertEquals;
#
# public class CalculadoraTest {
#     @Test
#     public void testSumar() {
#         Calculadora calc = new Calculadora();
#         assertEquals(8, calc.sumar(5, 3));
#     }
# }
```
# 6. Ejecutar pruebas desde Git Bash
```bash
mvn test
```
# 7. Limpiar compilaciones previas (opcional)
```bash
mvn clean
```
# 8. Compilar y reconstruir después de cambios
```bash
mvn clean install
```
# 9. Ver resultados de las pruebas
En Git Bash se mostrará un resumen de tests ejecutados, fallidos o exitosos
#Para más detalles, revisar: target/surefire-reports/*.txt


## **¿POR QUÉ USARLO?**
- Prevención de errores: Detecta fallos antes de que lleguen a producción.

- Mantenimiento más sencillo: Permite refactorizar código con seguridad.

- Documentación viva: Cada test describe cómo debería comportarse el código.

- Automatización: Integración continua (CI/CD) puede ejecutar tests automáticamente.

## **¿CUÁNDO USARLO?**
- Al crear funciones críticas que deben funcionar correctamente.

- En proyectos grandes donde múltiples desarrolladores trabajan simultáneamente.

- Antes de hacer cambios o refactorizaciones importantes en el código.

- Para garantizar que los bugs corregidos no reaparezcan (regresión).