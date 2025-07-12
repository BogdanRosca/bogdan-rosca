package com.monefy.mobile.tests

import com.monefy.mobile.pages.AddExpensePage
import com.monefy.mobile.pages.AddIncomePage
import com.monefy.mobile.pages.BalancePage
import com.monefy.mobile.base.BaseTest
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
        val salaryAmount = 6543.21
        val depositAmount = 321.42
        val rentAmout = 1020.00
        val taxiAmount = 48.25

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
        homePage.clearToastMessage()
        homePage.tapAddIncome()

        addIncomePage.waitForPageToLoad()
        addIncomePage.fillIncomeDetails(depositAmount, "cash", "Deposits", "Sold bike")

        homePage.waitForPageToLoad()
        homePage.checkBallance(salaryAmount + depositAmount)
        homePage.clearToastMessage()
        homePage.closeSettingsScreen()
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(rentAmout, "card", "Bills", "Rent july 2025")

        homePage.waitForPageToLoad()
        homePage.checkBallance(salaryAmount + depositAmount - rentAmout)
        homePage.clearToastMessage()
        homePage.tapAddExpense()

        addExpensePage.waitForPageToLoad()
        addExpensePage.fillExpenseDetails(taxiAmount, "cash", "Taxi", "Taxi to airport")

        homePage.waitForPageToLoad()
        homePage.checkBallance(salaryAmount + depositAmount - rentAmout - taxiAmount)
        homePage.openBalancePage() 
        
        balancePage.waitForPageToLoad()
        balancePage.checkChategory(0, "Salary", salaryAmount)
        balancePage.checkChategory(1, "Deposits", depositAmount)
        balancePage.checkChategory(2, "Bills", rentAmout)
        balancePage.checkChategory(3, "Taxi", taxiAmount)
    }
    
}