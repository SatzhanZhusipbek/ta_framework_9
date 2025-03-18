# **Practice Task from Modules 9 and 10**
The Test Automation Framework was built using *TestNG*, *Selenide*, *Selenium*, *Maven*, 
*WebDriverManager* and *Cucumber*.

---

## **Features**

- **WebDriverManager** - browser driver management dependency.
- **Page Object Model (POM)** Abstractions of real web pages.
- **Business Models (User)** - Entity used in tests.
- **Environment-Specific Properties** - *dev* and *staging* environments.
- **TestNG XML Suites** - *Smoke* and *Regression* xml files.
- **Parallel Test Execution** - Tests that run using *testng.xml* file.
- **Logging** - Using *LogBack* -> (console logs + daily file logs).
- **Screenshot Capture** - Screenshots that are taken on test failures with logs.
- **Element Highlighting** - Important elements are emphasized during test execution.
- **SOLID Principles** - the project follows the principles. 
- **Cucumber BDD** - the project implements the BDD using step definitions and a feature file.
---
## **Design Patterns**

- **Factory Method** - used to abstract WebDriver creation logic through the use
of abstract *WebDriverFactory* and two its implementations: *ChromeDriverFactory* and 
*FirefoxDriverFactory*.
- **Singleton** - ensures that only WebDriver instance is per thread in *DriverManager*, 
also only one config object is loaded in *ConfigManager*.
- **Decorator** - wraps WebDriver to add extra functionality using *WebDriverDecorator*.
- **Strategy** - allows switching config sources, doesn't break existing functionality
through the use of *ConfigLoader* strategy interface.
- **Observer** - listens for test failures, logs errors and takes screenshots -> implemented
in *TestsListener*.

---
## **Tests**

### How to Run the Smoke Test
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/suites/smoke.xml"
```

### How to Run the Regression Test
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/suites/regression.xml"
```

### How to Run two Tests concurrently
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng.xml"
```

### How to Run the Cucumber Test
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/cucumber.xml"
```

## **Environments & Browsers**

The framework can run tests in the following environments (*dev* and *staging*) 
and across these browsers (*Chrome* and *Firefox*).

### **Environment Configuration**
Environment properties are situated in:
- `src/main/resources/dev.properties`
- `src/main/resources/staging.properties`

