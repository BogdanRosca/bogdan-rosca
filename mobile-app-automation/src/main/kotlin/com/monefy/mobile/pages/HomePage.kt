package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

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
        selenium.assertVisibleById(topbBarId, "Home screen is not visible")
    }

    fun checkBallance(balance: Double) {
        selenium.assertVisibleById(balanceTextId, "Balance is not visible")
        val balanceString = if (balance < 0) {
            String.format("-$%,.2f", -balance)
        } else {
            String.format("$%,.2f", balance)
        }
        selenium.waitForTextById(balanceTextId, balanceString, "Balance is not $balance")
    }

    fun tapAddIncome() {
        selenium.waitAndClickById(addIncomeButtonId)
    }

    fun tapAddExpense() {
        selenium.waitAndClickById(addExpenseButtonId)
    }

    fun tapFoodCategoryButton() {
        selenium.tapFirstElementOfClassInContainerById(piechartId, foodButtonClass)
    }

    fun clearToastMessage() {
        selenium.tapFirstElementOfClassInContainerById(piechartId, foodButtonClass)
        selenium.goBack()
    }

} 