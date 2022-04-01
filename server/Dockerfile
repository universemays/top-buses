FROM maven:3.8-jdk-11 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn clean package -D maven.test.skip -D maven.main.skip -D spring-boot.repackage.skip && rm -r target/

COPY src ./src
RUN mvn clean package -D maven.test.skip

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11-jdk as runner
WORKDIR /app

ARG DEPENDENCY=/app/target/dependency

RUN addgroup --system --gid 1001 springio
RUN adduser --system --uid 1001 svc

USER svc

COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app/classes
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF

CMD java -cp .:classes:lib/* com.example.topbuses.TopBusesApplication
