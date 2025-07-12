package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class AddIncomePage(driver: RemoteWebDriver) : AddTransactionPage(driver) {

    override fun waitForPageToLoad() { 
        super.waitForPageToLoad()
    }

    fun fillIncomeDetails(amount: Double, paymentType: String, incomeType: String, note: String = "") {
        enterAmountByKeyboard(amount)
        choosePaymentType(paymentType)
        addNote(note)
        openCategorySelection()
        chooseIncomeType(incomeType)
    }

    fun chooseIncomeType(incomeType: String) {
        val validIncomeTypes = listOf("Deposits", "Salary", "Savings")
        if (!validIncomeTypes.contains(incomeType)) {
            throw RuntimeException("Invalid incomeType: '$incomeType'. Valid types are: ${validIncomeTypes.joinToString(", ")}")
        }
        selenium.waitAndClickByTextContains(incomeType)
    }

}

