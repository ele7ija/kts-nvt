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

public class AdminE2ETest {

    private WebDriver driver;

    private SigninPage signinPage;

    private AdminPage adminPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        adminPage = PageFactory.initElements(driver, AdminPage.class);
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
    public void AddAdminSuccess() throws InterruptedException {
        //sign in as super admin
        driver.get("http://localhost:4200/sign-in");

        justWait(2);

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("ana.ana@gmail.com");
        signinPage.getPassword().sendKeys("ana");
        signinPage.getSigninBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/super-admin/admins", driver.getCurrentUrl());

        justWait(2);

        //add new admin
        adminPage.getAddCancelBtn().click();

        adminPage.ensureIsDisplayedInput();

        adminPage.getEmail().sendKeys("zikiaw37@gmail.com");
        adminPage.getPassword().sendKeys("Zika");
        adminPage.getFirstName().sendKeys("Zika");
        adminPage.getLastName().sendKeys("Zikic");

        justWait(3);

        adminPage.getAddAdminBtn().click();

        justWait(5);

        //delete admin
        //get to the last page
        for(int i = 0;i < 100; i++){
            justWait(1);
            if(adminPage.getNextPageButton().isEnabled()) {
                adminPage.getNextPageButton().click();
            }
            else {
                break;
            }
        }

        //get button from last tr
        justWait(2);

        adminPage.getDeleteBtn().click();

        justWait(5);

    }

    //typed email exists
    @Test
    public void AddAdminFailed() throws InterruptedException {
        //sign in as super admin
        driver.get("http://localhost:4200/sign-in");

        justWait(2);

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("ana.ana@gmail.com");
        signinPage.getPassword().sendKeys("ana");
        signinPage.getSigninBtn().click();

        justWait(2);

        assertEquals("http://localhost:4200/super-admin/admins", driver.getCurrentUrl());

        justWait(2);

        //add new admin
        adminPage.getAddCancelBtn().click();

        adminPage.ensureIsDisplayedInput();

        adminPage.getEmail().sendKeys("milan.milan@gmail.com");
        adminPage.getPassword().sendKeys("Milan");
        adminPage.getFirstName().sendKeys("Zika");
        adminPage.getLastName().sendKeys("Zikic");

        justWait(2);

        adminPage.getAddAdminBtn().click();

        justWait(5);

    }

}
