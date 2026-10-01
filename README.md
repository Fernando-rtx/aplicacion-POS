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
