package ftn.ktsnvt.culturalofferings.e2e.tests;

import ftn.ktsnvt.culturalofferings.e2e.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class CulturalOfferingSubtypeE2ETest {

    private WebDriver driver;

    private SigninPage signinPage;

    private CulturalOfferingSubtypePage subtypePage;

    private HomepagePage homepage;

    private CulturalOfferingsTablePage culturalOfferingsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        subtypePage = PageFactory.initElements(driver, CulturalOfferingSubtypePage.class);
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
    public void AddNewSubtypeSuccess() throws InterruptedException {
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
        culturalOfferingsPage.getSubtypesBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering-sub-type", driver.getCurrentUrl());

        //click Dodaj novi tip button to add new type
        subtypePage.getAddCancelBtn().click();

        subtypePage.ensureIsDisplayedInput();

        subtypePage.getSubtypeName().sendKeys("Novi tip 1");
        subtypePage.getTypeSelectInput().click();
        subtypePage.getTypeOption().click();

        justWait(2);

        subtypePage.getAddSaveBtn().click();

        justWait(5);

        //change
        //get to the last page
        for(int i = 0;i < 100; i++){
            justWait(1);
            if(subtypePage.getNextPageButton().isEnabled()) {
                subtypePage.getNextPageButton().click();
            }
            else {
                break;
            }
        }

        //get button from last tr
        justWait(2);

        //typePage.ensureIsDisplayedEdit();
        subtypePage.getEditBtn().click();

        justWait(2);

        subtypePage.getSubtypeName().clear();
        subtypePage.getSubtypeName().sendKeys("Novi tip 1 izmenjen");
        subtypePage.getTypeSelectInput().click();
        subtypePage.getChangeTypeOption().click();

        justWait(2);

        subtypePage.getAddSaveBtn().click();

        justWait(5);

        // delete

        subtypePage.getDeleteBtn().click();

        justWait(5);

    }

}
