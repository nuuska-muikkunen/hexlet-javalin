
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    application
    id ("checkstyle")
}

application {
    mainClass.set("org.example.hexlet.HelloWorld")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.datafaker:datafaker:2.0.2")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("gg.jte:jte:3.0.1")
    implementation("io.javalin:javalin-rendering:5.6.2")
    implementation("io.javalin:javalin:5.6.2")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("gg.jte:jte:3.0.1")
    implementation("com.h2database:h2:2.2.220")
    implementation("com.zaxxer:HikariCP:5.0.1")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.commons:commons-lang3:3.13.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}
