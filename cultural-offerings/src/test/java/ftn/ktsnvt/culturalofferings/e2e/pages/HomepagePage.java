package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomepagePage {
	
	private WebDriver driver;

    @FindBy(xpath="//div[2]/map/area")
    private WebElement culturalOfferingMarker;

    @FindBy(xpath = "//*[@id=\"termField\"]")
    private WebElement termField;

    @FindBy(xpath = "//*[@id=\"subtypeField10\"]")
    private WebElement subtypeField;

    @FindBy(xpath = "//*[@id=\"typeField10\"]")
    private WebElement typeField;
    
    @FindBy(xpath = "//*[@id=\"dropdownMenuBtn\"]")
    private WebElement dropdownMenuBtn;

    public HomepagePage() {
    }


    public HomepagePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedMarker() {
    	//wait 30s to ensure page is loaded
        // (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("marker10")));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/map/area")));
    }


    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCulturalOfferingMarker() {
        return this.culturalOfferingMarker;
    }

    public void setCulturalOfferingMarker(WebElement culturalOfferingMarker) {
        this.culturalOfferingMarker = culturalOfferingMarker;
    }

    public WebElement getTermField() {
        return this.termField;
    }

    public void setTermField(WebElement termField) {
        this.termField = termField;
    }

    public WebElement getSubtypeField() {
        return this.subtypeField;
    }

    public void setSubtypeField(WebElement subtypeField) {
        this.subtypeField = subtypeField;
    }

    public WebElement getTypeField() {
        return this.typeField;
    }

    public void setTypeField(WebElement typeField) {
        this.typeField = typeField;
    }
    
    public WebElement getDropdownMenuBtn() {
        return this.dropdownMenuBtn;
    }


}
