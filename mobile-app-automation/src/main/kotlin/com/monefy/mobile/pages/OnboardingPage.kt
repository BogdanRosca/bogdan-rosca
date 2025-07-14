package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class OnboardingPage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val continueButtonId = "com.monefy.app.lite:id/buttonContinue"
    private val firstScreenButtonText = "GET STARTED"
    private val secondScreenButtonText = "AMAZING"
    private val thirdScreenButtonText = "READY"
    
    override fun waitForPageToLoad() {
        selenium.waitForTextById(continueButtonId, firstScreenButtonText, "Onboarding page not loaded")
    }
    
    fun completeFirstScreen() {
        selenium.waitAndClickById(continueButtonId)
    }
    
    fun completeSecondScreen() {
        selenium.waitForTextById(continueButtonId, secondScreenButtonText, "Second onboarding screen not displayed")
        selenium.waitAndClickById(continueButtonId)
    }
    
    fun completeThirdScreen() {
        selenium.waitForTextById(continueButtonId, thirdScreenButtonText, "Third onboarding screen not displayed")
        selenium.waitAndClickById(continueButtonId)
    }
    
    fun completeOnboarding() {
        completeFirstScreen()
        completeSecondScreen()
        completeThirdScreen()
    }

} 