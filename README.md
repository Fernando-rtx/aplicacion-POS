# Aplicación POS

Aplicación de escritorio Java Swing para gestionar productos, combos, usuarios y órdenes de un restaurante.

## Requisitos

- JDK 25
- Apache Ant (incluido normalmente con NetBeans)
- MariaDB

## Base de datos

Crea una base de datos llamada `rinconazteca` en MariaDB e importa el esquema desde un respaldo propio. La aplicación se conecta a `jdbc:mariadb://localhost:3306/rinconazteca` con el usuario `root` y contraseña vacía; cambia estos valores en `src/clases/Conexion.java` según tu entorno.

## Abrir y ejecutar

Abre esta carpeta como proyecto Ant en NetBeans y ejecuta el proyecto. Las dependencias MariaDB y OpenPDF están incluidas en `lib/`.


## Demo web

En la rama `adaptacion-web-codex` hay una demo web estática en `web/`, adaptada desde esta aplicación Java de escritorio. La versión web fue hecha por Codex. Funciona en el navegador con datos de ejemplo guardados localmente y no se conecta a la base de datos Java.

Para publicarla gratis con GitHub Pages, abre **Settings → Pages**, selecciona la rama `adaptacion-web-codex` y la carpeta `/web`, y guarda. GitHub mostrará la URL pública de la demo en esa sección.
