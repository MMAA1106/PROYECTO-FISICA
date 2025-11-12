# **GIT**
## **DATOS**
**Nombre:** Arcani Alvarez Miguel Mateo
**Grupo:** D6

--- 

## **TEORIA**

Git es un sistema de control de versiones distribuido creado por Linus Torvalds en 2005.  
Sirve para registrar, organizar y controlar los cambios en los archivos de un proyecto, permitiendo volver a versiones anteriores y trabajar en equipo sin sobrescribir el trabajo de otros.

Git guarda el historial de tu proyecto a través de *commits* (puntos de guardado).  
Cada vez que realizas un cambio y lo confirmas con un commit, Git lo registra con información como la fecha, el autor y el mensaje del cambio.

**Conceptos principales:**
- **Repositorio:** Carpeta donde se almacena el proyecto y su historial.  
- **Commit:** Punto de guardado con los cambios realizados.  
- **Rama (branch):** Línea de desarrollo independiente (por ejemplo, `main`).  
- **Merge:** Fusión de cambios entre ramas.  
- **Remote:** Repositorio alojado en un servidor como GitHub.

---

## **¿CÓMO USARLO?**

**Recomendación** Ejecutar Git Bash en la carpeta dónde se ubica el archivo que queremos subir.

**1. Abre el aplicativo Git Bash y luego en la terminal escribe.**
```bash
git init
```

**2. Agrega archivos al control de versiones**
```bash
git add nombreArchivo  (Subir un Archivo en específico)

git add .              (SUbir todos los archivos disponibles)
```

**3. Guarda los cambios con un mensaje que confirme la subida del archivo**
```bash
git commit -m "Archivo Subido"
```

**4️. Conecta tu repositorio local con GitHub**
```bash
git remote add origin https://github.com/usuario/repositorio.git
```

**5. Sube los cambios a GitHub**
```bash
git branch -M main
git push -u origin main
```

En caso de querer actualizar el archivo o los archivos tenemos lo siguiente:
```bash
git add .  o  git add nombreArchivo
git commit -m "Actualización del archivo"
git push
```
## **¿POR QUÉ USARLO?**

-Permite trabajar en equipo sin perder versiones.
-Guarda el historial completo del proyecto.
-Facilita la recuperación ante errores.
-Se integra con plataformas como GitHub y GitLab.

## **¿CUÁNDO USARLO?**

El uso de Git resulta especialmente conveniente al desarrollar proyectos de gran tamaño o en los que intervienen varios colaboradores, ya que permite mantener un control ordenado de las versiones y evitar la pérdida de información.
Asimismo, facilita llevar un registro claro y cronológico de los avances realizados, además de ofrecer la posibilidad de compartir el código o los archivos con otros usuarios de manera eficiente mediante plataformas como GitHub.