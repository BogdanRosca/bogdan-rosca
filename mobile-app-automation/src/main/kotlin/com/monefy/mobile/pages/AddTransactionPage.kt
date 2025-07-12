package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

abstract class AddTransactionPage(driver: RemoteWebDriver) : BasePage(driver) {

    protected val cashOrCardButtonId = "com.monefy.app.lite:id/icon"
    protected val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    protected val keyBoardContainerId = "com.monefy.app.lite:id/linearLayoutKeyboard"
    protected val keyBoardNumberButtonId = "com.monefy.app.lite:id/buttonKeyboard"
    protected val keyBoardDotButtonId = "com.monefy.app.lite:id/buttonKeyboardDot"
    protected val noteButtonId = "com.monefy.app.lite:id/textViewNote"
    protected val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
   

    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(topbBarId, "Transaction screen is not visible")
    }

    protected fun enterAmountByKeyboard(amount: Double) {
        if (amount > 999999999.99) { 
            throw RuntimeException("Amount is too large")
        }
        println("Entering amount: $amount")
        for (char in String.format("%.2f", amount)) {
            if (char == '.') {
                selenium.waitAndClickById(keyBoardDotButtonId)
            }
            else {
                if (char.isDigit()) {
                    val numberToPressId = keyBoardNumberButtonId + char
                    selenium.waitAndClickById(numberToPressId)
                }
            }
        }
    }

    protected fun choosePaymentType(paymentType: String) {
        if (paymentType == "card") {
            selenium.waitAndClickById(cashOrCardButtonId)
            selenium.waitAndClickByTextContains("Payment card")
        } else if (paymentType != "cash") {
            throw RuntimeException("Invalid incomeType selected")
        }
    }

    protected fun addNote(note: String) {
        if (note.isNotEmpty()) {
            selenium.waitAndTypeById(noteButtonId, note)
        }
    }

    protected fun openCategorySelection() {
        selenium.waitAndClickById(chooseChategoryButtonId)
    }
}