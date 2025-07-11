package com.monefy.mobile.base

import com.monefy.mobile.config.AppiumConfig
import com.monefy.mobile.utils.SeleniumUtils
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import java.time.Duration

abstract class BaseTest {
    
    protected lateinit var driver: RemoteWebDriver
    protected lateinit var selenium: SeleniumUtils
    
    @BeforeMethod
    fun setUp() {
        try {
            val capabilities = AppiumConfig.getAndroidCapabilities()
            driver = AndroidDriver(AppiumConfig.getAppiumServerUrl(), capabilities)
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
            selenium = SeleniumUtils(driver)
            println("Appium driver initialized successfully!")
        } catch (e: Exception) {
            println("Failed to initialize Appium driver: ${e.message}")
            throw e
        }
    }
    
    @AfterMethod
    fun tearDown() {
        if (::driver.isInitialized) {
            try {
                (driver as AndroidDriver).terminateApp("com.monefy.app.lite")
                println("App closed successfully!")
            } catch (e: Exception) {
                println("Error closing app: ${e.message}")
            }
            
            try {
                driver.quit()
                println("Driver closed successfully!")
            } catch (e: Exception) {
                println("Error closing driver: ${e.message}")
            }
        }
    }
} 