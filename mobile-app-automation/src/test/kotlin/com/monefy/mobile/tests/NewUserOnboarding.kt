package com.monefy.mobile.tests

import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test

class NewUserOnboarding : BaseTest() {
    
    @Test
    fun testNewUserOnboarding() { 
            val homePage = HomePage(driver)
            val onboardingPage = OnboardingPage(driver)
            val upgradePage = UpgradePage(driver)

            onboardingPage.waitForPageToLoad()
            onboardingPage.completeFirstScreen()
            onboardingPage.completeSecondScreen()
            onboardingPage.completeThirdScreen()
            
            upgradePage.waitForPageToLoad()
            upgradePage.closeUpgradeScreen()

            homePage.waitForPageToLoad()
    }

}