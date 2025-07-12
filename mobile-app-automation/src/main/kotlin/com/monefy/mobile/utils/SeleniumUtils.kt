package com.monefy.mobile.utils

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class SeleniumUtils(private val driver: RemoteWebDriver) {
    /**
     * Wait for an element with id to be present and clickable or fail due to timeout
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
     * Wait for an element with specific text to be present and clickable, then click it or fail due to timeout
     */
    fun waitAndClickByTextContains(elementText: String, timeoutMs: Long = 5000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val element = driver.findElement(By.xpath("//*[contains(translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'${elementText.lowercase()}')]"))
                if (element.isDisplayed && element.isEnabled) {
                    element.click()
                    println("Clicked element containing text: '$elementText'")
                    return
                }
            } catch (e: Exception) {
                // Element not found or not clickable yet, continue waiting
            }
            Thread.sleep(100)
        }
        throw RuntimeException("Element containing text '$elementText' not clickable after $timeoutMs ms")
    }
    
    /**
     * Wait for an element to be present by xpath and clickable or fail due to timeout
     */
    fun waitAndClickByXpath(elementXpath: String, timeoutMs: Long = 5000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val element = driver.findElement(By.xpath(elementXpath))
                if (element.isDisplayed && element.isEnabled) {
                    element.click()
                    println("Clicked element with Xpath: $elementXpath")
                    return
                }
            } catch (e: Exception) {
                // Element not found or not clickable yet, continue waiting
            }
            Thread.sleep(100)
        }
        throw RuntimeException("Element $elementXpath not clickable after $timeoutMs ms")
    }

    /**
     * Wait for an element to be present and clickable or fail due to timeout
     */
    fun waitAndReturnElementsById(elementId: String, timeoutMs: Long = 5000): List<WebElement> {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val elements = driver.findElements(By.id(elementId))
                if (elements.size > 0) {
                    println("Found ${elements.size} elements with ID: $elementId")
                    return elements
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
    fun waitForTextById(elementId: String, expectedText: String, message: String = "", timeoutMs: Long = 5000) {
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
     * Wait for an element to be visible by ID or fail due to timeout
     */
    fun waitForElementVisibilityById(elementId: String, message: String = "", timeoutMs: Long = 5000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val isVisible = isVisibleById(elementId)
                if (isVisible) {
                    println("Element $elementId is visible")
                    return
                }
            } catch (e: Exception) {
                // Element not found yet, continue waiting
            }
            Thread.sleep(100)
        }
        throw RuntimeException("$message - Element $elementId was not visible after $timeoutMs ms.")
    }
    
   
    /**
     * Wait for an element to be present and typeable, then type text into it
     */
    fun waitAndTypeById(elementId: String, text: String, timeoutMs: Long = 5000) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                val element = driver.findElement(By.id(elementId))
                if (element.isDisplayed && element.isEnabled) {
                    element.clear()
                    element.sendKeys(text)
                    println("Typed text '$text' into element with ID: $elementId")
                    return
                }
            } catch (e: Exception) {
                // Element not found or not typeable yet, continue waiting
            }
            Thread.sleep(100)
        }
        throw RuntimeException("Element $elementId not typeable after $timeoutMs ms")
    }
    
    /**
     * Navigate back to the previous screen
     */
    fun goBack() {
        driver.navigate().back()
        println("Navigated back to previous screen")
    }
} 