# 🩺 API de Reservas de Citas Médicas

Sistema backend para la gestión de citas médicas entre pacientes y doctores. Diseñado para demostrar habilidades como desarrollador backend senior, incluyendo modelado de datos, validaciones de reglas de negocio, estructura limpia, y documentación de API.

---

## 🚀 Funcionalidades

### 👨‍⚕️ Doctores
- CRUD completo
- Campos: `id`, `name`, `specialty`

### 🧑 Pacientes
- CRUD completo
- Campos: `id`, `name`, `email`

### 📆 Citas Médicas
- Crear, listar y cancelar citas
- Campos: `id`, `doctorId`, `patientId`, `startTime`, `endTime`, `notes`

---

## 📋 Reglas de Negocio

- ⛔ **No se permite solapamiento de citas para el mismo doctor**
- ⛔ **Un paciente no puede tener dos citas al mismo tiempo**
- ✅ `startTime` debe ser anterior a `endTime`
- ✅ Ver disponibilidad de un doctor por día o rango de fechas
- 🧠 Validaciones personalizadas con excepciones claras

---

## 🧪 Tecnologías utilizadas

- **SpringBoot** - Framework backend (Java)
- **JPQL** - ORM para PostgreSQL
- **PostgreSQL** - Base de datos relacional
- **Swagger** - Documentación automática de la API
- **JUnit** - Testing unitario
- **Docker** - Entorno de desarrollo reproducible

---

📁 Estructura de Carpetas — Arquitectura Hexagonal
src/
├── application/                # Lógica de aplicación (Casos de uso)
│   ├── use-cases/
│   │   └── create-customer.use-case.ts
│   └── services/               # Servicios de orquestación si aplica
│       └── customer.service.ts
│
├── domain/                    # Modelo del dominio puro
│   ├── models/                # Entidades y objetos de valor
│   │   └── customer.model.ts
│   ├── repositories/          # Interfaces (puertos primarios)
│   │   └── customer.repository.interface.ts
│   └── exceptions/            # Errores de dominio
│       └── customer-not-found.exception.ts
│
├── infrastructure/            # Adaptadores secundarios (implementaciones técnicas)
│   ├── database/              # ORMs, entidades, migraciones
│   │   ├── entities/
│   │   │   └── customer.entity.ts
│   │   └── repositories/
│   │       └── customer.repository.impl.ts
│   ├── config/                # Configuración del entorno, .env, etc.
│   └── services/              # Adaptadores como APIs externas, correo, etc.
│
├── interface/                 # Adaptadores primarios (entradas)
│   ├── rest/                  # Controladores HTTP
│   │   ├── controllers/
│   │   │   └── customer.controller.ts
│   │   ├── dtos/
│   │   │   └── customer.dto.ts
│   │   └── mappers/
│   │       └── customer.mapper.ts
│
├── shared/                    # Utilidades, constantes, logging, etc.
│   ├── utils/
│   ├── constants/
│   └── middleware/
│
└── main.ts                    # Punto de entrada principal

---

## 🎯 Qué demuestra este proyecto

| Área                        | Habilidad |
|-----------------------------|-----------|
| Relaciones entre entidades  | Manejo de `ManyToOne`, `OneToMany` |
| Validaciones de negocio     | Manejo de solapamientos y rangos horarios |
| Arquitectura limpia         | Separación por módulos, uso de DTOs, servicios, controladores |
| Testing                     | Unit tests para la lógica de validación |
| Seguridad                   | Autenticación con JWT y control de roles |
| DevOps                      | Docker, Docker Compose, scripts de CI/CD |

---

## 🧪 Scripts

```bash
# instalar dependencias
maven install

# correr la app
java --jar

# correr tests
java --jar test

# ver docs Swagger
GET /api (una vez corriendo el servidor)
```

---

## 📦 Docker

```bash
podman build -t doctorya-app:latest .
podman compose up
```
---

# MINIKUBE
Descargar Minikube para windows
Hacer la instalacion del .exe
Si no se tiene docker-desktop iniciar minikube con hyperv
```bash
minikube delete
minikube start
minikube addons enable metrics-server
kubectl get nodes
kubectl get pods
```
Posterior es necesario crear la imagen
Exportar la imagen y cargar la imagen a minikube y por ultimo aplicar los manifiestos
```bash
podman save -o doctorya-app.tar doctorya-app:latest
minikube image load doctorya-app.tar
kubectl apply -f k8s/
kubectl logs "pod"
kubectl delete pod "pod"
minikube service
```

## Buenas practicas del proyecto
1. Separación clara por dominio (modularización por contexto): Tener Appointment, Auth, Patient, Physician, etc. como módulos separados es excelente para escalar y mantener el proyecto.

2. Subdivisión interna coherente: Controller, Dto, Entity, Repository, Service separados dentro de cada módulo permite mantener responsabilidades claras y evitar archivos gigantes.

3. Centralización de utilidades: Una carpeta util separada es útil para helpers, funciones comunes o validadores personalizados.

4. Carpeta exceptions: Buena práctica para manejar y centralizar errores personalizados (como NotFoundException, BadRequestException custom, etc.).
