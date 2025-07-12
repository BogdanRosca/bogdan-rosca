package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class AddExpensePage(driver: RemoteWebDriver) : AddTransactionPage(driver) {

    private val addExpenseButtonId = "com.monefy.app.lite:id/keyboard_action_button"

    override fun waitForPageToLoad() {
        super.waitForPageToLoad()
    }

    fun fillExpenseDetails(amount: Double, paymentType: String, expenseType: String, note: String = "") {
        enterAmountByKeyboard(amount)
        choosePaymentType(paymentType)
        addNote(note)
        openCategorySelection()
        chooseExpenseType(expenseType)
    }

    fun chooseExpenseType(expenseType: String) {
        val validExpenseTypes = listOf(
            "Bills", "Car", "Clothes", "Communications", "Eating out", 
            "Entertainment", "Food", "Gifts", "Health", "House", 
            "Pets", "Sports", "Taxi", "Toiletry", "Transport"
        )
        if (!validExpenseTypes.contains(expenseType)) {
            throw RuntimeException("Invalid expenseType: '$expenseType'. Valid types are: ${validExpenseTypes.joinToString(", ")}")
        }
        selenium.waitAndClickByTextContains(expenseType)
    }

    fun checkSelectedCategory(category: String) {
        val expectedText = "ADD '" + category.uppercase() + "'"    
        selenium.waitForTextById(addExpenseButtonId, expectedText, "Category $category is not selected")
    }

} 