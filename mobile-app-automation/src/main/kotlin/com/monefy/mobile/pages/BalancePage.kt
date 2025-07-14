package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.StringUtils
import com.monefy.mobile.utils.Transaction

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

    fun checkTransactionChategory(index: Int, transaction: Transaction) {
        checkChategyName(index, transaction)
        checkChategyTotalAmount(index, transaction)
        checkTransactionChategoryDetails(index, transaction)
    }

    fun checkTransactionChategoryDetails(index: Int, transaction: Transaction) {
        expandTransactionChategory(index)
        checkTransactionAmount(index, transaction)
        checkTransactionNote(index, transaction)
        checkTransactionDate(index, transaction)
    }

    fun checkChategyName(index: Int, transaction: Transaction) {
        var chategories = selenium.waitAndReturnElementsById(transactionsCategoryNameId)
        assert(chategories[index].text.contains(transaction.category)) { "Transaction category ${transaction.category} not found" }
    }

    fun checkChategyTotalAmount(index: Int, transaction: Transaction) {
        var totals = selenium.waitAndReturnElementsById(transactionsCategoryTotalId) 
        var chategoryTotals = StringUtils.formatBalance(transaction.amount)
        assert(totals[index].text.contains(chategoryTotals)) { "Transaction category ${transaction.category} amount is wrong. Expected ${transaction.amount}, but got ${totals[index].text}" }
    }

    fun expandTransactionChategory(index: Int) {
        var transactionContainer = selenium.waitAndReturnElementsById(transactionsCategoryContianer) 
        transactionContainer[index].click()
    }

    fun checkTransactionAmount(index: Int, transaction: Transaction) {
        var amountString = StringUtils.formatBalance(transaction.amount)
        var amounts = selenium.waitAndReturnElementsById(transactionsAmountId) 
        assert(amounts[index].text.contains(amountString)) { "Transaction category ${transaction.category} amount is wrong. Expected ${transaction.amount}, but got ${amounts[index].text}" }
    }

    fun checkTransactionNote(index: Int, transaction: Transaction) {
        var notes = selenium.waitAndReturnElementsById(transactionsNoteId) 
        assert(notes[index].text.contains(transaction.note)) { "Transaction category ${transaction.category} note is wrong. Expected ${transaction.note}, but got ${notes[index].text}" }
    }

    fun checkTransactionDate(index: Int, transaction: Transaction) {
        var dates = selenium.waitAndReturnElementsById(transactionsDateId) 
        assert(dates[index].text.contains(transaction.date)) { "Transaction category ${transaction.category} date is wrong. Expected ${transaction.date}, but got ${dates[index].text}" }
    }

} 