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

public class NewsE2ETest {

    private static String IMG_PATH = "D:\\Fakultet\\7sms-KTS\\kts-images\\spring_dependencies.PNG";

    private WebDriver driver;

    private SigninPage signinPage;

    private NewsPage newsPage;

    private HomepagePage homepage;

    private CulturalOfferingsTablePage culturalOfferingsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        newsPage = PageFactory.initElements(driver, NewsPage.class);
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
    public void AddNewsSuccess() throws InterruptedException {
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

        //chose newsletter button
        culturalOfferingsPage.getNewsletterBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering/12/news", driver.getCurrentUrl());

        //click Dodaj novi tip button to add new type
        newsPage.getAddCancelBtn().click();

        newsPage.ensureIsDisplayedInput();

        newsPage.getTitle().sendKeys("Nova vest za spomenik");
        newsPage.getEmailText().sendKeys("Ovo je tekst mejla.");

        justWait(2);

        newsPage.getAddSaveBtn().click();

        justWait(5);

        //change
        //get to the last page
        for(int i = 0;i < 100; i++){
            justWait(1);
            if(newsPage.getNextPageButton().isEnabled()) {
                newsPage.getNextPageButton().click();
            }
            else {
                break;
            }
        }

        //get button from last tr
        justWait(2);

        //typePage.ensureIsDisplayedEdit();
        newsPage.getEditBtn().click();

        justWait(2);

        newsPage.getTitle().clear();
        newsPage.getTitle().sendKeys("Izmenjena vest za spomenik");
        newsPage.getEmailText().sendKeys(" Dodatak na tekst...");

        justWait(5);

        newsPage.getAddSaveBtn().click();

        justWait(5);

        // delete

        newsPage.getDeleteBtn().click();

        justWait(5);

    }

    @Test
    public void AddNewsWithPicturesSuccess() throws InterruptedException {
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

        //chose newsletter button
        culturalOfferingsPage.getNewsletterBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/admin/cultural-offering/12/news", driver.getCurrentUrl());

        //click Dodaj novi tip button to add new type
        newsPage.getAddCancelBtn().click();

        newsPage.ensureIsDisplayedInput();

        newsPage.getChoseFileBtn().sendKeys(IMG_PATH);

        newsPage.getTitle().sendKeys("Nova vest za spomenik");
        newsPage.getEmailText().sendKeys("Ovo je tekst mejla.");

        justWait(2);

        newsPage.getAddSaveBtn().click();

        justWait(10);

        //change
        //get to the last page
        for(int i = 0;i < 100; i++){
            justWait(1);
            if(newsPage.getNextPageButton().isEnabled()) {
                newsPage.getNextPageButton().click();
            }
            else {
                break;
            }
        }

        //get button from last tr
        justWait(2);

        //typePage.ensureIsDisplayedEdit();
        newsPage.getEditBtn().click();

        justWait(5);

        newsPage.getDeleteFileBtn().click();

        newsPage.getTitle().clear();
        newsPage.getTitle().sendKeys("Izmenjena vest za spomenik");
        newsPage.getEmailText().sendKeys(" Dodatak na tekst...");

        justWait(2);

        newsPage.getAddSaveBtn().click();

        justWait(10);

        // delete

        newsPage.getDeleteBtn().click();

        justWait(5);

    }

}
