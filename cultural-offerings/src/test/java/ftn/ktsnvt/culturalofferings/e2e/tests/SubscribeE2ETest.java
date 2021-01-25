package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ftn.ktsnvt.culturalofferings.e2e.pages.CulturalOfferingPage;
import ftn.ktsnvt.culturalofferings.e2e.pages.HomepagePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SubscribeE2ETest {
    private WebDriver driver;

    private HomepagePage homepagePage;

    private SigninPage signinPage;

    private CulturalOfferingPage culturalOfferingPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        homepagePage = PageFactory.initElements(driver, HomepagePage.class);
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        culturalOfferingPage = PageFactory.initElements(driver, CulturalOfferingPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SubscribeTestSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/sign-in");

        justWait();
        
        signinPage.ensureIsDisplayedEmail();

        signinPage.getEmail().sendKeys("mika.mika@gmail.com");
        signinPage.getPassword().sendKeys("Mika");
        signinPage.getSigninBtn().click();
        
        justWait();

        assertEquals("http://localhost:4200/homepage", driver.getCurrentUrl());

        driver.get("http://localhost:4200/cultural-offering/10");

        justWait();

        assertEquals("http://localhost:4200/cultural-offering/10", driver.getCurrentUrl());

        justWait(3000);

        if (culturalOfferingPage.isUnsubscribePresent()) {
            culturalOfferingPage.getUnsubscribeButton().click();
            culturalOfferingPage.ensureIsSubscribable();
            culturalOfferingPage.getSubscribeButton().click();
            culturalOfferingPage.ensureIsUnsubscribable();
        }
        else {
            culturalOfferingPage.getSubscribeButton().click();
            culturalOfferingPage.ensureIsUnsubscribable();
            culturalOfferingPage.getUnsubscribeButton().click();
            culturalOfferingPage.ensureIsSubscribable();
        }

        assertEquals("http://localhost:4200/cultural-offering/10", driver.getCurrentUrl());

    }

    private void justWait(int millis) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(millis);
        }
    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(2000);
        }
    }
}
