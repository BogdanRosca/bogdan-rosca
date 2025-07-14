package com.monefy.mobile.tests

import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Story
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Step

@Feature("User Onboarding")
class NewUserOnboarding : BaseTest() {
    
    @Test
    @Story("New User Registration Flow")
    @Description("Verify that a new user can successfully complete the onboarding process")
    @Severity(SeverityLevel.CRITICAL)
    fun testNewUserOnboarding() { 
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)

        completeOnboardingFlow(onboardingPage)
        handleUpgradeScreen(upgradePage)
        verifyHomePageLoaded(homePage)
    }
    
    @Step("Complete onboarding flow through all screens")
    private fun completeOnboardingFlow(onboardingPage: OnboardingPage) {
        onboardingPage.waitForPageToLoad()
        onboardingPage.completeFirstScreen()
        onboardingPage.completeSecondScreen()
        onboardingPage.completeThirdScreen()
    }
    
    @Step("Handle upgrade screen and close it")
    private fun handleUpgradeScreen(upgradePage: UpgradePage) {
        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()
    }
    
    @Step("Verify home page is loaded successfully")
    private fun verifyHomePageLoaded(homePage: HomePage) {
        homePage.waitForPageToLoad()
    }

}