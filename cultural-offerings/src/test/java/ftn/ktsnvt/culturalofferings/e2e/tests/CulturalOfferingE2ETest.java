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

public class CulturalOfferingE2ETest {
    private WebDriver driver;

    private SigninPage signinPage;

    private CulturalOfferingTypePage typePage;

    private HomepagePage homepage;

    private CulturalOfferingsTablePage culturalOfferingsPage;

    static {
        System.setProperty("java.awt.headless", "false");
    }

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
        synchronized (driver) {
            driver.wait(seconds * 1000);
        }
    }

    @Test
    public void AddNewCulturalOfferingSuccess() throws InterruptedException, AWTException {
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
        
        culturalOfferingsPage.getDodajNovuKulturnuPonuduBtn().click();

        culturalOfferingsPage.ensureIsDisplayedInput();

        culturalOfferingsPage.getNazivInput().sendKeys("Nova");
        culturalOfferingsPage.getTypeSelectInput().click();
        culturalOfferingsPage.getTypeOption().click();
        culturalOfferingsPage.getSubtypeSelectInput().click();
        culturalOfferingsPage.getSubtypeOption().click();
        culturalOfferingsPage.getLocationInput().sendKeys("Novi Sad");
        justWait(1);
        Point coordinates = culturalOfferingsPage.getLocationInput().getLocation();
        Robot robot = new Robot();
        System.out.println(coordinates);
        robot.mouseMove(coordinates.getX()+10,coordinates.getY()+120+40);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        justWait(1);
        culturalOfferingsPage.getDodajIzmeniBtn().click();

        for(int i = 0;i < 100; i++){
        	justWait(1);
        	if(typePage.getNextPageButton().isEnabled()) {
                typePage.getNextPageButton().click();
        	}
        	else {
        		break;
        	}
        }

        justWait(1);

        culturalOfferingsPage.getDeleteBtn().click();

        justWait(5);

    }
}
