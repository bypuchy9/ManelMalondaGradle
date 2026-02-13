import org.gradle.api.tasks.Exec

plugins {
    id("java")
    id("application")
}

group = "com.manelmalonda.tema4gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // IA
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.manelmalonda.tema4gradle.Main")
}


val isWindows = System.getProperty("os.name").lowercase().contains("win")

tasks.register<Exec>("ollamaVersion") {
    group = "ollama"
    description = "Comprueba la versión de Ollama"

    if (isWindows) {
        commandLine("powershell", "-c", "ollama --version")
    } else {
        commandLine("bash", "-c", "ollama --version")
    }
}

tasks.register<Exec>("ollamaPs") {
    group = "ollama"
    description = "Muestra modelos en memoria"

    if (isWindows) {
        commandLine("powershell", "-c", "ollama ps")
    } else {
        commandLine("bash", "-c", "ollama ps")
    }
}

tasks.register("llmInfo") {
    group = "ollama"
    description = "Ejecuta todo el flujo"

    dependsOn("ollamaVersion", "ollamaPs")

    val tPs = tasks.findByName("ollamaPs")
    tPs?.mustRunAfter("ollamaVersion")

    doLast {
        println(" Demo finalizada con EXITO")
    }
}