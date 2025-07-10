package com.monefy.mobile.config

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class AppiumConfig {
    
    companion object {
        const val APPIUM_SERVER_URL = "http://localhost:4723"
        
        fun getAndroidCapabilities(): DesiredCapabilities {
            val capabilities = DesiredCapabilities()
            capabilities.setCapability("platformName", "Android")
            capabilities.setCapability("automationName", "UiAutomator2")
            capabilities.setCapability("deviceName", "emulator-5554")
            capabilities.setCapability("app", "src/main/kotlin/com/monefy/mobile/apk/com.monefy.app.lite_1.18.0.apk")
            capabilities.setCapability("appPackage", "com.monefy.app.lite")
            capabilities.setCapability("appActivity", "com.monefy.activities.main.MainActivity_")
            capabilities.setCapability("noReset", true)
            capabilities.setCapability("autoGrantPermissions", true)
            return capabilities
        }
        
        fun getAppiumServerUrl(): URL {
            return URL(APPIUM_SERVER_URL)
        }
    }
} 