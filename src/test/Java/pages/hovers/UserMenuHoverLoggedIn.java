package pages.hovers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageStart;
import pages.iframes.LoginSingUpIFrame;

public class UserMenuHoverLoggedIn {
    private WebElement menu;

    private WebDriver driver;

    private WebDriverWait wait;

    private By header = By.cssSelector(".display-user");

    private By logOutLink = By.cssSelector(".global-user-container li:nth-child(9) a");

    private By myProfileLink = By.cssSelector(".global-user-container li:nth-child(5) a");

    private String myProfileIFrameId = "disneyid-iframe";

    public UserMenuHoverLoggedIn(WebElement menu, WebDriver driver) {
        this.menu = menu;
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public boolean isMenuDisplayed() {
        return menu.isDisplayed();
    }

    public String getHeader() {
        return menu.findElement(header).getText();
    }

    public HomePageStart clickLogOutLink() {
        wait.until(ExpectedConditions.visibilityOf(menu));
        menu.findElement(logOutLink).click();
        return new HomePageStart(driver);
    }

    private void switchToMyProfileIFrame() {
        driver.switchTo().frame(myProfileIFrameId);
    }
}
