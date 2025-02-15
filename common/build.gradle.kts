plugins {
    `java-library`
}

repositories {
    mavenCentral()
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
    compileOnly("com.google.guava:guava:33.4.0-jre")
    compileOnly("com.google.code.gson:gson:2.12.1")
    compileOnly("org.spongepowered:configurate-core:4.1.2")
    compileOnly("net.kyori:adventure-api:4.18.0")
    compileOnly("net.kyori:adventure-text-minimessage:4.18.0")
    compileOnly("net.kyori:adventure-nbt:4.18.0")
}
