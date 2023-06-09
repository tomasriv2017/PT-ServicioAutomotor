# Prueba tecnica: Servicio del automotor - Certant
***
Este programa consiste en realizar la gestión de un negocio que se dedica a brindar servicios en una “boutique del automotor”
===
**El servidor se implementara con la version 2.7.11 de Spring boot**
---
Herramientas uitilizadas para el desarrollo del programa:
* IDE: Eclipse - Spring tools suite
* Intefaces creadas con: HTML5, CSS, BOOTSTRAP5, JQUERY Y JAVASCRIPT VANILLA
* Patron de diseño: MVC (Model-View-Controller)
* Framework para el backend: Spring boot (Java)
* Base de datos SQL: Mysql
* Controlador de versiones: Git - Utilizado con la interfaz Sourcetree

---
Dependencias utilizadas con Spring:
* Spring web: crea el servidor de la plataforma
* Spring JPA: ORM (Object Relational Mapping) que se encarga de realizar los mapeos de las clases de Java con las entidades de la base de datos
* Mysql connector - java: dependencia encargada de realizar la conexion de Java con el motor de base de datos MySQL
* Thymeleaf: motor de plantillas uitlizado para trabajar con la capa de vistas HTML del MVC 
* Mockito - JUnit: dependencias utilizadas para poder simular las pruebas unitarias

---
Como inicializar el proyecto cargando los datos del script de sql correctamente
 * 1-Crear una base de datos llamada **pruebatecnica**
 * 2-Levantar el servidor con el IDE: Spring Tools Suite para que se creen las tablas en la base de datos
 * 3-Parar el servidor y setearle a la variable del aplication.properties **spring.sql.init.mode** el valor: **always**
 * 4-Volver a correr el servidor con el IDE
