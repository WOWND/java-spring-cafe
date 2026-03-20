# 빌드 단계
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# 실행 단계
FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]