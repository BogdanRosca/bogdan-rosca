package com.monefy.mobile.pages

import com.monefy.mobile.utils.SeleniumUtils
import org.openqa.selenium.remote.RemoteWebDriver

abstract class BasePage(protected val driver: RemoteWebDriver) {
    protected val selenium = SeleniumUtils(driver)
    
    abstract fun waitForPageToLoad()
} 