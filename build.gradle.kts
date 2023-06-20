val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
// Converts Kotlin objects into a serialized form like JSON and vice versa.
// Formats API output to and to consume user input that is structured in JSON.
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

/*
ktor-server-core
    adds Ktor's core components to our project
ktor-server-netty
    Adds the netty engine to our project
    Allows us to use server functionality without having to rely on an external application container
ktor-server-content-negotiation   &  ktor-serialization-kotlinx-json
    Provide a convenient mechanism for converting Kotlin objects into a serialized form like JSON, and vice versa
    We use this to format our APIs output and to consume user input that is structured in JSON
logback-classic
    Provides an implementation of SLF4J
    Allows us to see nicely formatted logs in a console
ktor-server-host & kotlin-test-junit
    Allows us to test parts of our Ktor App without having to use the whole HTTP stack in the process
    Used to define unit tests for our project
*/










