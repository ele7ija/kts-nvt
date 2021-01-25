package ftn.ktsnvt.culturalofferings.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalOfferingPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"subscribeButton\"]")
    private WebElement subscribeButton;

    @FindBy(xpath = "//*[@id=\"unsubscribeButton\"]")
    private WebElement unsubscribeButton;

    @FindBy(id = "comment")
    private WebElement commentField;

    @FindBy(css = ".my-icon-add")
    private WebElement addComment;

    @FindBy(css = ".my-icon-danger")
    private WebElement deleteComment;


    public CulturalOfferingPage() {
    }


    public CulturalOfferingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsSubscribable() {
    	//wait 30s to ensure page is loaded
        // (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("marker10")));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"subscribeButton\"]")));
    }

    public void ensureIsUnsubscribable() {
    	//wait 30s to ensure page is loaded
        // (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("marker10")));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"unsubscribeButton\"]")));
    }

    public boolean isUnsubscribePresent() {
    	//wait 30s to ensure page is loaded
        // (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("marker10")));
        if (driver.findElements( By.id("unsubscribeButton") ).size() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void ensureIsCommentable() {
    	//wait 30s to ensure page is loaded
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("comment")));
    }

    public void ensureIsDeletable() {
    	//wait 30s to ensure page is loaded
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".my-icon-danger")));
    }

    public boolean isDeletable() {
    	//wait 30s to ensure page is loaded
        // (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("marker10")));
        if (driver.findElements( By.cssSelector(".my-icon-danger") ).size() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getSubscribeButton() {
        return this.subscribeButton;
    }

    public void setSubscribeButton(WebElement subscribeButton) {
        this.subscribeButton = subscribeButton;
    }

    public WebElement getUnsubscribeButton() {
        return this.unsubscribeButton;
    }

    public void setUnsubscribeButton(WebElement unsubscribeButton) {
        this.unsubscribeButton = unsubscribeButton;
    }


    public WebElement getCommentField() {
        return this.commentField;
    }

    public void setCommentField(WebElement commentField) {
        this.commentField = commentField;
    }

    public WebElement getAddComment() {
        return this.addComment;
    }

    public void setAddComment(WebElement addComment) {
        this.addComment = addComment;
    }

    public WebElement getDeleteComment() {
        return this.deleteComment;
    }

    public void setDeleteComment(WebElement deleteComment) {
        this.deleteComment = deleteComment;
    }

    
}
