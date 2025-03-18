package com.epam.tests.stepdefinitions;

import static com.codeborne.selenide.Selenide.open;

import com.epam.pages.SauceInventoryPage;
import com.epam.pages.SauceLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

  private SauceLoginPage sauceLoginPage;

  private SauceInventoryPage sauceInventoryPage;

  @Given("the user is on the login page")
  public void the_user_is_on_the_login_page() {
    open("https://www.saucedemo.com");
  }

  @When("the user enters \"([^\"]*)\" and \"([^\"]*)\"$")
  public void the_user_enters_and(String username, String password) {
    sauceLoginPage = new SauceLoginPage();
    sauceLoginPage.typeValidName(username, password);
  }

  @And("the user clicks the login button")
  public void theUserClicksTheLoginButton() {
    sauceLoginPage.pressLoginButton();
  }

  @Then("the user should see the inventory page")
  public void theUserShouldSeeTheInventoryPage() {
    sauceInventoryPage = new SauceInventoryPage();
    Assert.assertTrue(sauceInventoryPage.isLogoDisplayed(), "Logo is not displayed");
  }

  @When("the user enters invalid \"([^\"]*)\" and \"([^\"]*)\"$")
  public void theUserEntersInvalidAnd(String username, String password) {
    sauceLoginPage = new SauceLoginPage();
    sauceLoginPage.typeAnyCred(username, password);
  }

  @Then("the user should see the error message")
  public void theUserShouldSeeTheErrorMessage() {
    Assert.assertEquals(sauceLoginPage.getErrorMessage(),
        "Epic sadface: Username is required", "Username validation failed");
  }
}
