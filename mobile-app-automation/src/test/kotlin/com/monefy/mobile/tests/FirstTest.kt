package com.monefy.mobile.tests

import com.monefy.mobile.config.AppiumConfig
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.Duration

class FirstTest {
    
    private lateinit var driver: RemoteWebDriver
    
    @BeforeMethod
    fun setUp() {
        try {
            val capabilities = AppiumConfig.getAndroidCapabilities()
            driver = AndroidDriver(AppiumConfig.getAppiumServerUrl(), capabilities)
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
        } catch (e: Exception) {
            println("Failed to initialize Appium driver: ${e.message}")
            throw e
        }
    }

    @Test
    fun testDeviceConnection() {
        val deviceName = driver.capabilities.getCapability("deviceName")
        Assert.assertEquals(deviceName, "emulator-5554", "Should connect to correct emulator")
        
        try {
            Thread.sleep(3000)
            
            val closeButton = driver.findElement(By.id("com.monefy.app.lite:id/buttonClose"))
            closeButton.click()

            Thread.sleep(3000)

            val balanceElement = driver.findElement(By.id("com.monefy.app.lite:id/balance_amount"))
            val balanceText = balanceElement.text
            
            // Assert that the balance is 0.00$
            Assert.assertEquals(balanceText, "Balance $0.00", "Balance should be 0.00$")
            
        } catch (e: Exception) {
            println("Error tapping close button: ${e.message}")
            throw e
        }
    }
    
    @AfterMethod
    fun tearDown() {
        if (::driver.isInitialized) {
            try {
                (driver as AndroidDriver).terminateApp("com.monefy.app.lite")
            } catch (e: Exception) {
                println("Error closing app: ${e.message}")
            }
            
            try {
                driver.quit()
            } catch (e: Exception) {
                println("Error closing driver: ${e.message}")
            }
        }
    }
}