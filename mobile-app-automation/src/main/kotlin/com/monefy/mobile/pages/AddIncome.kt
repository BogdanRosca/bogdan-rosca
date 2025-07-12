package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class AddIncomePage(driver: RemoteWebDriver) : BasePage(driver) {

    private val cashOrCardButtonId = "com.monefy.app.lite:id/icon"
    private val chooseChategoryButtonId = "com.monefy.app.lite:id/keyboard_action_button"
    private val keyBoardContainerId = "com.monefy.app.lite:id/linearLayoutKeyboard"
    private val keyBoardNumberButtonId = "com.monefy.app.lite:id/buttonKeyboard"
    private val keyBoardDotButtonId = "com.monefy.app.lite:id/buttonKeyboardDot"
    private val noteButtonId = "com.monefy.app.lite:id/textViewNote"
    private val salaryButtonId = "com.monefy.app.lite:id/buttonKeyboard1"
    private val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
    
    override fun waitForPageToLoad() {
        selenium.assertVisibleById(keyBoardContainerId, "Add income screen is not visible")
    }

    fun fillIncomeDetails(amount: Double, paymentType: String, incomeType: String, note: String = "") {
        enterAmountByKeyboard(amount)
        choosePaymentType(paymentType)
        addNote(note)
        selenium.waitAndClickById(chooseChategoryButtonId)
        chooseIncomeType(incomeType)
    }


    fun chooseIncomeType(incomeType: String) {
        val validIncomeTypes = listOf("Deposits", "Salary", "Savings")
        if (!validIncomeTypes.contains(incomeType)) {
            throw RuntimeException("Invalid incomeType: '$incomeType'. Valid types are: ${validIncomeTypes.joinToString(", ")}")
        }
        selenium.waitAndClickByTextContains(incomeType)
    }

    fun choosePaymentType(paymentType: String) {
        if (paymentType == "card") {
            selenium.waitAndClickById(cashOrCardButtonId)
            selenium.waitAndClickByTextContains("Payment card")
        } else if (paymentType != "cash") {
            throw RuntimeException("Invalid incomeType selected")
        }
    }

    fun addNote(note: String) {
        if (note != "") {
            selenium.waitAndTypeById(noteButtonId, note)
        }
    }

    fun enterAmountByKeyboard(amount: Double) {
        if (amount > 999999999.99) { 
            throw RuntimeException("Amount is too large")
        }
        println(amount.toString())
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

}

