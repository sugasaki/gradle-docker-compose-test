import com.avast.gradle.dockercompose.ComposeExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"

    // docker-compose
    id("com.avast.gradle.docker-compose") version "0.14.0"
}

/**
 * dockerCompose setting
 */
configure<ComposeExtension> {
    buildAdditionalArgs = listOf("--force-rm")
    forceRecreate = true //  // pass '--force-recreate' and '--renew-anon-volumes' when calling 'docker-compose up' when set to 'true`
    stopContainers = true // doesn't call `docker-compose down` if set to false - see below the paragraph about reconnecting; default is true
    removeContainers = true // default is true
    useComposeFiles = listOf("docker-compose.yml") // ファイル置き場を相対パスで指定
}


group = "me.atsu"
version = "1.0-SNAPSHOT"

val fuelVersion = "2.3.1"

repositories {
    mavenCentral()
}

dependencies {
    // fuel core
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.20")
}

tasks.test {
    dependsOn("composeUp") // add
    useJUnitPlatform()
    this.finalizedBy("composeDown") // add
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
