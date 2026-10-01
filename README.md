# Aplicación POS

Aplicación de escritorio Java Swing para gestionar productos, combos, usuarios y órdenes de un restaurante.

## Demo web

Esta rama contiene una adaptación web de la aplicación Java de escritorio. La versión web fue hecha por Codex. Es una demo estática con productos y órdenes de ejemplo; las órdenes creadas se guardan en el navegador y no se conectan a la base de datos de la app Java.

Para publicarla gratis con GitHub Pages, abre **Settings → Pages**, selecciona **Deploy from a branch**, elige la rama `adaptacion-web-codex`, selecciona `/(root)` y pulsa **Save**. GitHub Pages publicará los archivos `index.html`, `app.js` y `styles.css` desde la raíz de esta rama.

## Proyecto Java

Los archivos del proyecto de escritorio se mantienen en `src/` y los del proyecto Ant/NetBeans en la raíz. Para ejecutar el proyecto Java se requiere JDK 25, Apache Ant y MariaDB. Las dependencias MariaDB y OpenPDF están en `lib/`.
