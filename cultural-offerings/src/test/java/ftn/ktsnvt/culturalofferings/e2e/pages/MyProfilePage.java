package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfilePage {
	
	private WebDriver driver;

	//change user data form
    @FindBy(xpath = "//*[@id=\"firstName\"]")
    private WebElement firstName;
    
    @FindBy(xpath = "//*[@id=\"lastName\"]")
    private WebElement lastName;
    
    @FindBy(xpath = "//*[@id=\"dataBtn\"]")
    private WebElement dataBtn;
    
    @FindBy(xpath = "//*[@id=\"dataMsgSuccess\"]")
    private WebElement dataMsgSuccess;
    
    @FindBy(xpath = "//*[@id=\"dataMsgError\"]")
    private WebElement dataMsgError;
    
    //change password form
    @FindBy(xpath = "//*[@id=\"oldPassField\"]")
    private WebElement oldPassField;
    
    @FindBy(xpath = "//*[@id=\"passField\"]")
    private WebElement passField;
    
    @FindBy(xpath = "//*[@id=\"confPassField\"]")
    private WebElement confPassField;
    
    @FindBy(xpath = "//*[@id=\"passBtn\"]")
    private WebElement passBtn;
    
    @FindBy(xpath = "//*[@id=\"passMsgSuccess\"]")
    private WebElement passMsgSuccess;
    
    @FindBy(xpath = "//*[@id=\"passMsgError\"]")
    private WebElement passMsgError;
    
    public void ensureIsDisplayedElement() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
    }
    
    public void ensureIsNotVisibleDataBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("dataBtn")));
    }
    
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement getFirstName() {
		return firstName;
	}
	
	public WebElement getLastName() {
		return lastName;
	}
	
	public WebElement getDataBtn() {
		return dataBtn;
	}
	
	public WebElement getOldPassField() {
		return oldPassField;
	}
	
	public WebElement getPassField() {
		return passField;
	}
	
	public WebElement getConfPassField() {
		return confPassField;
	}
	
	public WebElement getPassBtn() {
		return passBtn;
	}

	public WebElement getDataMsgSuccess() {
		return dataMsgSuccess;
	}

	public WebElement getDataMsgError() {
		return dataMsgError;
	}

	public WebElement getPassMsgSuccess() {
		return passMsgSuccess;
	}

	public WebElement getPassMsgError() {
		return passMsgError;
	}
    
}
