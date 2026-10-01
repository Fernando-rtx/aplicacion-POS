# Aplicación POS

Aplicación de escritorio Java Swing para gestionar productos, combos, usuarios y órdenes de un restaurante.

> **[Abrir demo web en vivo](https://fernando-rtx.github.io/aplicacion-POS/)** · [Ver la adaptación web hecha por Codex](https://github.com/Fernando-rtx/aplicacion-POS/tree/adaptacion-web-codex)
>
> La demo funciona en el navegador con datos de ejemplo. Las órdenes que crees se guardan solo en el almacenamiento local de tu navegador; no se conectan a una base de datos.

## Aplicación Java de escritorio

### Requisitos

- JDK 25
- Apache Ant (incluido normalmente con NetBeans)
- MariaDB

### Base de datos

Crea una base de datos llamada `rinconazteca` en MariaDB e importa el esquema desde un respaldo propio. La aplicación se conecta a `jdbc:mariadb://localhost:3306/rinconazteca` con el usuario `root` y contraseña vacía; cambia estos valores en `src/clases/Conexion.java` según tu entorno.

### Abrir y ejecutar

Abre esta carpeta como proyecto Ant en NetBeans y ejecuta el proyecto. Las dependencias MariaDB y OpenPDF están incluidas en `lib/`.
