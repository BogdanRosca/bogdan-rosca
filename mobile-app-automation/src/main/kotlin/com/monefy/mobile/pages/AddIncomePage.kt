package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.Transaction

class AddIncomePage(driver: RemoteWebDriver) : AddTransactionPage(driver) {

    override fun waitForPageToLoad() { 
        super.waitForPageToLoad()
    }

    fun fillIncomeDetails(transaction: Transaction) {
        enterAmountByKeyboard(transaction.amount)
        choosePaymentType(transaction.paymentType)
        addNote(transaction.note)
        openCategorySelection()
        chooseIncomeType(transaction.category)
    }

    fun chooseIncomeType(incomeType: String) {
        val validIncomeTypes = listOf("Deposits", "Salary", "Savings")
        if (!validIncomeTypes.contains(incomeType)) {
            throw RuntimeException("Invalid incomeType: '$incomeType'. Valid types are: ${validIncomeTypes.joinToString(", ")}")
        }
        selenium.waitAndClickByTextContains(incomeType)
    }

}

