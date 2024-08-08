plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val versionStr = "1.3.1"

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
    maven {
        url = uri("https://repo.viaversion.com")
    }
}

val javaTarget = 21
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
    compileOnly("com.viaversion:viaversion-api:5.0.1")

    // Common Dependencies
    implementation("net.kyori:adventure-api:4.15.0")
    implementation("net.kyori:adventure-nbt:4.15.0")
    implementation("net.kyori:adventure-text-minimessage:4.15.0")

    compileOnly("com.google.guava:guava:32.0.0-android")
    compileOnly("com.google.code.gson:gson:2.8.9")
}

tasks {
    processResources {
        outputs.upToDateWhen { false }
        eachFile { expand("version" to project.version) }
    }

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
