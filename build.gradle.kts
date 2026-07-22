plugins {
    id("java")
}

group = "de.tomse.misc"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.46")
    compileOnly("org.projectlombok:lombok:1.18.46")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    test {
        useJUnitPlatform()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}