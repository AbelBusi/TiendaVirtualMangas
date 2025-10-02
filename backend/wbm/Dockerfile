# Imagen base de Java
FROM openjdk:19-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR
COPY target/wbm-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8089

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
