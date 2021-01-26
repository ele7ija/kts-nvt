package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CulturalOfferingsTablePage {

	private WebDriver driver;
    
	@FindBy(xpath = "//*[@id=\"manageTypesBtn\"]")
    WebElement typesBtn;
    
	@FindBy(xpath = "//*[@id=\"manageSubtypesBtn\"]")
    WebElement subtypesBtn;
    
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

	
	
}
