# --- Estágio 1: Build (Construção) ---
# Usando a sua imagem base
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

# Cria o usuário
RUN adduser -D userapp
USER userapp
WORKDIR /app

# --- OTIMIZAÇÃO DE CACHE ---
# 1. Copia SÓ o pom.xml primeiro
COPY --chown=userapp:userapp pom.xml .

# 2. Baixa SÓ as dependências
# (O Docker vai guardar isso em cache)
RUN mvn dependency:go-offline -DskipTests

# 3. Agora, copia o seu código-fonte
COPY --chown=userapp:userapp src ./src
# --- FIM DA OTIMIZAÇÃO ---

# 4. Compila o projeto (isso será rápido se só o 'src' mudou)
RUN mvn clean package -DskipTests


# --- Estágio 2: Run (Execução) ---
# OTIMIZAÇÃO DE TAMANHO: Usando JRE (Runtime) em vez de JDK (Development Kit)
FROM openjdk:17-jre-slim

# Cria o usuário na imagem de execução
RUN adduser --disabled-password --gecos "" userapp
USER userapp
WORKDIR /app

# Copia o .jar do estágio 'builder' (mudei 'from=0' para 'from=builder')
COPY --from=builder /app/target/gs-java-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Usando ENTRYPOINT (um pouco mais robusto que CMD)
ENTRYPOINT ["java", "-jar", "app.jar"]