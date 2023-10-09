plugins {
    java
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_20
    targetCompatibility = JavaVersion.VERSION_20
}

defaultTasks = mutableListOf("run")

group = "io.github.cake-lier"
version = "0.1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://raw.github.com/jacamo-lang/mvn-repo/master")
    }
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases/")  // For current version of Gradle tooling API
    }
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases-local/") // For older versions of Gradle tooling API
    }
}

dependencies {
    implementation("org.jacamo:jacamo:1.2.2")
}

sourceSets {
    main {
        resources {
            srcDir("src/main/environment")
            srcDir("src/main/agents")
            srcDir("src/main/organizations")
            srcDir("src/main/resources")
        }
    }
}

tasks {
    getByName<JavaExec>("run") {
        description = "Runs the JaCaMo application"
        dependsOn("classes")
        doFirst {
            mkdir("log")
        }
        mainClass = "jacamo.infra.JaCaMoLauncher"
        args = listOf("config.jcm")
        classpath = sourceSets.main.get().runtimeClasspath
    }
}
