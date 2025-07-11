package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class HomePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val addIncomeButtonId = "com.monefy.app.lite:id/income_button"
    private val balanceTextId = "com.monefy.app.lite:id/balance_amount"
    private val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    private val keyBoardZeroButtonId = "com.monefy.app.lite:id/buttonKeyboard0"
    private val keyBoardOneButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val keyBoardTwoButtonId = "com.monefy.app.lite:id/buttonKeyboard2"
    private val keyBoardThreeButtonId = "com.monefy.app.lite:id/buttonKeyboard3"
    private val keyBoardFourButtonId = "com.monefy.app.lite:id/buttonKeyboard4"
    private val keyBoardFiveButtonId = "com.monefy.app.lite:id/buttonKeyboard5"
    private val keyBoardSixButtonId = "com.monefy.app.lite:id/buttonKeyboard6"
    private val keyBoardSevenButtonId = "com.monefy.app.lite:id/buttonKeyboard7"
    private val keyBoardEightButtonId = "com.monefy.app.lite:id/buttonKeyboard8"
    private val keyBoardNineButtonId = "com.monefy.app.lite:id/buttonKeyboard9"
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
        selenium.waitAndClickById(addIncomeButtonId)
        enterAmountByKeyboard(amount.toString())
        selenium.waitAndClickById(chooseChategoryButtonId)
        selenium.waitAndClickByTextContains("Salary")
    }

    fun enterAmountByKeyboard(amount: String) {
        for (digit in amount) {
            val buttonId = "com.monefy.app.lite:id/buttonKeyboard$digit"
            selenium.waitAndClickById(buttonId)
        }
    }

} 