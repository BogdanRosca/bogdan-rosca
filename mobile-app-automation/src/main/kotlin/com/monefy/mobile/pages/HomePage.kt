package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class HomePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val addIncomeButtonId = "com.monefy.app.lite:id/income_button"
    private val balanceTextId = "com.monefy.app.lite:id/balance_amount"
    private val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    private val keyBoardZeroButtonId = "com.monefy.app.lite:id/buttonKeyboard0"
    private val keyBoardOneButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val salaryButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
    
    override fun waitForPageToLoad() {
        selenium.assertVisibleById(topbBarId, "Home screen is not visible")
    }

    fun checkBallance(balance: String) {
        selenium.assertVisibleById(balanceTextId, "Balance is not visible")
        selenium.waitForTextById(balanceTextId, balance, "Balance is not $balance")
    }

    fun addIncome(amount: Int, ) {
        println("Adding income $amount")
        selenium.waitAndClickById(addIncomeButtonId)
        selenium.waitAndClickById(keyBoardOneButtonId)
        selenium.waitAndClickById(keyBoardZeroButtonId)
        selenium.waitAndClickById(keyBoardZeroButtonId)
        selenium.waitAndClickById(chooseChategoryButtonId)
        selenium.waitAndClickByTextContains("Salary")
    }

} 