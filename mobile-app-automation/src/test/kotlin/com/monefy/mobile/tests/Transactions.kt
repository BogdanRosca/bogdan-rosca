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
        val salaryAmount = 6543.21

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance("$0.00")
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(salaryAmount, "card", "Salary", "Salary june 2025")

        homePage.waitForPageToLoad()
        homePage.checkBallance("$6,543.21")
    }
}