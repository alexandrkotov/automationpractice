package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class WebDriverAutomationpracticeDef {

    private String testEmail;
    private String testPass;
    private String testEmailExtra;

    @Given("I open url {string} and verify title contains {string}")
    public void iOpenUrlAndVerifyTitleContains(String url, String title) throws InterruptedException {
        getDriver().get(url);
        sleep(3000);
        getDriver().getTitle().contains(title);
    }

    @Given("I set email as {string} and password as {string} for further scenarios")
    public void iSetEmailAsAndPasswordAsForFurtherScenarios(String email, String pass) {
        this.testEmail = email;
        this.testPass = pass;
        String[] arrOfStr = email.split("@", 2);
        this.testEmailExtra = arrOfStr[0] + "2@" + arrOfStr[1];
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int sec) throws InterruptedException {
        sleep(sec * 1000);
    }

    @When("I have empty search field and click on magnifier button")
    public void iHaveEmptySearchFieldAndClickOnMagnifierButton() {
        getDriver().findElement(By.xpath("//*[@id='search_query_top']")).clear();
        getDriver().findElement(By.xpath("//*[@name='submit_search']")).click();
    }

    @Then("message {string} should be appear on the page")
    public void messageShouldBeAppearOnThePage(String message) {
        assertThat(getDriver().findElement(By.xpath("//*[@id='center_column']/p")).getText()).containsIgnoringCase(message);
    }

    @When("I type {string} in search field and click on magnifier button")
    public void iTypeInSearchFieldAndClickOnMagnifierButton(String searchItem) {
        getDriver().findElement(By.xpath("//*[@id='search_query_top']")).clear();
        getDriver().findElement(By.xpath("//*[@id='search_query_top']")).sendKeys(searchItem);
        getDriver().findElement(By.xpath("//*[@name='submit_search']")).click();
    }

    @Then("a non-empty search result should appear")
    public void aNonEmptySearchResultShouldAppear() {
        assertThat(getDriver().findElement(By.xpath("//*[@class='product-container']")).isDisplayed()).isTrue();
    }

    @When("I click on {string} in top of the page")
    public void iClickOnInTopOfThePage(String button) {
        getDriver().findElement(By.xpath("//*[contains(text(),'" + button + "')]")).click();
    }

    @Then("authentication page should be appear")
    public void authenticationPageShouldBeAppear() throws InterruptedException {
        assertThat(getDriver().findElements(By.xpath("//input[@id='email_create']"))).hasSize(1);
        sleep(500);
    }

    @When("I type {string} into email field and click on [Create an account] button")
    public void iTypeIntoEmailFieldAndClickOnCreateAnAccountButton(String email) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='email_create']")).clear();
        sleep(200);
        if (email.equals("emailGiven")) {
            email = testEmail;
        }
        if (email.equals("emailGivenExtra")) {
            email = testEmailExtra;
        }
        getDriver().findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        getDriver().findElement(By.xpath("//button[@id='SubmitCreate']")).click();
    }

    @Then("error message {string} should be appear")
    public void errorMessageShouldBeAppear(String errorMessage) throws InterruptedException {
        assertThat(getDriver().findElement(By.xpath("//div[@id='create_account_error']/ol/li")).isDisplayed()).isTrue();
        sleep(200);
        String actualText = getDriver().findElement(By.xpath("//div[@id='create_account_error']/ol/li")).getText();
        assertThat(actualText).containsIgnoringCase(errorMessage);
        sleep(1000);
    }

    @Then("registration form should be displayed")
    public void registrationFormShouldBeDisplayed() {
        assertThat(getDriver().findElement(By.xpath("//*[@id='account-creation_form']")).isDisplayed()).isTrue();
    }

    @And("I put all necessary information into registration form: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iPutAllNecessaryInformationIntoRegistrationForm(String firstName, String lastName, String pass, String address, String city, String state, String zip, String country, String phone) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(firstName);
        getDriver().findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lastName);
        if (pass.equals("passGiven")) {
            pass = testPass;
        }
        getDriver().findElement(By.xpath("//input[@id='passwd']")).sendKeys(pass);
        getDriver().findElement(By.xpath("//input[@id='address1']")).sendKeys(address);
        getDriver().findElement(By.xpath("//input[@id='city']")).sendKeys(city);
        getDriver().findElement(By.xpath("//option[contains(text(),\"" + state + "\")]")).click();
        sleep(200);
        getDriver().findElement(By.xpath("//input[@id='postcode']")).sendKeys(zip);
        getDriver().findElement(By.xpath("//option[contains(text(),\"" + country + "\")]")).click();
        sleep(200);
        getDriver().findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys(phone);
        getDriver().findElement(By.xpath("//input[@id='alias']")).sendKeys(address);
        getDriver().findElement(By.xpath("//button[@id='submitAccount']")).click();
    }

    @Then("welcome to an account page should be appear")
    public void welcomeToAnAccountPageShouldBeAppear() {
        assertThat(getDriver().findElements(By.xpath("//p[contains(text(),'Welcome to your account')]"))).hasSize(1);
    }

    @When("I type {string} in email field of sign in form")
    public void iTypeInEmailFieldOfSignInForm(String email) {
        if (email.equals("emailGiven")) {
            email = testEmail;
        }
        if(email.equals("emailGivenExtra")){
            email = testEmailExtra;
        }
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(email);
    }

    @And("I type {string} in password field of sign in form and click on [Sign in] button")
    public void iTypeInPasswordFieldOfSignInFormAndClickOnSignInButton(String pass) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='passwd']")).clear();
        sleep(200);
        if (pass.equals("passwordGiven")) {
            pass = testPass;
        }
        getDriver().findElement(By.xpath("//input[@id='passwd']")).sendKeys(pass);
        sleep(300);
        getDriver().findElement(By.xpath("//button[@id='SubmitLogin']")).click();
    }

    @Then("sign in error message {string} should be appear")
    public void signInErrorMessageShouldBeAppear(String errorMessage) throws InterruptedException {
        assertThat(getDriver().findElement(By.xpath("//div[@id='center_column']/div/ol/li")).isDisplayed()).isTrue();
        sleep(200);
        String actualText = getDriver().findElement(By.xpath("//div[@id='center_column']/div/ol/li")).getText();
        assertThat(actualText).containsIgnoringCase(errorMessage);
    }

    @And("I just stop here")
    public void iJustStopHere() {
        System.out.println("nothing");
    }

    @When("I have empty field {string} and  click [Register] button")
    public void iHaveEmptyFieldAndClickRegisterButton(String fieldName) throws InterruptedException {
        String xpath = "";
        switch (fieldName) {
            case "First name":
                xpath = "//input[@id='customer_firstname']";
                break;
            case "Last name":
                xpath = "//input[@id='customer_lastname']";
                break;
            case "Password":
                xpath = "//input[@id='passwd']";
                break;
            case "Address":
                xpath = "//input[@id='address1']";
                break;
            case "City":
                xpath = "//input[@id='city']";
                break;
            case "Zip/Postal Code":
                xpath = "//input[@id='postcode']";
                break;
            case "Mobile phone":
                xpath = "//input[@id='phone_mobile']";
                break;
            case "Assign an address alias for future reference":
                xpath = "//input[@id='alias']";
                break;
            default:
                xpath = "";
                break;
        }
        getDriver().findElement(By.xpath(xpath)).clear();
        sleep(200);
        getDriver().findElement(By.xpath("//*[@id='submitAccount']")).click();
    }

    @Then("error message containing [{string} is required] should be appear above the registration form")
    public void errorMessageContainingIsRequiredShouldBeAppearAboveTheRegistrationForm(String errorMessage) {
        assertThat(getDriver().findElement(By.xpath("//*[contains(@class,'alert')]/*//*[contains(text(),'"+errorMessage+"')]")).isDisplayed()).isTrue();
    }

    @And("I type {string} in the field {string}")
    public void iTypeInTheField(String text, String fieldName) {
        String xpath = "";
        switch (fieldName) {
            case "First name":
                xpath = "//input[@id='customer_firstname']";
                break;
            case "Last name":
                xpath = "//input[@id='customer_lastname']";
                break;
            case "Password":
                xpath = "//input[@id='passwd']";
                break;
            case "Address":
                xpath = "//input[@id='address1']";
                break;
            case "City":
                xpath = "//input[@id='city']";
                break;
            case "Zip/Postal Code":
                xpath = "//input[@id='postcode']";
                break;
            case "Mobile phone":
                xpath = "//input[@id='phone_mobile']";
                break;
            case "Assign an address alias for future reference":
                xpath = "//input[@id='alias']";
                break;
            default:
                xpath = "";
                break;
        }
        getDriver().findElement(By.xpath(xpath)).clear();
        if(text.equals("passwordGiven")){
            text = testPass;
        }
        getDriver().findElement(By.xpath(xpath)).sendKeys(text);
    }

    @When("I clear country in drop down list and click [Register] button")
    public void iClearCountryInDropDownList() throws InterruptedException {
        getDriver().findElement(By.xpath("//select[@id='id_country']/option[contains(text(),'-')]")).click();
        sleep(200);
        getDriver().findElement(By.xpath("//*[@id='submitAccount']")).click();
    }

    @And("I choose [United States] in country drop down list")
    public void iChooseUnitedStatesInCountryDropDownList() {
        getDriver().findElement(By.xpath("//select[@id='id_country']/option[contains(text(),'United States')]")).click();
    }

    @Then("error message containing {string} should be appear above the registration form")
    public void errorMessageContainingShouldBeAppearAboveTheRegistrationForm(String errorMessage) {
        assertThat(getDriver().findElement(By.xpath("//*[contains(@class,'alert')]/*//*[contains(text(),'"+errorMessage+"')]")).isDisplayed()).isTrue();
    }

    @When("I clear state in drop down list and click [Register] button")
    public void iClearStateInDropDownListAndClickRegisterButton() throws InterruptedException {
        //"//select[@id='id_state']/option[contains(text(),\"" + "-" + "\")]"
        getDriver().findElement(By.xpath("//select[@id='id_state']/option[contains(text(),'-')]")).click();
        sleep(200);
        getDriver().findElement(By.xpath("//*[@id='submitAccount']")).click();
    }

    @And("I choose [Washington] in state drop down list")
    public void iChooseWashingtonInStateDropDownList() {
        //"//option[contains(text(),\"" + "Washington" + "\")]"
        getDriver().findElement(By.xpath("//select[@id='id_state']/option[contains(text(),'Washington')]")).click();
    }

    @When("I click {string} button in my account")
    public void iClickButtonInMyAccount(String button) {
        String xpath = "";
        if(button.contains("MY ADDRESSES")){
            xpath = "//*[@title='Addresses']";
        } else if(button.contains("MY PERSONAL INFORMATION")){
            xpath = "//*[@title='Information']";
        } else {
            System.out.println("Unknown button");
        }
        getDriver().findElement(By.xpath(xpath)).click();
    }

    @Then("page with my addresses should be appear")
    public void pageWithMyAddressesShouldBeAppear() {
        assertThat(getDriver().findElements(By.xpath("//*[@class='addresses']"))).hasSize(1);
    }

    @And("{string} should be on the page")
    public void shouldBeOnThePage(String text) {
        assertThat(getDriver().findElement(By.xpath("//*[@class='addresses']//*[contains(text(),'"+text+"')]")).isDisplayed()).isTrue();
    }

    @When("I click [Back to your account] button")
    public void iClickBackToYourAccountButton() {
        getDriver().findElement(By.xpath("//*[contains(text(),'Back to your account')]")).click();
    }

    @Then("page with my personal information should be appear")
    public void pageWithMyPersonalInformationShouldBeAppear() {
        assertThat(getDriver().findElements(By.xpath("//h1[contains(text(),'Your personal information')]"))).hasSize(1);
    }

    @And("{string} should be on this page")
    public void shouldBeOnThisPage(String text) {
        if(text.equals("emailGivenExtra")){
            text = testEmailExtra;
        }
        assertThat(getDriver().findElements(By.xpath("//input[@value='"+text+"']"))).hasSize(1);
    }
}
