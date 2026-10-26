# MPAZ PRO 3.0

Aplicacion movil educativa para Tercero Basico de la Escuela Escritora Marcela Paz.

## Integrantes
- Bastián Román
- Sofia Catalán

## Funcionalidades implementadas

### Perfil Estudiante
- Seleccion de asignatura (Lenguaje, Matematica, Ciencias, Historia)
- Visualizacion de objetivos de aprendizaje
- Desarrollo de actividades con retroalimentacion visual
- Evaluacion final con limite de intentos
- Pantalla de resultados con porcentaje y mensaje

### Perfil Docente
- Asignacion de objetivos de aprendizaje
- Generacion de informe pedagogico
- Visualizacion de desempeno de estudiantes

### Caracteristicas tecnicas
- Arquitectura MVVM con separacion de responsabilidades
- Persistencia local con Room Database
- Validaciones centralizadas (clase Validador)
- Animaciones funcionales (fade_in, shake, scale_up, slide)
- Recursos nativos: camara (CameraX), vibracion, notificaciones
- Formularios con validacion visual e iconos
- Navegacion coherente entre vistas

## Como ejecutar
1. Abrir el proyecto en Android Studio
2. Sincronizar Gradle
3. Ejecutar en emulador o dispositivo fisico (Android 7.0+)

## Estructura del proyecto
- `data/` - Entidades, DAOs, base de datos, repositorios
- `ui/` - Activities y layouts organizados por perfil
- `viewmodel/` - Logica de presentacion
- `util/` - Validadores y utilidades
