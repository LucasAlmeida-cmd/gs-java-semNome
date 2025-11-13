# --- Estágio 1: Build (Construção) ---
# Usando a sua imagem base (Alpine)
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

# Cria o usuário
RUN adduser -D userapp
USER userapp
WORKDIR /app

# --- OTIMIZAÇÃO DE CACHE ---
# 1. Copia SÓ o pom.xml primeiro
COPY --chown=userapp:userapp pom.xml .

# 2. Baixa SÓ as dependências
RUN mvn dependency:go-offline -DskipTests

# 3. Agora, copia o seu código-fonte
COPY --chown=userapp:userapp src ./src

# 4. Compila o projeto
RUN mvn clean package -DskipTests


# --- Estágio 2: Run (Execução) ---

# Esta imagem EXISTE e é perfeita para o seu build.
FROM eclipse-temurin:17-jre-alpine

# Cria o usuário na imagem de execução
RUN adduser --disabled-password --gecos "" userapp
USER userapp
WORKDIR /app

# Copia o .jar do estágio 'builder'
COPY --from=builder /app/target/gs-java-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
