package com.monefy.mobile.pages

import org.openqa.selenium.remote.RemoteWebDriver

class HomePage(driver: RemoteWebDriver) : BasePage(driver) {
    
    private val topbBarId = "com.monefy.app.lite:id/toolbar_layout"
    
    override fun waitForPageToLoad() {
        selenium.assertVisibleById(topbBarId, "Home screen is not visible")
    }

} 