# ─── Stage 1: Build ───────────────────────────────────────
  FROM gradle:8.7-jdk17 AS builder
  WORKDIR /app
  COPY . .
  RUN gradle bootJar --no-daemon

  # ─── Stage 2: Run ─────────────────────────────────────────
  FROM eclipse-temurin:17-jre
  WORKDIR /app
  COPY --from=builder /app/build/libs/*.jar app.jar
  ENTRYPOINT ["java", "-jar", "app.jar"]
