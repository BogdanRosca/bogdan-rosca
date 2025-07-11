package com.monefy.mobile.tests

import com.monefy.mobile.base.BaseTest
import org.testng.Assert
import org.testng.annotations.Test

class NewUserOnboarding : BaseTest() {

    @Test
    fun testDeviceConnection() {
        val deviceName = driver.capabilities.getCapability("deviceName")
        Assert.assertEquals(deviceName, "emulator-5554", "Should connect to correct emulator")
        
        try { 
            // First onboarding screen
            selenium.waitForTextById("com.monefy.app.lite:id/buttonContinue", "GET STARTED", "First screen not displayed")
            selenium.waitAndClickById("com.monefy.app.lite:id/buttonContinue")
            
            // Second onboarding screen
            selenium.waitForTextById("com.monefy.app.lite:id/buttonContinue", "AMAZING", "Second screen not displayed")
            selenium.waitAndClickById("com.monefy.app.lite:id/buttonContinue")
            
            // Third onboarding screen
            selenium.waitForTextById("com.monefy.app.lite:id/buttonContinue", "READY", "Third screen not displayed")
            selenium.waitAndClickById("com.monefy.app.lite:id/buttonContinue")
            
            // Upgrade screen
            selenium.waitForTextById("com.monefy.app.lite:id/textViewTitle", "Claim your one-time welcome offer", "Upgrade screen not displayed")
            
            // Close upgrade screen
            selenium.waitAndClickById("com.monefy.app.lite:id/buttonClose")
    
            // Verify home screen is visible
            selenium.assertVisibleById("com.monefy.app.lite:id/toolbar_layout", "Home screen is not visible")
            
            // Verify balance
            selenium.waitForTextById("com.monefy.app.lite:id/balance_amount", "Balance $0.00", "Balance should be 0.00$")
            
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw e
        }
    }
}