package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class UpgradePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val closeButtonId = "com.monefy.app.lite:id/buttonClose"
    private val giftToYourselfId = "com.monefy.app.lite:id/textViewPrice"
    
    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(giftToYourselfId)       
    }
    
    fun closeUpgradeScreen() {
            selenium.clickById(closeButtonId)
    }

} 