package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.StringUtils

class HomePage(driver: RemoteWebDriver) : BasePage(driver) {
    
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
        selenium.waitForElementVisibilityById(topbBarId)
    }

    fun checkBallance(balance: Double) {
        var balanceString = StringUtils.formatBalance(balance)
        selenium.waitForTextById(balanceTextId, balanceString)
    }

    fun tapAddIncome() {
        selenium.clickById(addIncomeButtonId)
    }

    fun tapAddExpense() {
        selenium.clickById(addExpenseButtonId)
    }

    fun tapFoodCategoryButton() {
        val elementXpath = "//*[@resource-id='$piechartId']//$foodButtonClass[1]"
        selenium.clickByXpath(elementXpath)
    }

    fun openBalancePage() {
        selenium.clickById(balanceTextId)
    }

    fun clearToastMessage() {
        tapFoodCategoryButton()
        selenium.goBack()
    }

    fun closeSettingsScreen() {
        selenium.goBack()   
    }
} 