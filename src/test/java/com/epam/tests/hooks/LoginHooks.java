package com.epam.tests.hooks;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class LoginHooks {

    private WebDriver driver;

    @Before
    public void beforeLogin() {
        driver = DriverManager.getDriver("chrome");
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void afterLogin() {
        WebDriverRunner.closeWebDriver();
        DriverManager.closeDriver();
    }

}
