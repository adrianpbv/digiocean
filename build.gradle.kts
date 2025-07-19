plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.cloud.tools.jib") version "3.4.5"
}

group = "com.digiocean.examp"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

jib {
    dockerClient {
        executable = "/usr/local/bin/docker"
    }
    to {
        image = "adrianjpbv/${project.name}:s4"
        auth {
            // TODO set environment variables
            username = System.getenv("DOCKER_USERNAME") ?: project.findProperty("docker.username") as String?
            password = System.getenv("DOCKER_PASSWORD") ?: project.findProperty("docker.password") as String?
        }
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
