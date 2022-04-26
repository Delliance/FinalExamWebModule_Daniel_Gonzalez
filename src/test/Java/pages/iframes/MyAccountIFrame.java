package pages.iframes;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountIFrame {

    private WebDriver driver;

    private WebDriverWait wait;

    private By activatedIFrame = By.cssSelector("#disneyid-wrapper[style='display: block;']");

    private By cancelAccountLink = By.id("cancel-account");

    private By confirmCancelAccountButton = By.id(".main .btn-group button:first-child");

    private By iFrameId = By.id("disneyid-iframe");

    public MyAccountIFrame(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public boolean isTheIFrameActive() {
        driver.switchTo().parentFrame();
        wait.until(ExpectedConditions.presenceOfElementLocated(activatedIFrame));
        boolean isIFrameActive;
        try {
            driver.findElement(activatedIFrame);
            isIFrameActive = true;
        }
        catch (NoSuchElementException e){
            isIFrameActive = false;
        }
        driver.switchTo().frame(driver.findElement(iFrameId));
        return isIFrameActive;
    }

    public void clickCancelAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelAccountLink));
        try{
            driver.findElement(cancelAccountLink).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e){
            WebElement cancelLink = driver.findElement(By.id("cancel-account"));
            cancelLink.click();
        }
    }

    public void clickConfirmCancelAccount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmCancelAccountButton));
        driver.findElement(confirmCancelAccountButton).click();
    }

}
