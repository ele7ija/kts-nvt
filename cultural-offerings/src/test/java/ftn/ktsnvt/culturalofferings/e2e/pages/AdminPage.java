package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    private WebElement firstName;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    private WebElement lastName;

    @FindBy(xpath = "//*[@id=\"addCancelBtn\"]")
    private WebElement addCancelBtn;

    @FindBy(xpath = "//*[@id=\"addAdminBtn\"]")
    private WebElement addAdminBtn;

    @FindBy(xpath = "//*[@aria-label=\"Next page\"]")
    private WebElement NextPageButton;

    @FindBy(xpath = "//table/tbody/tr[position() = last()]/td[6]/mat-icon[1]")
    private WebElement deleteBtn;

    public AdminPage() {
    }

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedInput() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getAddCancelBtn() {
        return addCancelBtn;
    }

    public WebElement getAddAdminBtn() {
        return addAdminBtn;
    }

    public WebElement getNextPageButton() {
        return NextPageButton;
    }

    public WebElement getDeleteBtn() {
        return deleteBtn;
    }





}
