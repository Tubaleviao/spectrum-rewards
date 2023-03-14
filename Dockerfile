# Build front-end and send it to Springboot's static folder

FROM node AS rewards-build
RUN mkdir build
RUN git clone https://github.com/Tubaleviao/spectrum-rewards.git build
WORKDIR build/src/main/resources/frontend
RUN rm -R ../static/* && npm install && npm run build
RUN mv build/* ../static/ && cd ../ && rm -R frontend

# Build application into the .jar file

FROM eclipse-temurin:17-jdk-jammy as spring-build
WORKDIR /build
COPY --from=rewards-build build/ .
RUN chmod a+x ./mvnw && ./mvnw dependency:go-offline
RUN ./mvnw clean install

# Prepare production app

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=spring-build /build/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar" ]
