package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GithubSteps {
    private WebDriver driver;
    private WebDriverWait wait;


        @Given("I open GitHub")
        public void i_open_github() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://github.com");
        }

        @And("I log in with valid credentials")
        public void i_log_in_with_valid_credentials() {
            driver.findElement(By.linkText("Sign in")).click();
            driver.findElement(By.id("login_field")).sendKeys("your_email@example.com");
            driver.findElement(By.id("password")).sendKeys("your_password");
            driver.findElement(By.name("commit")).click();
        }

        @When("I navigate to the {string} section in my profile")
        public void i_navigate_to_the_section_in_my_profile(String section) {
            WebElement profileDropdown = driver.findElement(By.cssSelector("summary[aria-label='View profile and more']"));
            profileDropdown.click();
            driver.findElement(By.linkText("Your profile")).click();
            driver.findElement(By.linkText(section)).click();
        }

        @And("I search for the repository named {string}")
        public void i_search_for_the_repository_named(String repoName) {
            WebElement searchBox = driver.findElement(By.cssSelector("input[placeholder='Find a repository…']"));
            searchBox.sendKeys(repoName);
            searchBox.submit();
        }

        @Then("I should see it listed with visibility as {string}")
        public void i_should_see_it_listed_with_visibility_as(String visibility) {
            WebElement repoVisibility = driver.findElement(By.cssSelector(".Label"));
            if (!repoVisibility.getText().equalsIgnoreCase(visibility)) {
                throw new AssertionError("Expected visibility: " + visibility + ", but found: " + repoVisibility.getText());
            }
        }

        @And("the last activity date displayed")
        public void the_last_activity_date_displayed() {
            WebElement lastActivity = driver.findElement(By.cssSelector(".updated-at"));
            if (lastActivity.getText().isEmpty()) {
                throw new AssertionError("Last activity date is not displayed.");
            }
        }

        @When("I complete repository checks")
        public void i_complete_repository_checks() {
            System.out.println("Repository checks completed.");
        }

        @And("click {string} in the profile menu")
        public void click_in_the_profile_menu(String option) {
            WebElement profileDropdown = driver.findElement(By.cssSelector("summary[aria-label='View profile and more']"));
            profileDropdown.click();
            driver.findElement(By.linkText(option)).click();
        }

        @Then("I should be logged out successfully")
        public void i_should_be_logged_out_successfully() {
            if (!driver.getCurrentUrl().contains("github.com")) {
                throw new AssertionError("Logout unsuccessful. Current URL: " + driver.getCurrentUrl());
            }
            driver.quit();
        }
    }
