package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import ftn.ktsnvt.culturalofferings.e2e.pages.HomepagePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.MyProfilePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class MyProfileE2ETest {

    private WebDriver driver;

    private SigninPage signinPage;

    private MyProfilePage myProfilePage;

    private HomepagePage homepage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        myProfilePage = PageFactory.initElements(driver, MyProfilePage.class);
        homepage = PageFactory.initElements(driver, HomepagePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void DataChangeSuccess() throws InterruptedException {

        //sign in
        driver.get("http://localhost:4200/sign-in");

        justWait();

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("mika.mika@gmail.com");
        signinPage.getPassword().sendKeys("Mika");
        signinPage.getSigninBtn().click();

        justWait();

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        //chose my profile from dropdown
        homepage.getDropdownMenuBtn().click();

        justWait();

        homepage.getMyProfileLink().click();

        justWait();

        assertEquals("http://localhost:4200/my-profile", driver.getCurrentUrl());

        //change user data
        assertEquals("Mika", myProfilePage.getFirstName().getAttribute("value"));
        myProfilePage.getFirstName().clear();
        myProfilePage.getFirstName().sendKeys("Anka");
        //just click anywhere to enable button
        myProfilePage.getLastName().click();
        myProfilePage.getDataBtn().click();

        justWait();
        assertEquals(true, myProfilePage.getDataMsgSuccess().isDisplayed());

        //Revert changes
        myProfilePage.getFirstName().clear();
        myProfilePage.getFirstName().sendKeys("Mika");
        myProfilePage.getLastName().click();
        myProfilePage.getDataBtn().click();
        justWait();
    }

    @Test
    public void PasswordChangeSuccess() throws InterruptedException {

        //sign in
        driver.get("http://localhost:4200/sign-in");

        justWait();

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("mika.mika@gmail.com");
        signinPage.getPassword().sendKeys("Mika");
        signinPage.getSigninBtn().click();

        justWait();

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        //chose my profile from dropdown
        homepage.getDropdownMenuBtn().click();

        justWait();

        homepage.getMyProfileLink().click();

        justWait();

        assertEquals("http://localhost:4200/my-profile", driver.getCurrentUrl());

        //change password
        myProfilePage.getOldPassField().sendKeys("Mika");
        myProfilePage.getPassField().sendKeys("123");
        myProfilePage.getConfPassField().sendKeys("123");
        myProfilePage.getPassBtn().click();

        justWait();
        assertEquals(true, myProfilePage.getPassMsgSuccess().isDisplayed());

        //Revert changes
        myProfilePage.getOldPassField().clear();
        myProfilePage.getPassField().clear();
        myProfilePage.getConfPassField().clear();
        myProfilePage.getOldPassField().sendKeys("123");
        myProfilePage.getPassField().sendKeys("Mika");
        myProfilePage.getConfPassField().sendKeys("Mika");
        myProfilePage.getPassBtn().click();
        justWait();
    }

    //wrong current (old) password typed
    @Test
    public void PasswordChangeFailed() throws InterruptedException {

        //sign in
        driver.get("http://localhost:4200/sign-in");

        justWait();

        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("mika.mika@gmail.com");
        signinPage.getPassword().sendKeys("Mika");
        signinPage.getSigninBtn().click();

        justWait();

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        //chose my profile from dropdown
        homepage.getDropdownMenuBtn().click();

        justWait();

        homepage.getMyProfileLink().click();

        justWait();

        assertEquals("http://localhost:4200/my-profile", driver.getCurrentUrl());

        //change password
        myProfilePage.getOldPassField().sendKeys("123");
        myProfilePage.getPassField().sendKeys("123");
        myProfilePage.getConfPassField().sendKeys("123");
        myProfilePage.getPassBtn().click();

        justWait();
        assertEquals(true, myProfilePage.getPassMsgError().isDisplayed());
    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(2000);
        }
    }

}
