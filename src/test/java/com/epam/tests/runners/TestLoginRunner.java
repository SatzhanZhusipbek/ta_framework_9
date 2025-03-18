package com.epam.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
    glue = {"com.epam.tests.stepdefinitions", "com.epam.tests.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true)
public class TestLoginRunner extends AbstractTestNGCucumberTests {

}
