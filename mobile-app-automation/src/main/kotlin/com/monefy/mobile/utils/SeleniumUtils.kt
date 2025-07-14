package com.monefy.mobile.utils

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.Duration

class SeleniumUtils(private val driver: RemoteWebDriver) {
    /**
     * Wait for an element with id to be present and clickable or fail due to timeout
     */
    fun clickById(elementId: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        val element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)))
        element.click()
        println("Clicked element with ID: $elementId")
    }
    
    /**
     * Wait for an element to be present by xpath and clickable or fail due to timeout
     */
    fun clickByXpath(elementXpath: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        val element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)))
        element.click()
        println("Clicked element with Xpath: $elementXpath")
    }

     /**
     * Wait for an element with specific text to be present and clickable, then click it or fail due to timeout
     */
    fun clickByTextContains(elementText: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        val xpath = "//*[contains(translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'${elementText.lowercase()}')]"
        val element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
        element.click()
        println("Clicked element containing text: '$elementText'")
    }
    
    /**
     * Wait for an element to be present and typeable, then type text into it
     */
    fun typeById(elementId: String, text: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        val element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)))
        element.clear()
        element.sendKeys(text)
        println("Typed text '$text' into element with ID: $elementId")
    }

    /**
     * Wait and return list of elements by ID or fail due to timeout
     */
    fun returnElementsById(elementId: String, timeoutMs: Long = 5000): List<WebElement> {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        val elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementId)))
        println("Found ${elements.size} elements with ID: $elementId")
        return elements
    }

    /**
     * Wait for an element to have specific text, or fail due to timeout
     */
    fun waitForTextById(elementId: String, expectedText: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(elementId), expectedText))
        println("Element $elementId now contains text: '$expectedText'")
    }

    /**
     * Wait for an element to be visible by ID or fail due to timeout
     */
    fun waitForElementVisibilityById(elementId: String, timeoutMs: Long = 5000) {
        val wait = WebDriverWait(driver, Duration.ofMillis(timeoutMs))
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)))
        println("Element $elementId is visible")
    }
    
    
    /**
     * Navigate back to the previous screen
     */
    fun goBack() {
        driver.navigate().back()
        println("Navigated back to previous screen")
    }
} 