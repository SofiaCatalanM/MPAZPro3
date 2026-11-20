# MPAZ Pro 3.0 - Aplicación Educativa 

Aplicación móvil desarrollada en Kotlin para la gestión de objetivos de aprendizaje, seguimiento de estudiantes y consumo de servicios de red. Este proyecto forma parte de la evaluación transversal de desarrollo de aplicaciones.

## Integrantes del Equipo
- Sofia: Desarrollo de la aplicación Android, arquitectura, base de datos y consumo de APIs.  
- Bastian: Desarrollo del backend (microservicios), pruebas unitarias, informe técnico y gestión del tablero Trello.

## Características Principales

### Modulo Estudiante
- Inicio de sesión con validación de datos.
- Navegación por 4 asignaturas (Lenguaje, Matemática, Ciencias, Historia).
- Visualización de objetivos de aprendizaje y contenidos cargados dinámicamente desde base de datos local (Room).
- Uso de recursos nativos: Cámara para actualización de foto de perfil y vibración háptica.
- Consumo de API externa (Open Trivia DB) para actividades interactivas.

### Modulo Docente
- Panel de control con acceso a lista de estudiantes y rendimientos.
- Generación de informes pedagógicos individuales con dashboard visual de métricas.

### Arquitectura y Tecnologías
- Lenguaje: Kotlin
- Arquitectura: Clean Architecture con separación de capas (UI, ViewModel, Repository, DAO).
- Persistencia: Room Database con estrategia de migración y seeder de datos iniciales.
- Red: Retrofit2 con OkHttpClient, interceptores de logging y Gson Converter.
- Pruebas: JUnit para pruebas unitarias de lógica de negocio.
- Interfaz: Híbrida (XML con ViewBinding y componentes de Material Design).

## Implementacion

- Flujos funcionales completos: Navegación implementada entre Login, Estudiante, Contenido, Actividad, Resultados y paneles de Docente.
- Recurso nativo: Implementación de cámara (Captura de imagen) y vibración del dispositivo.
- Integración con microservicios: Arquitectura Retrofit completa implementada. Actualmente utiliza un endpoint mock funcional para demostración de flujo de datos, listo para ser conectado al servidor real del equipo modificando únicamente la URL base en RetrofitClient.
- Consumo de API externa: Integración exitosa con Open Trivia DB, con manejo de estados de carga, éxito y error.
- Arquitectura limpia: Separación estricta de responsabilidades usando ViewModel, Repository y DAOs.
- Pruebas unitarias: Suite de pruebas implementada para la clase Validador (3 casos de prueba exitosos).
- APK firmado: Generación de paquete de distribución firmado (Release).
- Evidencia: Control de versiones gestionado mediante Git y commits descriptivos.

## Nota sobre la Integración de Microservicios
El proyecto incluye una integración funcional con un servidor de prueba (JSONPlaceholder) para demostrar la arquitectura de red, el manejo de modelos de datos y la respuesta del servidor. Para conectar el backend real desarrollado por el equipo, solo se requiere actualizar la variable BASE_URL en la clase RetrofitClient, sin necesidad de modificar la lógica de la aplicación.

## Instrucciones de Instalación y Ejecucion

1. Clonar este repositorio en su entorno local.
2. Abrir el proyecto en Android Studio (se recomienda AGP 8.2.x o superior).
3. Sincronizar el proyecto con los archivos de Gradle (Sync Project with Gradle Files).
4. Ejecutar la aplicación en un emulador de Android o en un dispositivo físico conectado mediante depuración USB.
5. Para instalar la versión de producción, utilizar el archivo APK firmado ubicado en la carpeta app/release/app-release.apk.

## Estructura del Proyecto
- app/src/main/java/com/mpazpro3/app/data: Entidades, DAOs y configuración de Room.
- app/src/main/java/com/mpazpro3/app/network: Interfaces de Retrofit y modelos de respuesta de API.
- app/src/main/java/com/mpazpro3/app/ui: Actividades, ViewModels y lógica de interfaz de usuario.
- app/src/main/java/com/mpazpro3/app/util: Clases de utilidad y validadores.
- app/src/test: Pruebas unitarias de la lógica de negocio.