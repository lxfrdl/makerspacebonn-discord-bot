plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "de.makerspacebonn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.discord4j:discord4j-core:3.2.7")
    implementation("io.projectreactor:reactor-core:3.4.12")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    implementation("org.slf4j:slf4j-simple:2.0.0")
}

application {
    mainClass.set("de.makerspacebonn.discordbot.DiscordBotKt")
}

tasks.test {
    useJUnitPlatform()
}