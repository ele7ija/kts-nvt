package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	
	private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"nameField\"]")
    private WebElement nameField;

    @FindBy(xpath = "//*[@id=\"surnameField\"]")
    private WebElement surnameField;

    @FindBy(xpath = "//*[@id=\"emailField\"]")
    private WebElement emailField;
    
    @FindBy(xpath = "//*[@id=\"passField\"]")
    private WebElement passField;
    
    @FindBy(xpath = "//*[@id=\"confPassField\"]")
    private WebElement confPassField;
    
    @FindBy(xpath = "//*[@id=\"regBtn\"]")
    private WebElement regBtn;

    public RegisterPage() {
    }

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
    	//wait 30s to ensure page is loaded
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("emailField")));
    }
    public void ensureIsNotVisibleRegBtn() {
    	//wait 10s to ensure registration succeed  
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("regBtn")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("emailField")));
    }

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getNameField() {
		return nameField;
	}

	public WebElement getSurnameField() {
		return surnameField;
	}

	public WebElement getEmailField() {
		return emailField;
	}

	public WebElement getPassField() {
		return passField;
	}

	public WebElement getConfPassField() {
		return confPassField;
	}

	public WebElement getRegBtn() {
		return regBtn;
	}
    
    

}
