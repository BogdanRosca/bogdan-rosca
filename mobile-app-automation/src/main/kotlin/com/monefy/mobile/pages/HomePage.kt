package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.StringUtils

class HomePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val stringUtils = StringUtils()
    private val addExpenseButtonId = "com.monefy.app.lite:id/expense_button_title"
    private val addIncomeButtonId = "com.monefy.app.lite:id/income_button"
    private val balanceTextId = "com.monefy.app.lite:id/balance_amount"
    private val foodButtonClass = "android.widget.ImageView"
    private val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    private val keyBoardNumberButtonId = "com.monefy.app.lite:id/buttonKeyboard"
    private val keyBoardDotButtonId = "com.monefy.app.lite:id/buttonKeyboardDot"
    private val piechartId = "com.monefy.app.lite:id/piegraph"
    private val salaryButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
    
    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(topbBarId, "Home screen is not visible", 10000)
    }

    fun checkBallance(balance: Double) {
        selenium.waitForElementVisibilityById(balanceTextId, "Balance is not visible", 10000)
        var balanceString = stringUtils.formatBalance(balance)
        selenium.waitForTextById(balanceTextId, balanceString, "Balance value is wrong. Expected $balance, but got $balanceString")
    }

    fun tapAddIncome() {
        selenium.waitAndClickById(addIncomeButtonId)
    }

    fun tapAddExpense() {
        selenium.waitAndClickById(addExpenseButtonId)
    }

    fun tapFoodCategoryButton() {
        val elementXpath = "//*[@resource-id='$piechartId']//$foodButtonClass[1]"
        selenium.waitAndClickByXpath(elementXpath)
    }

    fun openBalancePage() {
        selenium.waitAndClickById(balanceTextId)
    }

    fun clearToastMessage() {
        tapFoodCategoryButton()
        selenium.goBack()
    }

    fun closeSettingsScreen() {
        selenium.goBack()   
    }
} 