package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import ftn.ktsnvt.culturalofferings.e2e.pages.CulturalOfferingTypePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.CulturalOfferingsTablePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.HomepagePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.MyProfilePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class CulturalOfferingTypeE2ETest {

    private static String IMG_PATH = "D:\\Fakultet\\7sms-KTS\\kts-images\\ankete.PNG";

    private WebDriver driver;

    private SigninPage signinPage;
    
    private CulturalOfferingTypePage typePage;
    
    private HomepagePage homepage;
    
    private CulturalOfferingsTablePage culturalOfferingsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        typePage = PageFactory.initElements(driver, CulturalOfferingTypePage.class);
        homepage = PageFactory.initElements(driver, HomepagePage.class);
        culturalOfferingsPage = PageFactory.initElements(driver, CulturalOfferingsTablePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    private void justWait(int seconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(seconds * 1000);
        }
    }
    
    @Test
    public void AddNewTypeSuccess() throws InterruptedException {
    	//sign in as admin
    	driver.get("http://localhost:4200/sign-in");

        justWait(2);
        
        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("milan.milan@gmail.com");
        signinPage.getPassword().sendKeys("Milan");
        signinPage.getSigninBtn().click();
        
        justWait(2);

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());
        
        //chose options from dropdown
        homepage.getDropdownMenuBtn().click();
        
        justWait(2);
        
        homepage.getOptionsLink().click();
        
        justWait(2);
        
        assertEquals("http://localhost:4200/admin", driver.getCurrentUrl());
        
        //navigate to types table from admin dashboard
        culturalOfferingsPage.getTypesBtn().click();
        
        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering-type", driver.getCurrentUrl());
        
        //click Dodaj novi tip button to add new type
        typePage.getAddCancelBtn().click();
        
        typePage.ensureIsDisplayedInput();
        
        typePage.getTypeName().sendKeys("Novi tip kulturne ponude");
        typePage.getTypeMatChip().sendKeys("Tip 1");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);
        
        justWait(2);
        
        typePage.getRemoveSubtypeBtn().click();
        
        justWait(2);
        
        //typePage.getChoseFileBtn().sendKeys();
        
        typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 2");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);
        
        typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 3");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);
        
        justWait(2);
        
        typePage.getAddSaveBtn().click();
        
        justWait(5);
        
        //change 
        //get to the last page
        for(int i = 0;i < 100; i++){
        	justWait(1);
        	if(typePage.getNextPageButton().isEnabled()) {
                typePage.getNextPageButton().click();
        	}
        	else {
        		break;
        	}
        }

        //get button from last tr
        justWait(2);

        //typePage.ensureIsDisplayedEdit();
        typePage.getEditBtn().click();
        
        justWait(2);
        
        typePage.getRemoveSubtypeBtn().click();
        
        //typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 4");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);
        
        justWait(2);
        
        typePage.getAddSaveBtn().click();
        
        justWait(5);
        
        // delete   
        
        typePage.getDeleteBtn().click();

        justWait(5);

    }

    @Test
    public void AddNewTypeWithImageSuccess() throws InterruptedException {
        //sign in as admin
        driver.get("http://localhost:4200/sign-in");

        justWait(2);

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("milan.milan@gmail.com");
        signinPage.getPassword().sendKeys("Milan");
        signinPage.getSigninBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        //chose options from dropdown
        homepage.getDropdownMenuBtn().click();

        justWait(2);

        homepage.getOptionsLink().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin", driver.getCurrentUrl());

        //navigate to types table from admin dashboard
        culturalOfferingsPage.getTypesBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering-type", driver.getCurrentUrl());

        //click Dodaj novi tip button to add new type
        typePage.getAddCancelBtn().click();

        typePage.ensureIsDisplayedInput();

        typePage.getTypeName().sendKeys("Novi tip kulturne ponude");
        typePage.getTypeMatChip().sendKeys("Tip 1");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);

        justWait(2);

        typePage.getRemoveSubtypeBtn().click();

        justWait(2);

        typePage.getChoseFileBtn().sendKeys("D:\\Fakultet\\7sms-KTS\\kts-images\\ankete.PNG");

        typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 2");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);

        typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 3");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);

        justWait(5);

        typePage.getAddSaveBtn().click();

        justWait(5);

        //change
        //get to the last page
        for(int i = 0;i < 100; i++){
            justWait(1);
            if(typePage.getNextPageButton().isEnabled()) {
                typePage.getNextPageButton().click();
            }
            else {
                break;
            }
        }

        //get button from last tr
        justWait(2);

        //typePage.ensureIsDisplayedEdit();
        typePage.getEditBtn().click();

        justWait(5);

        typePage.getRemoveSubtypeBtn().click();

        //typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 4");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);

        typePage.getDeleteFileBtn().click();

        justWait(5);

        typePage.getAddSaveBtn().click();

        justWait(3);

        // delete

        typePage.getDeleteBtn().click();

        justWait(5);

    }

    //type name exists
    @Test
    public void AddNewTypeFailed() throws InterruptedException {
        //sign in as admin
        driver.get("http://localhost:4200/sign-in");

        justWait(2);

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("milan.milan@gmail.com");
        signinPage.getPassword().sendKeys("Milan");
        signinPage.getSigninBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        //chose options from dropdown
        homepage.getDropdownMenuBtn().click();

        justWait(2);

        homepage.getOptionsLink().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin", driver.getCurrentUrl());

        //navigate to types table from admin dashboard
        culturalOfferingsPage.getTypesBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering-type", driver.getCurrentUrl());

        //click Dodaj novi tip button to add new type
        typePage.getAddCancelBtn().click();

        typePage.ensureIsDisplayedInput();

        typePage.getTypeName().sendKeys("Manifestacija");

        justWait(2);

        //typePage.getChoseFileBtn().sendKeys();

        typePage.getTypeMatChip().clear();
        typePage.getTypeMatChip().sendKeys("Tip 2");
        typePage.getTypeMatChip().sendKeys(Keys.ENTER);

        justWait(2);

        typePage.getAddSaveBtn().click();

        justWait(5);

    }
	
}
