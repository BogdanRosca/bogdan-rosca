package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test
import com.monefy.mobile.utils.Transaction

class Transactions : BaseTest() {
    @Test
    fun testAddSalary() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val salary = Transaction(6543.21, "card", "Salary", "Salary july 2025")

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(salary)

        homePage.waitForPageToLoad()
        homePage.checkBallance(salary.amount)
    }


    @Test
    fun testAddDeposit() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val deposit = Transaction(320.00, "cash", "Deposits", "Sold bike")

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(deposit)

        homePage.waitForPageToLoad()
        homePage.checkBallance(deposit.amount)
    }

    @Test
    fun testAddSavings() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val savings = Transaction(1020.00, "cash", "Savings", "Savings july 2025")

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(savings)

        homePage.waitForPageToLoad()
        homePage.checkBallance(savings.amount)
    }

    @Test
    fun testAddExpenseByMainButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val rent = Transaction(1020.00, "card", "Bills", "Rent july 2025")

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(rent) 

        homePage.waitForPageToLoad()
        homePage.checkBallance(-rent.amount)
    }

    @Test
    fun testAddExpenseByChategoryButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val food = Transaction(48.25, "cash", "Food", "Monday groceries")

        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()

        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()

        homePage.waitForPageToLoad()
        homePage.checkBallance(0.00)
        homePage.tapFoodCategoryButton()

        addExpensePage.waitForPageToLoad()
        addExpensePage.checkSelectedCategory("Food")
        addExpensePage.fillExpenseDetails(food)

        homePage.waitForPageToLoad()
        homePage.checkBallance(-food.amount)
    }

}