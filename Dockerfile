# Etapa 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias primero (para cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construir el jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen runtime optimizada
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el jar generado
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto (ajusta según tu app)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]