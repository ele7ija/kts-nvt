package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"title\"]")
    private WebElement title;

    @FindBy(xpath = "//*[@id=\"emailText\"]")
    private WebElement emailText;

    @FindBy(xpath = "//*[@id=\"addCancelBtn\"]")
    private WebElement addCancelBtn;

    @FindBy(xpath = "//*[@id=\"addSaveBtn\"]")
    private WebElement addSaveBtn;

    @FindBy(xpath = "//*[@aria-label=\"Next page\"]")
    private WebElement NextPageButton;

    @FindBy(xpath = "//table/tbody/tr[position() = (last()-1)]/td[4]/mat-icon[1]")
    private WebElement editBtn;

    @FindBy(xpath = "//table/tbody/tr[position() = (last()-1)]/td[4]/mat-icon[2]")
    private WebElement deleteBtn;

    @FindBy(id = "file")
    private WebElement choseFileBtn;

    @FindBy(id = "removeFileBtn")
    private WebElement deleteFileBtn;

    public NewsPage() {
    }

    public NewsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedInput() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("title")));
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getEmailText() {
        return emailText;
    }

    public WebElement getAddCancelBtn() {
        return addCancelBtn;
    }

    public WebElement getAddSaveBtn() {
        return addSaveBtn;
    }

    public WebElement getNextPageButton() {
        return NextPageButton;
    }

    public WebElement getDeleteBtn() {
        return deleteBtn;
    }

    public WebElement getChoseFileBtn() {
        return choseFileBtn;
    }

    public WebElement getEditBtn() {
        return editBtn;
    }

    public WebElement getDeleteFileBtn() {
        return deleteFileBtn;
    }
}
