package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {
	
	private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"emailField\"]")
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"passField\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"signInBtn\"]")
    private WebElement signinBtn;

    public SigninPage() {
    }

    public SigninPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
    	//wait 30s to ensure page is loaded
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("emailField")));
    }
    public void ensureIsNotVisibleLoginBtn() {
    	//wait 10s to ensure login succeed and user is redirected to another page 
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInBtn")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("emailField")));
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSigninBtn() {
        return signinBtn;
    }

}
