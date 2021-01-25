package ftn.ktsnvt.culturalofferings.e2e.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ftn.ktsnvt.culturalofferings.e2e.pages.RegisterPage;
import ftn.ktsnvt.culturalofferings.e2e.pages.SigninPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterE2ETest {

	private WebDriver driver;

    private RegisterPage registerPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SigninTestSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/register");

        justWait();
        
        registerPage.ensureIsDisplayedEmail();

        registerPage.getNameField().sendKeys("Nemanja");
        registerPage.getSurnameField().sendKeys("Mikic");
        registerPage.getEmailField().sendKeys("nemanja.mikic@gmail.com");
        registerPage.getPassField().sendKeys("Nemanja1");
        registerPage.getConfPassField().sendKeys("Nemanja1");
        registerPage.getRegBtn().click();

        registerPage.ensureIsNotVisibleRegBtn();
        registerPage.ensureIsNotVisibleEmail();
        
        justWait();

        assertEquals("http://localhost:4200/register", driver.getCurrentUrl());

    }
    
    //typed email exists
    @Test
    public void SigninTestFailed() throws InterruptedException {

        driver.get("http://localhost:4200/register");

        justWait();
        
        registerPage.ensureIsDisplayedEmail();

        registerPage.getNameField().sendKeys("Ana");
        registerPage.getSurnameField().sendKeys("Anic");
        registerPage.getEmailField().sendKeys("ana.ana@gmail.com");
        registerPage.getPassField().sendKeys("ana");
        registerPage.getConfPassField().sendKeys("ana");
        registerPage.getRegBtn().click();

        registerPage.ensureIsDisplayedEmail();
        
        justWait();

        assertEquals("http://localhost:4200/register", driver.getCurrentUrl());

    }
    
    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(2000);
        }
    }
	
}
