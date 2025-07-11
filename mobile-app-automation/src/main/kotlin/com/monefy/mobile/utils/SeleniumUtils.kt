package com.monefy.mobile.utils

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class SeleniumUtils(private val driver: RemoteWebDriver) {
    /**
     * Wait for an element to be present and clickable
     */
    fun waitAndClickById(elementId: String, timeoutMs: Long = 5000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val element = driver.findElement(By.id(elementId))
                if (element.isDisplayed && element.isEnabled) {
                    element.click()
                    println("Clicked element with ID: $elementId")
                    return
                }
            } catch (e: Exception) {
                // Element not found or not clickable yet, continue waiting
            }
            Thread.sleep(100)
        }
        throw RuntimeException("Element $elementId not clickable after $timeoutMs ms")
    }
    
    /**
     * Wait for an element to have specific text, or fail due to timeout
     */
    fun waitForTextById(elementId: String, expectedText: String, message: String = "", timeoutMs: Long = 10000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val element = driver.findElement(By.id(elementId))
                val actualText = element.text
                if (actualText.contains(expectedText)) {
                    println("Element $elementId now contains text: '$expectedText'")
                    return
                }
            } catch (e: Exception) {
                // Element not found yet, continue waiting
            }
            Thread.sleep(100)
        }
        val finalText = try {
            driver.findElement(By.id(elementId)).text
        } catch (e: Exception) {
            "Element not found"
        }
        throw RuntimeException("$message - Element $elementId did not contain '$expectedText' after $timeoutMs ms. Final text: '$finalText'")
    }
    
    /**
     * Check if an element is visible by ID
     */
    fun isVisibleById(elementId: String): Boolean {
        val element = driver.findElement(By.id(elementId))
        val isVisible = element.isDisplayed
        println("Element $elementId is visible: $isVisible")
        return isVisible
    }
    
    /**
     * Assert that an element is visible by ID
     */
    fun assertVisibleById(elementId: String, message: String = "") {
        val isVisible = isVisibleById(elementId)
        if (!isVisible) {
            throw RuntimeException("$message - Element $elementId is not visible")
        }
    }
} 