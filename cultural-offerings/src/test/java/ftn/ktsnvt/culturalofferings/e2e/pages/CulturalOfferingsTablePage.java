package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalOfferingsTablePage {

	private WebDriver driver;
    
	@FindBy(xpath = "//*[@id=\"manageTypesBtn\"]")
    WebElement typesBtn;
    
	@FindBy(xpath = "//*[@id=\"manageSubtypesBtn\"]")
	WebElement subtypesBtn;
	
	@FindBy(xpath = "//*[@id=\"dodajNovuKulturnuPonudu\"]")
    WebElement dodajNovuKulturnuPonuduBtn;

    @FindBy(xpath = "//*[@id=\"name\"]")
    WebElement nazivInput;

    @FindBy(xpath = "//*[@id=\"culturalOfferingTypeName\"]")
    WebElement typeSelectInput;

    @FindBy(xpath = "//*[@id=\"typeOption10\"]")
    WebElement typeOption;

    @FindBy(xpath = "//*[@id=\"culturalOfferingSubtypeName\"]")
    WebElement subtypeSelectInput;

    @FindBy(xpath = "//*[@id=\"subtypeOption10\"]")
    WebElement subtypeOption;

    @FindBy(css = "app-google-autocomplete input")
    WebElement locationInput;

    @FindBy(css = "app-google-autocomplete option")
	WebElement noviSadOption;
	
	@FindBy(id = "dodajIzmeni")
	WebElement dodajIzmeniBtn;
	
	@FindBy(xpath = "//*[@aria-label=\"Next page\"]")
    private WebElement NextPageButton;

    @FindBy(xpath = "//*[@aria-label=\"Previous page\"]")
	private WebElement PreviousPageButton;

	//@FindBy(css = ".deleteBtn:last-of-type")
	@FindBy(xpath = "//table/tbody/tr[position() = (last()-1)]/td[3]/mat-icon[2]")
    private WebElement deleteBtn;

	@FindBy(xpath = "//table/tbody/tr[5]/td[3]/a[2]/mat-icon")
	private WebElement newsletterBtn;

    public CulturalOfferingsTablePage() {
    }

    public CulturalOfferingsTablePage(WebDriver driver) {
        this.driver = driver;
    }

	public WebElement getTypesBtn() {
		return typesBtn;
	}

	public WebElement getSubtypesBtn() {
		return subtypesBtn;
	}


	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public void setTypesBtn(WebElement typesBtn) {
		this.typesBtn = typesBtn;
	}
	public void setSubtypesBtn(WebElement subtypesBtn) {
		this.subtypesBtn = subtypesBtn;
	}

	public WebElement getDodajNovuKulturnuPonuduBtn() {
		return this.dodajNovuKulturnuPonuduBtn;
	}

	public void setDodajNovuKulturnuPonuduBtn(WebElement dodajNovuKulturnuPonuduBtn) {
		this.dodajNovuKulturnuPonuduBtn = dodajNovuKulturnuPonuduBtn;
	}

	public WebElement getNazivInput() {
		return this.nazivInput;
	}

	public void setNazivInput(WebElement nazivInput) {
		this.nazivInput = nazivInput;
	}

	public WebElement getTypeSelectInput() {
		return this.typeSelectInput;
	}

	public void setTypeSelectInput(WebElement typeSelectInput) {
		this.typeSelectInput = typeSelectInput;
	}

	public WebElement getTypeOption() {
		return this.typeOption;
	}

	public void setTypeOption(WebElement typeOption) {
		this.typeOption = typeOption;
	}

	public WebElement getSubtypeSelectInput() {
		return this.subtypeSelectInput;
	}

	public void setSubtypeSelectInput(WebElement subtypeSelectInput) {
		this.subtypeSelectInput = subtypeSelectInput;
	}

	public WebElement getSubtypeOption() {
		return this.subtypeOption;
	}

	public void setSubtypeOption(WebElement subtypeOption) {
		this.subtypeOption = subtypeOption;
	}

	public WebElement getLocationInput() {
		return this.locationInput;
	}

	public void setLocationInput(WebElement locationInput) {
		this.locationInput = locationInput;
	}

	public WebElement getNoviSadOption() {
		return this.noviSadOption;
	}

	public void setNoviSadOption(WebElement noviSadOption) {
		this.noviSadOption = noviSadOption;
	}

	public void ensureIsDisplayedInput() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("dodajNovuKulturnuPonudu")));
	}
	

	public WebElement getDodajIzmeniBtn() {
		return this.dodajIzmeniBtn;
	}

	public void setDodajIzmeniBtn(WebElement dodajIzmeniBtn) {
		this.dodajIzmeniBtn = dodajIzmeniBtn;
	}


	public WebElement getNextPageButton() {
		return this.NextPageButton;
	}

	public void setNextPageButton(WebElement NextPageButton) {
		this.NextPageButton = NextPageButton;
	}

	public WebElement getPreviousPageButton() {
		return this.PreviousPageButton;
	}

	public void setPreviousPageButton(WebElement PreviousPageButton) {
		this.PreviousPageButton = PreviousPageButton;
	}

	public WebElement getDeleteBtn() {
		return this.deleteBtn;
	}

	public void setDeleteBtn(WebElement deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public WebElement getNewsletterBtn() { return this.newsletterBtn; }

}
