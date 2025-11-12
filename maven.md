# **MAVEN**
## **DATOS**
**Nombre:** Arcani Alvarez Miguel Mateo
**Grupo:** D6

## **TEORIA**

Apache Maven es una herramienta la cuál se aplica en el ecosistema Java para la gestión y automatización de la construcción  de proyectos. Su objetivo es estandarizar el proceso de desarrollo, facilitando la compilación, la gestión de librerías y la distribución del producto final.

---

## **¿CÓMO USARLO?**
1. **Abrir Git Bash**: Utiliza Git Bash como terminal para ejecutar comandos Maven.
2. **Verificar instalación de Maven**:
   ```bash
   mvn -v
   ```
3. **Crear un proyecto Maven**: 
   ```bash
   mvn archetype:generate -DgroupId=com.miempresa -DartifactId=mi-proyecto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```

4. **Navegar al proyecto**:
   ```bash
   cd mi-proyecto
   ```

5. **Agregar dependencias**: Editar pom.xml y añadir librerías necesarias dentro de <dependencies>.

6. **Compilar y construir el proyecto**:
   ```bash
   mvn clean install
   ```

7. **Ejecutar pruebas**:
   ```bash
   mvn test
   ```
## **¿POR QUÉ USARLO?**
- Gestión automática de dependencias: No necesitas descargar manualmente librerías externas.

- Estandarización de proyectos: Facilita la organización y mantenimiento del código.

- Automatización del ciclo de trabajo: Compilación, pruebas, empaquetado y despliegue de forma automatizada.

- Compatibilidad con IDEs y terminales: Eclipse, IntelliJ y Git Bash soportan Maven directamente.

## **¿CUÁNDO USARLO?**
- Cuando se trabaja en proyectos grandes con múltiples dependencias externas.

- Cuando se necesita un proceso de compilación reproducible en distintos entornos.

- Para automatizar tareas como pruebas, generación de documentación y empaquetado.

- En proyectos donde se busca colaboración en equipo, manteniendo una estructura y dependencias estandarizadas.