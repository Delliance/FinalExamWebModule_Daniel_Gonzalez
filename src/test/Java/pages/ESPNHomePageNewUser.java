package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.iframes.LoginSingUpIFrame;

public class ESPNHomePageNewUser {

    private WebDriver driver;

    private Actions actions;

    private WebDriverWait wait;

    private By loginLink = By.cssSelector(".user li:nth-child(7) a");

    private By userMenuHover = By.id("global-user-trigger");

    private By logoutLink = By.cssSelector("#header-wrapper .account-management .small");

    private By myProfileLink = By.cssSelector("#header-wrapper .account-management li:nth-child(5) a");

    public ESPNHomePageNewUser(WebDriver driver) {
        this.driver =  driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public void hoverUserMenu() {
        actions.moveToElement(driver.findElement(userMenuHover)).build().perform();
    }

    public LoginSingUpIFrame clickLoginLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginLink));
        driver.findElement(loginLink).click();
        return new LoginSingUpIFrame(driver);
    }
}
