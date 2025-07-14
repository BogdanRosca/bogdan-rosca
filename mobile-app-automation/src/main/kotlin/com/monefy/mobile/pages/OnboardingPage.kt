package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class OnboardingPage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val continueButtonId = "com.monefy.app.lite:id/buttonContinue"
    private val firstScreenButtonText = "GET STARTED"
    private val secondScreenButtonText = "AMAZING"
    private val thirdScreenButtonText = "READY"
    
    override fun waitForPageToLoad() {
        selenium.waitForTextById(continueButtonId, firstScreenButtonText)
    }
    
    fun completeFirstScreen() {
        selenium.clickById(continueButtonId)
    }
    
    fun completeSecondScreen() {
        selenium.waitForTextById(continueButtonId, secondScreenButtonText)
        selenium.clickById(continueButtonId)
    }
    
    fun completeThirdScreen() {
        selenium.waitForTextById(continueButtonId, thirdScreenButtonText)
        selenium.clickById(continueButtonId)
    }
    
    fun completeOnboarding() {
        completeFirstScreen()
        completeSecondScreen()
        completeThirdScreen()
    }

} 