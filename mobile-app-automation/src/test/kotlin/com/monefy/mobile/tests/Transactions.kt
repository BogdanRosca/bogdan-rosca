package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test

class Transactions : BaseTest() {
    @Test
    fun testAddSalary() {
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
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(salaryAmount, "card", "Salary", "Salary july 2025")

        homePage.waitForPageToLoad()
        homePage.checkBallance(salaryAmount)
    }


    @Test
    fun testAddDeposit() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val depositAmount = 320.00

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(depositAmount, "cash", "Deposits", "Sold bike")

        homePage.waitForPageToLoad()
        homePage.checkBallance(depositAmount)
    }

    @Test
    fun testAddSavings() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val savingsAmount = 1020.00

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(savingsAmount, "cash", "Savings")

        homePage.waitForPageToLoad()
        homePage.checkBallance(savingsAmount)
    }

    @Test
    fun testAddExpenseByMainButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val expenseAmount = 1020.00

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(expenseAmount, "card", "Bills", "Rent july 2025")

        homePage.waitForPageToLoad()
        homePage.checkBallance(-expenseAmount)
    }

    @Test
    fun testAddExpenseByChategoryButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val expenseAmount = 43.75

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapFoodCategoryButton()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(expenseAmount, "cash", "Food", "Monday groceries")

        homePage.waitForPageToLoad()
        homePage.checkBallance(-expenseAmount)
    }

}