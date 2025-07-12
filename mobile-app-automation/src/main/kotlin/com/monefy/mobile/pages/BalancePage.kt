package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.StringUtils

class BalancePage(driver: RemoteWebDriver) : BasePage(driver) {

    private val transactionsHeaderId = "com.monefy.app.lite:id/balance_amount"
    private val transactionsCategoryContianer = "com.monefy.app.lite:id/transaction_group_header"
    private val transactionsCategoryNameId = "com.monefy.app.lite:id/textViewCategoryName"
    private val transactionsCategoryTotalId = "com.monefy.app.lite:id/textViewWholeAmount"
    private val transactionsAmountId = "com.monefy.app.lite:id/textViewTransactionAmount"
    private val transactionsNoteId = "com.monefy.app.lite:id/textViewTransactionNote"
    private val transactionsDateId = "com.monefy.app.lite:id/textViewTransactionDate"

    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(transactionsHeaderId, "Balance screen is not visible")
    }

    fun checkTransactionChategory(index: Int, category: String, amount: Double, note: String, date: String) {
        checkChategyName(index, category)
        checkChategyTotalAmount(index, category, amount)
        checkTransactionChategoryDetails(index, category, amount, note, date)
    }

    fun checkTransactionChategoryDetails(index: Int, category: String, amount: Double, note: String, date: String) {
        expandTransactionChategory(index)
        checkTransactionAmount(index, category, amount)
        checkTransactionNote(index, category, note)
        checkTransactionDate(index, category, date)
    }

    fun checkChategyName(index: Int, category: String) {
        var chategories = selenium.waitAndReturnElementsById(transactionsCategoryNameId)
        assert(chategories[index].text.contains(category)) { "Transaction category $category not found" }
    }

    fun checkChategyTotalAmount(index: Int, category: String, amount: Double) {
        var totals = selenium.waitAndReturnElementsById(transactionsCategoryTotalId) 
        var chategoryTotals = StringUtils.formatBalance(amount)
        assert(totals[index].text.contains(chategoryTotals)) { "Transaction category $category amount is wrong. Expected $amount, but got ${totals[index].text}" }
    }

    fun expandTransactionChategory(index: Int) {
        var transactionContainer = selenium.waitAndReturnElementsById(transactionsCategoryContianer) 
        transactionContainer[index].click()
    }

    fun checkTransactionAmount(index: Int, category: String, amount: Double) {
        var amountString = StringUtils.formatBalance(amount)
        var amounts = selenium.waitAndReturnElementsById(transactionsAmountId) 
        assert(amounts[index].text.contains(amountString)) { "Transaction category $category amount is wrong. Expected $amountString, but got ${amounts[index].text}" }
    }

    fun checkTransactionNote(index: Int, category: String, note: String) {
        var notes = selenium.waitAndReturnElementsById(transactionsNoteId) 
        assert(notes[index].text.contains(note)) { "Transaction category $category note is wrong. Expected $note, but got ${notes[index].text}" }
    }

    fun checkTransactionDate(index: Int, category: String, date: String) {
        var dates = selenium.waitAndReturnElementsById(transactionsDateId) 
        assert(dates[index].text.contains(date)) { "Transaction category $category date is wrong. Expected $date, but got ${dates[index].text}" }
    }

} 