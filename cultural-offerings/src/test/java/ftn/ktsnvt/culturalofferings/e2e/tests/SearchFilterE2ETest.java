package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import ftn.ktsnvt.culturalofferings.e2e.pages.CulturalOfferingTypePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.CulturalOfferingsTablePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.HomepagePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.MyProfilePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchFilterE2ETest {
    private WebDriver driver;

    private SigninPage signinPage;


    private HomepagePage homepage;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        homepage = PageFactory.initElements(driver, HomepagePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void justWait(int seconds) throws InterruptedException {
        synchronized (driver) {
            driver.wait(seconds * 1000);
        }
    }


    @Test
    public void SearchFilter() throws InterruptedException, AWTException {
        //sign in as user
    	driver.get("http://localhost:4200/sign-in");

        justWait(2);
        
        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("mika.mika@gmail.com");
        signinPage.getPassword().sendKeys("Mika");
        signinPage.getSigninBtn().click();
        
        justWait(2);

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());
        justWait(3);

        assertEquals(1, driver.findElements(By.id("marker10")).size());
        assertEquals(1, driver.findElements(By.id("marker11")).size());

        homepage.getTermField().sendKeys("1936");
        homepage.getPrimeniBtn().click();

        justWait(3);

        assertEquals(1, driver.findElements(By.id("marker10")).size());
        assertEquals(0, driver.findElements(By.id("marker11")).size());
    }
}
