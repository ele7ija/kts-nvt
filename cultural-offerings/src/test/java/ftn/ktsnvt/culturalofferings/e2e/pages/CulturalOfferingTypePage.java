package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalOfferingTypePage {
	
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"typeName\"]")
    private WebElement typeName;
	
	@FindBy(xpath = "//*[@id=\"typeMatChip\"]")
    private WebElement typeMatChip;
	
	@FindBy(xpath = "//*[@id=\"addCancelBtn\"]")
    private WebElement addCancelBtn;
	
	@FindBy(xpath = "//*[@id=\"removeSubtype\"]")
    private WebElement removeSubtypeBtn;
	
	@FindBy(xpath = "//*[@id=\"addSaveBtn\"]")
    private WebElement addSaveBtn;
	
	@FindBy(xpath = "//*[@aria-label=\"Next page\"]")
    private WebElement NextPageButton;

    @FindBy(xpath = "//*[@aria-label=\"Previous page\"]")
    private WebElement PreviousPageButton;
    
    @FindBy(xpath = "//tr[last()]//*[@id='deleteBtn']")
    private WebElement deleteBtn;
    
    @FindBy(id = "editBtn")
    private WebElement editBtn;

    public CulturalOfferingTypePage() {
    }

    public CulturalOfferingTypePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void ensureIsDisplayedInput() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("typeName")));
    }

	public WebElement getAddCancelBtn() {
		return addCancelBtn;
	}
	
	public WebElement getTypeName() {
		return typeName;
	}
	
	public WebElement getTypeMatChip() {
		return typeMatChip;
	}
	
	public WebElement getRemoveSubtypeBtn() {
		return removeSubtypeBtn;
	}
	
	public WebElement getAddSaveBtn() {
		return addSaveBtn;
	}
	
	public WebElement getNextPageButton() {
		return NextPageButton;
	}

	public WebElement getPreviousPageButton() {
		return PreviousPageButton;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

	public WebElement getEditBtn() {
		return editBtn;
	}

}
