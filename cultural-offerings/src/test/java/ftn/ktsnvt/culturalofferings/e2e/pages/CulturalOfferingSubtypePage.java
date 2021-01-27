package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalOfferingSubtypePage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"subTypeName\"]")
    private WebElement subTypeName;

    @FindBy(xpath = "//*[@id=\"addCancelBtn\"]")
    private WebElement addCancelBtn;

    @FindBy(xpath = "//*[@id=\"addSaveBtn\"]")
    private WebElement addSaveBtn;

    @FindBy(xpath = "//*[@id=\"typeId\"]")
    WebElement typeSelectInput;

    //option: Manifestacija za brisanje
    @FindBy(xpath = "//*[@id=\"typeOption13\"]")
    WebElement typeOption;

    //option: Institucija
    @FindBy(xpath = "//*[@id=\"typeOption10\"]")
    WebElement changeTypeOption;

    @FindBy(xpath = "//*[@aria-label=\"Next page\"]")
    private WebElement NextPageButton;

    @FindBy(xpath = "//table/tbody/tr[position() = (last()-1)]/td[3]/mat-icon[2]")
    private WebElement deleteBtn;

    @FindBy(xpath = "//table/tbody/tr[position() = (last()-1)]/td[3]/mat-icon[1]")
    private WebElement editBtn;

    public CulturalOfferingSubtypePage() {
    }

    public CulturalOfferingSubtypePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedInput() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("subTypeName")));
    }

    public WebElement getAddCancelBtn() {
        return addCancelBtn;
    }

    public WebElement getSubtypeName() {
        return subTypeName;
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

    public WebElement getEditBtn() {
        return editBtn;
    }

    public WebElement getTypeSelectInput() {
        return this.typeSelectInput;
    }

    public WebElement getTypeOption() {
        return this.typeOption;
    }

    public WebElement getChangeTypeOption() {
        return this.changeTypeOption;
    }

}

