import com.jetbrains.plugin.structure.base.utils.contentBuilder.buildDirectory
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.changelog.Changelog

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.changelog") version "2.0.0"
}

group = "org.purple"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


intellij {
    version.set("2024.1.4")
    type.set("IC")
    plugins.set(listOf())
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
    runIde {
        jvmArgs = listOf("-Xmx2048m")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation ("org.jetbrains:annotations:23.0.0")
    implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.9.24")
}