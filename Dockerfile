FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.3

WORKDIR /app

COPY /app .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin
