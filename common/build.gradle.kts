plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

val javaTarget = 21 // Sponge targets a minimum of Java 17
java {
    sourceCompatibility = JavaVersion.toVersion(javaTarget)
    targetCompatibility = JavaVersion.toVersion(javaTarget)
    /*if (JavaVersion.current() < JavaVersion.toVersion(javaTarget)) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
    }*/
}

dependencies {
    compileOnly("com.google.guava:guava:33.4.8-jre")
    compileOnly("com.google.code.gson:gson:2.13.1")
    implementation("org.spongepowered:configurate-core:4.2.0")
    implementation("net.kyori:adventure-api:4.22.0")
    implementation("net.kyori:adventure-text-minimessage:4.22.0")
    implementation("net.kyori:adventure-nbt:4.22.0")
}
