plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val versionStr = "1.1.0"

group = "net.edenor.minimap"
version = versionStr

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

val javaTarget = 17
java {
    sourceCompatibility = JavaVersion.toVersion(javaTarget)
    targetCompatibility = JavaVersion.toVersion(javaTarget)
    if (JavaVersion.current() < JavaVersion.toVersion(javaTarget)) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
    }
}

dependencies {
    // Main Dependencies
    compileOnly("dev.folia:folia-api:1.20.1-R0.1-SNAPSHOT")

    // Common Dependencies
    implementation("org.spongepowered:configurate-core:4.1.2")
    implementation("org.spongepowered:configurate-yaml:4.1.2")
    implementation("net.kyori:adventure-api:4.15.0")
    implementation("net.kyori:adventure-text-minimessage:4.15.0")

    compileOnly("com.google.guava:guava:32.0.0-android")
    compileOnly("com.google.code.gson:gson:2.8.9")

    implementation("dev.dewy:nbt:1.5.1")
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        exclude("com/google/gson/**")
        exclude("org/apache/commons/**")
        exclude("org/yaml/snakeyaml/**")
        archiveBaseName.set("${rootProject.name}-folia")
        archiveClassifier.set("")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
    }
}
