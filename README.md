# 🏁 Foro Hub - Challenge Back End (Alura Latam)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)

Bienvenido al proyecto **Foro Hub**, una réplica a nivel de Back End de un foro de discusión (estilo Alura). Este proyecto es una API REST robusta diseñada para gestionar tópicos de duda, permitiendo la interacción estructurada entre usuarios, cursos y respuestas.

---

## 🎯 Objetivo del Desafío
El desafío consiste en aplicar los conocimientos de **Spring Boot** para crear una API que cumpla con el modelo **CRUD** (Create, Read, Update, Delete) y que siga las mejores prácticas del modelo REST, incluyendo:
* Persistencia de datos en una base de datos relacional.
* Validaciones de reglas de negocio (evitar tópicos duplicados).
* Seguridad mediante autenticación y autorización con **JWT**.

---

## 🚀 Funcionalidades Principales

La API se centra en la gestión de **Tópicos** y ofrece los siguientes endpoints:

1. **Crear un nuevo tópico:** Registro de dudas con título, mensaje, autor y curso.
2. **Mostrar todos los tópicos:** Listado completo con soporte para paginación.
3. **Mostrar un tópico específico:** Consulta detallada mediante el ID.
4. **Actualizar un tópico:** Modificación de datos existentes con validación.
5. **Eliminar un tópico:** Remoción física o lógica del registro.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3
* **Gestión de Dependencias:** Maven
* **Base de Datos:** MySQL
* **Migraciones:** Flyway
* **Seguridad:** Spring Security & JWT (JSON Web Token)
* **Documentación:** SpringDoc / Swagger (Opcional)

---

## 📂 Estructura del Proyecto (Capas)

* `controller`: Endpoints de la API y manejo de peticiones HTTP.
* `domain`: Entidades JPA, Repositorios y DTOs (Data Transfer Objects).
* `infra`: Configuraciones de Seguridad (JWT) y Tratamiento de Errores.
* `service`: Lógica de negocio y validaciones personalizadas.
