package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver
import com.monefy.mobile.utils.StringUtils

class BalancePage(driver: RemoteWebDriver) : BasePage(driver) {

    private val stringUtils = StringUtils()
    private val transactionsHeaderId = "com.monefy.app.lite:id/balance_amount"
    private val transactionsCategoryNameId = "com.monefy.app.lite:id/textViewCategoryName"
    private val transactionsCategoryTotalId = "com.monefy.app.lite:id/textViewWholeAmount"
    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(transactionsHeaderId, "Balance screen is not visible", 10000)
    }

    fun checkChategory(index: Int, category: String, amount: Double) {
        checkChategyName(index, category)
        checkChategyTotalAmount(index, category, amount)
    }

    fun checkChategyName(index: Int, category: String) {
        var chategories = selenium.waitAndReturnElementsById(transactionsCategoryNameId)
        assert(chategories[index].text.contains(category)) { "Transaction category $category not found" }
    }

    fun checkChategyTotalAmount(index: Int, category: String, amount: Double) {
        var totals = selenium.waitAndReturnElementsById(transactionsCategoryTotalId) 
        var chategoryTotals = stringUtils.formatBalance(amount)
        assert(totals[index].text.contains(chategoryTotals)) { "Transaction category $category amount is wrong. Expected $amount, but got ${totals[index].text}" }
    }

} 