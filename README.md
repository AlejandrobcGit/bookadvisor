# 📚 Proyecto de Prácticas: Catálogo de Libros
gg
## 📝 Descripción
Aplicación web desarrollada en Java con Spring Boot, orientada a la gestión de un catálogo de libros. Permite consultar, puntuar y comentar libros, realizar búsquedas y obtener rankings según las puntuaciones de los usuarios. Ideal para prácticas, con base de datos H2 que se crea y carga automáticamente en cada ejecución.

---

## 🧠 Sobre el proyecto
    Este proyecto implementa un sistema de gestión completo con:
        -CRUD de entidades (empleados / cuentas / usuarios)
        -Seguridad con autenticación y autorización
        -Persistencia con base de datos H2
        -Validación de datos
        -Arquitectura en capas

    ✔ Diseñado siguiendo buenas prácticas de desarrollo backend
    ✔ Preparado para migración a API REST + frontend moderno

---

## 🏗️ Arquitectura
El proyecto sigue arquitectura multicapa (clean architecture básica):

Controller → Service → Repository → Database

    -Controller → Manejo de peticiones HTTP
    -Service → Lógica de negocio
    -Repository → Acceso a datos (Spring Data JPA)
    -Entity → Modelo de dominio

💡 Separación clara de responsabilidades → código mantenible y escalable

---

## 🔐 Seguridad
    Implementación con Spring Security:
        -Autenticación mediante login
        -Autorización basada en roles
    Roles:
        👤 Usuario → puede crear recursos / David → 1234
        🔧 Administrador → acceso total (editar/eliminar) / Ale → 1234
        🧑‍💼 Manager  → acceso total a excepción e la gestión de ususarios / Miguel → 1234
        👁️ Visitante → solo lectura
    Ejemplo de reglas:
        /            → público
        /nuevo/**    → USER, ADMIN
        /editar/**   → ADMIN
        /borrar/**   → ADMIN
    ✔ Protección de rutas
    ✔ Manejo de errores de acceso

---

## ⚙️ Funcionalidades principales

    ✅ CRUD completo
    ✅ Validación con anotaciones (@NotEmpty, @Email, etc.)
    ✅ Filtros y búsquedas
    ✅ Gestión de roles
    ✅ Login / Logout
    ✅ Inicialización de datos automática
    ✅ Acceso controlado por permisos

---

## 🛠️ Tecnologías usadas
    -Java 17 → Lenguaje principal
    -Spring Boot → Framework backend
    -Spring MVC → Control de peticiones
    -Spring Security → Autenticación y autorización
    -Spring Data JPA → Persistencia
    -Base de datos H2 → Almacenamiento
    -Thymeleaf → Motor de vistas (actual)
    -Maven → Gestión de dependencias

---

## 🚀 Ejecución del proyecto
        git clone <repo>
        cd proyecto
        mvn spring-boot:run 
    Acceder en: http://localhost:9000

---

## ⚠️ Problemas detectados (análisis técnico)
    Este proyecto incluye una evaluación crítica:
        -Thymeleaf usado de forma básica (poco dinámico)
        -Código redundante en servicios
        -Acoplamiento frontend-backend (MVC clásico)
    👉 Estos puntos han sido identificados como mejoras del sprint

## 🚀 Mejoras y evolución (Roadmap)
    🔹 Backend
        -Migración a API REST (@RestController)
        -Uso de DTOs
        -Control global de excepciones
        -Eliminación de código redundante
    🔹 Frontend
        -Sustitución de Thymeleaf por React
        -Separación frontend/backend
        -Consumo de API REST

---

## ⚛️ Futuro: Arquitectura moderna
    Objetivo del proyecto:
        Spring Boot (API REST) + React (SPA)
    ✔ Backend desacoplado
    ✔ Frontend independiente
    ✔ Escalabilidad real

---

## 💡 Lo que demuestra este proyecto
    ✅ Conocimiento de Spring Boot
    ✅ Implementación de seguridad web
    ✅ Trabajo con bases de datos relacionales
    ✅ Diseño en capas
    ✅ Capacidad de análisis y mejora de código
    ✅ Preparación para arquitecturas modernas

---

## 📌 Estado
    🟢 Funcional
    🟡 En evolución hacia arquitectura REST + React (Mediano Plazo)