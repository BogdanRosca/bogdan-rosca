# Mobile test automation 

## Used artifact: 
`[iOS](https://itunes.apple.com/us/app/monefy-money-manager/id1212024409?)`

## Test Framework Architecture

### Technology Stack Selection
In the initial phase of this initiative, I explored various tools available in the market. While I have experience with mobile automation frameworks using Python, Appium, and BrowserStack, I wanted to step outside my comfort zone and learn something new. Therefore, I chose to develop the framework using **Kotlin** and **TestNG** as the test orchestrator. Although this was my first encounter with Kotlin, my previous experience with Java, TestNG, and Selenium made the learning curve manageable.

### Project Structure
To maintain a clean and scalable solution, I implemented a clear separation between framework resources (under `main/`) and test cases (under `test/`). This structure promotes better organization and maintainability.
```
mobile-app-automation/
├── build.gradle.kts                                    # Gradle build configuration
├── gradlew                                             # Gradle wrapper script
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── com/monefy/mobile/
│   │           ├── apk/                                # Mobile app artifacts
│   │           │   └── com.monefy.app.lite_1.18.0.apk
│   │           ├── config/                             # Framework configuration
│   │           │   └── AppiumConfig.kt
│   │           ├── pages/                              # Page Object Model classes
│   │           │   ├── BasePage.kt
│   │           │   ├── HomePage.kt
│   │           │   ├── BalancePage.kt
│   │           │   ├── AddExpensePage.kt
│   │           │   ├── AddIncomePage.kt
│   │           │   ├── AddTransactionPage.kt
│   │           │   ├── OnboardingPage.kt
│   │           │   └── UpgradePage.kt
│   │           └── utils/                              # Utility classes
│   │               ├── DateUtils.kt
│   │               ├── SeleniumUtils.kt
│   │               ├── StringUtils.kt
│   │               └── Transaction.kt
│   └── test/
│       ├── kotlin/
│       │   └── com/monefy/mobile/
│       │       ├── base/                               # Base test classes
│       │       │   └── BaseTest.kt
│       │       └── tests/                              # Test implementations
│       │           ├── NewUserOnboarding.kt
│       │           ├── Transactions.kt
│       │           └── TransactionsHistory.kt
│       └── resources/
│           └── testng.xml                              # TestNG configuration
├── build/                                              # Build outputs
├── logs/                                               # Test execution logs
└── docs/                                               # Documentation
``` 

### Design Patterns
Once the first test was running successfully, I implemented the **Page Object Model (POM)** methodology to enhance maintainability. In this approach, element locators and methods are attached to specific pages (screens in mobile context), with each page having sole responsibility for its functionality.

### Reporting Framework
For test reporting, I chose **Allure**, a solution I'm highly familiar with. It's straightforward to implement - once the plugin is added, results are automatically generated under `build/allure-results`. These results can then be used to generate comprehensive reports using the commands detailed in the next section. To further enhance the reporting experience, I've utilized Allure decorators to provide optimal structure and improve test readability and maintenance.

## How to run the tests
### Running locally
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Java v21.0.7
- Appium v2.19.0
- Android SDK (for Android device/emulator management)

Setup steps: 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`
2. Install Java 21 `brew install openjdk@21`
3. Install Android SDK and create an Android Emulator 
4. Setup Android Studio environmental variables
    ```
    vi ~/.zshrc
    paste
        export ANDROID_HOME=$HOME/Library/Android/sdk
        export ANDROID_SDK_ROOT=$ANDROID_HOME
        export PATH=$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH
    save 
    source ~/.zshrc
    ```
5. Check your emulator name `adb devices`
6. In `mobile-app-automation/src/main/kotlin/com/monefy/mobile/config/AppiumConfig.kt` Update the **deviceName** with above value 
7. Install Appium `npm install -g appium@2.19.0`
8. Install Android Appium driver `appium driver install uiautomator2`
9. Install Allure `brew install allure`

Test execution steps: 
1. Make sure you are in `/mobile-app-automation` folder 
2. Start Appium `appium`
3. Make sure you are in `/mobile-app-automation` folder  
4. Run the tests `./gradlew test`. To run only a test use `./gradlew test --tests "com.monefy.mobile.tests.NewUserOnboarding"`
5. Build Allure report `allure generate build/allure-results --clean -o "allure-report"`
6. Serve Allure report `allure open allure-report`

