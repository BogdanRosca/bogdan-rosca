package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class UpgradePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val closeButtonId = "com.monefy.app.lite:id/buttonClose"
    private val giftToYourselfId = "com.monefy.app.lite:id/textViewPrice"
    
    override fun waitForPageToLoad() {
        selenium.waitForElementVisibilityById(giftToYourselfId, "Upgrade screen not displayed", 10000)       
    }
    
    fun closeUpgradeScreen() {
            selenium.waitAndClickById(closeButtonId)
    }

} 