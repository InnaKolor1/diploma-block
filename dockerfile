FROM arm64v8/openjdk:17-slim

WORKDIR /app

RUN apt-get update && apt-get install -y maven

RUN mkdir -p /app/images
EXPOSE 8083

CMD ["java", "-jar", "target/*.jar"]