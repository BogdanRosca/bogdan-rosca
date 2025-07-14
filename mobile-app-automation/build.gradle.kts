plugins {
    kotlin("jvm") version "1.9.22"
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "com.monefy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    
    // Appium - for mobile automation
    implementation("io.appium:java-client:8.5.1")
    
    // TestNG - for test execution
    testImplementation("org.testng:testng:7.7.1")
    
    // Allure TestNG listener
    testImplementation("io.qameta.allure:allure-testng:2.24.0")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
        listeners.add("io.qameta.allure.testng.AllureTestNg")
    }
    
    // Configure Allure results directory
    systemProperty("allure.results.directory", "$buildDir/allure-results")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
} 