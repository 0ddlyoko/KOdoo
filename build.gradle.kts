plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("maven-publish")
}

group = "me.oddlyoko.kodoo"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.xmlrpc/xmlrpc-client
    implementation("org.apache.xmlrpc:xmlrpc-client:3.1.3")


    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.oddlyoko.kodoo"
            artifactId = "kodoo"
            version = "1.0"

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.named("shadowJar", com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar::class) {
    mergeServiceFiles()
}
