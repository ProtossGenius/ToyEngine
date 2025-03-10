/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.3.3/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")
    // https://mvnrepository.com/artifact/com.alibaba/fastjson
    implementation("com.alibaba:fastjson:2.0.46")

    implementation(project(":door"))
    implementation(project(":engine"))

    implementation(fileTree(mapOf("dir" to "../libs", "include" to listOf("*.jar"))))
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit4 test framework
            useJUnit("4.13.2")
        }
    }
}

application {
    executableDir = "../resources/"
    // Define the main class for the application.
    mainClass.set("com.suremoon.suremoon.forms.PlayerSetter")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// Set the main class (important!)
tasks.named<Jar>("jar") {
    manifest {
        attributes(mapOf("Main-Class" to "com.suremoon.suremoon.forms.PlayerSetter")) // Replace with your main class
    }
}