package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class AddIncomePage(driver: RemoteWebDriver) : BasePage(driver) {

    private val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    private val keyBoardContainerId = "com.monefy.app.lite:id/linearLayoutKeyboard"
    private val keyBoardNumberButtonId = "com.monefy.app.lite:id/buttonKeyboard"
    private val keyBoardDotButtonId = "com.monefy.app.lite:id/buttonKeyboardDot"
    private val salaryButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
    
    override fun waitForPageToLoad() {
        selenium.assertVisibleById(keyBoardContainerId, "Add income screen is not visible")
    }

    fun fillIncomeDetails(amount: Double, ) {
        enterAmountByKeyboard(amount.toString())
        selenium.waitAndClickById(chooseChategoryButtonId)
        selenium.waitAndClickByTextContains("Salary")
    }

    fun enterAmountByKeyboard(amount: String) {
        for (char in amount) {
            when (char) {
                '.' -> selenium.waitAndClickById(keyBoardDotButtonId)
                else -> {
                    if (char.isDigit()) {
                        val numberToPressId = keyBoardNumberButtonId + char
                        selenium.waitAndClickById(numberToPressId)
                    }
                }
            }
        }
    }

} 