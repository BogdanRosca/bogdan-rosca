package com.monefy.mobile.tests

import org.testng.annotations.Test
import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.pages.BalancePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.utils.DateUtils
import com.monefy.mobile.utils.Transaction
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Story
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Step

@Feature("Transaction History")
class TransactionsHistory : BaseTest() {

    @Test
    @Story("Transaction Balance and history")
    @Description("Verify that balance updates correctly and transaction history is accurate after multiple income and expense operations")
    @Severity(SeverityLevel.CRITICAL)
    fun testBallanceByAmount() {
        val addExpensePage = AddExpensePage(driver)
        val addIncomePage = AddIncomePage(driver)
        val balancePage = BalancePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val salary = Transaction(6543.33, "card", "Salary", "Salary july 2025")
        val deposit = Transaction(320.00, "cash", "Deposits", "Sold bike")
        val bills = Transaction(1020.00, "card", "Bills", "Rent july 2025")
        val taxi = Transaction(48.25, "cash", "Taxi", "Taxi to airport")

        completeOnboarding(onboardingPage)
        handleUpgradeScreen(upgradePage)
        verifyInitialBalance(homePage, 0.00)
        addIncome(homePage, addIncomePage, salary)
        verifyBalance(homePage, salary.amount)
        addIncome(homePage, addIncomePage, deposit)
        verifyBalance(homePage, salary.amount + deposit.amount, true)
        addExpense(homePage, addExpensePage, bills)
        verifyBalance(homePage, salary.amount + deposit.amount - bills.amount)
        addExpense(homePage, addExpensePage, taxi)
        verifyBalance(homePage, salary.amount + deposit.amount - bills.amount - taxi.amount)
        verifyTransactionsHistory(homePage, balancePage, salary, deposit, bills, taxi)
    }

    @Step("Complete onboarding flow")
    private fun completeOnboarding(onboardingPage: OnboardingPage) {
        onboardingPage.waitForPageToLoad()
        onboardingPage.completeOnboarding()
    }

    @Step("Handle upgrade screen and close it")
    private fun handleUpgradeScreen(upgradePage: UpgradePage) {
        upgradePage.waitForPageToLoad()
        upgradePage.closeUpgradeScreen()
    }

    @Step("Verify initial balance is {balance}")
    private fun verifyInitialBalance(homePage: HomePage, balance: Double) {
        homePage.waitForPageToLoad()
        homePage.checkBallance(balance)
    }

    @Step("Verify balance is {balance}")
    private fun verifyBalance(homePage: HomePage, balance: Double, closeSettingsScreen: Boolean = false) {
        homePage.waitForPageToLoad()
        homePage.checkBallance(balance)
        homePage.clearToastMessage()
        if (closeSettingsScreen) {
            homePage.closeSettingsScreen()
        }
    }

    @Step("Add income: {transaction.category}")
    private fun addIncome(homePage: HomePage, addIncomePage: AddIncomePage, transaction: Transaction) {
        homePage.tapAddIncome()
        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(transaction)
    }

    @Step("Add expense: {transaction.category}")
    private fun addExpense(homePage: HomePage, addExpensePage: AddExpensePage, transaction: Transaction) {
        homePage.tapAddExpense()
        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(transaction)
        homePage.waitForPageToLoad()
    }

    @Step("Verify transaction history for all transactions")
    private fun verifyTransactionsHistory(homePage: HomePage, balancePage: BalancePage, salary: Transaction, deposit: Transaction, bills: Transaction, taxi: Transaction) {
        homePage.openBalancePage()
        balancePage.waitForPageToLoad()
        balancePage.checkTransactionChategory(0, salary)
        balancePage.checkTransactionChategory(1, deposit)
        balancePage.checkTransactionChategory(2, bills)
        balancePage.checkTransactionChategory(3, taxi)
    }
}