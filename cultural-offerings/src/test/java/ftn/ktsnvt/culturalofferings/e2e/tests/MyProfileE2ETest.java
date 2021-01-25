package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ftn.ktsnvt.culturalofferings.e2e.pages.MyProfilePage;
import ftn.ktsnvt.culturalofferings.e2e.pages.RegisterPage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class MyProfileE2ETest {
	
	private WebDriver driver;

    private SigninPage signinPage;
    
    private MyProfilePage myProfilePage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        signinPage = PageFactory.initElements(driver, SigninPage.class);
        myProfilePage = PageFactory.initElements(driver, MyProfilePage.class);
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
        
        //chose
        

    }
    
    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(2000);
        }
    }

}
