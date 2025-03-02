# **Test Automation Framework**
A robust and flexible Test Automation Framework built using **Java**, **TestNG**, **Selenide**, **WebDriverManager**, and **Maven**.

---

## **Features**

- ✅ **WebDriverManager** - Seamless browser driver management.
- ✅ **Page Object Model (POM)** with **PageFactory** - Clean and maintainable page interactions.
- ✅ **Business Models (UserFactory)** - Centralized test data management.
- ✅ **Environment-Specific Properties** - Easily switch between **dev** and **qa** environments.
- ✅ **TestNG XML Suites** - Organized **Smoke** and **Regression** test execution.
- ✅ **Parallel Test Execution** - Run tests across multiple browsers and environments simultaneously.
- ✅ **Logging** - Detailed logs using **Log4j2** (console + daily rolling file logs).
- ✅ **Screenshot Capture** - Automatic screenshot on test failures with log references.
- ✅ **Element Highlighting** - Visual feedback during test execution.
- ✅ **CI/CD Ready** - Flexible parameters for seamless integration.

---

## **Design Patterns Implemented**

- **Singleton**: Ensures a single instance of key classes like `ConfigLoader` and WebDriver management (`DriverFactory`).
- **Factory Method**: Dynamically initializes browsers through the `BrowserFactory` interface with specific implementations (e.g., `ChromeDriverFactory`, `FirefoxDriverFactory`).
- **Decorator**: Enhances element interactions with additional functionalities like logging and highlighting, without modifying existing code (`ElementDecorator`, `LoggingDecorator`, `HighlightingDecorator`).
- **Strategy** *(Bonus)*: Provides flexible login strategies (`LoginStrategy`, `StandardLoginStrategy`).


## **Test Execution**

### ✅ Run **Smoke Suite**
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/suites/smoke-tests.xml"
```

### ✅ Run **Regression Suite**
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/suites/regression-tests.xml"
```

### ✅ Run **Parallel Execution Suite** *(Smoke & Regression across different browsers and environments)*
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng.xml"
```

## **Environments & Browsers**

This framework supports running tests in various environments (**dev**, **qa**) and across multiple browsers (**Chrome**, **Firefox**).

### **Environment Configuration**
Environment-specific properties are located in:
- `src/test/resources/config/dev.properties`
- `src/test/resources/config/qa.properties`

---

## **Key Components**

### 🧩 **LoginTest**
Tests the login functionality with different scenarios:
- ✅ Successful login with valid credentials. *(Implemented using Strategy pattern for flexibility)*
- 🚫 Login failure with invalid credentials.

### **DriverFactory**
- Initializes browsers using the **Factory Method** pattern.
- Manages WebDriver lifecycle through a **Singleton** approach for resource safety.

### **LoginPage**
- Uses the **Decorator** pattern to enhance element interactions (logging & highlighting).

###  **ConfigLoader**
Loads environment-specific properties dynamically.

###  **ScreenshotUtils**
Captures screenshots on test failures for easier debugging.

###  **Logs**
Daily log files stored in the logs/ folder at the project root for tracking and debugging.

###  **Selenide Reports**
Detailed execution reports are automatically generated and stored in the target/selenide-reports/ directory.

---

## **Best Practices Implemented**

- **Code Reusability**: POM, Business Models, and Design Patterns prevent code duplication.
- **Flexibility**: Run tests with different browsers, environments, and login strategies using parameters.
- **Maintainability**: Adherence to **S.O.L.I.D. principles** and the use of patterns improve code organization.
- **Scalability**: Easily extend functionality with new browsers, login strategies, or decorators.
- **CI/CD Integration**: Ready for pipeline execution with parameterized builds.  