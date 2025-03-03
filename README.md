# **Practice Task from Module 9**
The Test Automation Framework was built using *TestNG*, *Selenide*, *Selenium*, *Maven* and 
*WebDriverManager*.

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

## **Environments & Browsers**

The framework can run tests in the following environments (*dev* and *staging*) 
and across these browsers (*Chrome* and *Firefox*).

### **Environment Configuration**
Environment properties are situated in:
- `src/main/resources/dev.properties`
- `src/main/resources/staging.properties`

