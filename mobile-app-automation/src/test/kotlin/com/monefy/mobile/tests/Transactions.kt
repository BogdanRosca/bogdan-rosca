package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test

class Transactions : BaseTest() {
    @Test
    fun testAddIncome() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance("$0.00")
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(4321.33)

        homePage.checkBallance("$4,321.33")
    }
}