plugins {
    kotlin("jvm") version "1.9.10"
    id("java")
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
    
    // Selenium - for web element interactions
    implementation("org.seleniumhq.selenium:selenium-java:4.15.0")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
} 