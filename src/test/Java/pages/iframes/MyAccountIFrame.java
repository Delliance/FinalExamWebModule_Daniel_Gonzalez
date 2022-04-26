package pages.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountIFrame {

    private WebDriver driver;

    private WebDriverWait wait;

    private Actions actions;

    private JavascriptExecutor js;

    private By cancelAccountLink = By.id("cancel-account");

    public MyAccountIFrame(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
    }

    public void ClickCancelAccount() {
        scrollDown();
//        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(cancelAccountLink));
        wait.until(ExpectedConditions.presenceOfElementLocated(cancelAccountLink));
        driver.findElement(cancelAccountLink).click();
    }

    private void scrollDown() {
        js.executeScript("arguments[0].scrollIntoView(true)", cancelAccountLink);
    }

}
