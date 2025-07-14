package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test
import com.monefy.mobile.utils.Transaction
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Story
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Step

@Feature("Transactions")
class Transactions : BaseTest() {
    @Test
    @Story("Income Transactions")
    @Description("Verify that salary income can be added successfully and balance updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    fun testAddSalary() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val salary = Transaction(6543.21, "card", "Salary", "Salary july 2025")

        completeOnboarding(onboardingPage, upgradePage)
        verifyHomePageLoaded(homePage)
        verifyBalance(homePage, 0.00)
        addIncome(homePage, addIncomePage, salary)
        verifyBalance(homePage, salary.amount)
    }


    @Test
    @Story("Income Transactions")
    @Description("Verify that deposit income can be added successfully and balance updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    fun testAddDeposit() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val deposit = Transaction(320.00, "cash", "Deposits", "Sold bike")

        completeOnboarding(onboardingPage, upgradePage)
        verifyHomePageLoaded(homePage)
        verifyBalance(homePage, 0.00)
        addIncome(homePage, addIncomePage, deposit)
        verifyBalance(homePage, deposit.amount)
    }

    @Test
    @Story("Income Transactions")
    @Description("Verify that savings income can be added successfully and balance updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    fun testAddSavings() {
        val addIncomePage = AddIncomePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val savings = Transaction(1020.00, "cash", "Savings", "Savings july 2025")

        completeOnboarding(onboardingPage, upgradePage)
        verifyHomePageLoaded(homePage)
        verifyBalance(homePage, 0.00)
        addIncome(homePage, addIncomePage, savings)
        verifyBalance(homePage, savings.amount)
    }

    @Test
    @Story("Expense Transactions")
    @Description("Verify that expense can be added via main button and balance updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    fun testAddExpenseByMainButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val rent = Transaction(1020.00, "card", "Bills", "Rent july 2025")

        completeOnboarding(onboardingPage, upgradePage)
        verifyHomePageLoaded(homePage)
        verifyBalance(homePage, 0.00)
        addExpense(homePage, addExpensePage, rent)
        verifyBalance(homePage, -rent.amount)
    }

    @Test
    @Story("Expense Transactions")
    @Description("Verify that expense can be added via category button and balance updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    fun testAddExpenseByChategoryButton() {
        val addExpensePage = AddExpensePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val food = Transaction(48.25, "cash", "Food", "Monday groceries")

        completeOnboarding(onboardingPage, upgradePage)
        verifyHomePageLoaded(homePage)
        verifyBalance(homePage, 0.00)
        addExpenseByCategory(homePage, addExpensePage, food)
        verifyBalance(homePage, -food.amount)
    }

    @Step("Complete onboarding flow")
    private fun completeOnboarding(onboardingPage: OnboardingPage, upgradePage: UpgradePage) {
        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()
        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()
    }

    @Step("Verify home page is loaded successfully")
    private fun verifyHomePageLoaded(homePage: HomePage) {
        homePage.waitForPageToLoad()
    }

    @Step("Verify balance is {balance}")
    private fun verifyBalance(homePage: HomePage, balance: Double) {
        homePage.checkBallance(balance)
    }

    @Step("Add income: {transaction.category}")
    private fun addIncome(homePage: HomePage, addIncomePage: AddIncomePage, transaction: Transaction) {
        homePage.tapAddIncome()
        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(transaction)
        homePage.waitForPageToLoad()
    }

    @Step("Add expense: {transaction.category}")
    private fun addExpense(homePage: HomePage, addExpensePage: AddExpensePage, transaction: Transaction) {
        homePage.tapAddExpense()
        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(transaction)
        homePage.waitForPageToLoad()
    }

    @Step("Add expense by category: {transaction.category}")
    private fun addExpenseByCategory(homePage: HomePage, addExpensePage: AddExpensePage, transaction: Transaction) {
        homePage.tapFoodCategoryButton()
        addExpensePage.waitForPageToLoad()
        addExpensePage.checkSelectedCategory("Food")
        addExpensePage.fillExpenseDetails(transaction)
        homePage.waitForPageToLoad()
    }

}