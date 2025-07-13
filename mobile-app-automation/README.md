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
- Java v19.0.1
- Appium v2.19.0
- Android SDK (for Android device/emulator management)

Steps: 
1. Clone the repositoty git@github.com:BogdanRosca/bogdan-rosca.git
2. Install Java `19 brew install openjdk@19`
3. Install Appium `npm install -g appium@2.19.0`
4. Run Appium `appium`
5. Install Android SDK and set up an Android Emulator 
6. Check your emulator name `adb devices`
7. In `mobile-app-automation/src/main/kotlin/com/monefy/mobile/config/AppiumConfig.kt` Update the **deviceName** with above value 
8. Run the tests `./gradlew test`
9. Install Allure `brew install allure`
10. Build Allure report `allure generate build/allure-results --clean -o "allure-report"`
11. Serve Allure report `allure open allure-report`
