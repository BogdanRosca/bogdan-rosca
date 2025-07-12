package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.pages.BalancePage
import com.monefy.mobile.base.BaseTest
import com.monefy.mobile.utils.DateUtils
import com.monefy.mobile.utils.Transaction
import com.monefy.mobile.pages.HomePage
import com.monefy.mobile.pages.OnboardingPage
import com.monefy.mobile.pages.UpgradePage
import org.testng.annotations.Test

class TransactionsHistory : BaseTest() {

    @Test
    fun testBallanceByAmount() {
        val addExpensePage = AddExpensePage(driver)
        val addIncomePage = AddIncomePage(driver)
        val balancePage = BalancePage(driver)
        val homePage = HomePage(driver)
        val onboardingPage = OnboardingPage(driver)
        val upgradePage = UpgradePage(driver)
        val salary = Transaction(6543.21, "card", "Salary", "Salary july 2025")
        val deposit = Transaction(321.42, "cash", "Deposits", "Sold bike")
        val bills = Transaction(1020.00, "card", "Bills", "Rent july 2025")
        val taxi = Transaction(48.25, "cash", "Taxi", "Taxi to airport")

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
        homePage.clearToastMessage()
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(deposit)

        homePage.waitForPageToLoad()
        homePage.checkBallance(salary.amount + deposit.amount)
        homePage.clearToastMessage()
        homePage.closeSettingsScreen()
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(bills)

        homePage.waitForPageToLoad()
        homePage.checkBallance(salary.amount + deposit.amount - bills.amount)
        homePage.clearToastMessage()
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(taxi)

        homePage.waitForPageToLoad()
        homePage.checkBallance(salary.amount + deposit.amount - bills.amount - taxi.amount)
        homePage.openBalancePage() 
        
        balancePage.waitForPageToLoad()
        balancePage.checkTransactionChategory(0, salary)
        balancePage.checkTransactionChategory(1, deposit)
        balancePage.checkTransactionChategory(2, bills)
        balancePage.checkTransactionChategory(3, taxi) 
    }
    
}