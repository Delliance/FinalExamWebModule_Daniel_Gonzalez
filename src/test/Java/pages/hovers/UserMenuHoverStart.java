package pages.hovers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.iframes.LoginSingUpIFrame;

public class UserMenuHoverStart {
    private WebElement menu;

    private WebDriver driver;

    private WebDriverWait wait;

    private By header = By.cssSelector(".display-user");

    private By loginLink = By.cssSelector(".global-user-container li:nth-child(7) a");

    private String loginIFrameId = "disneyid-iframe";

    public UserMenuHoverStart(WebElement menu, WebDriver driver) {
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

    public LoginSingUpIFrame clickLoginLink() {
        wait.until(ExpectedConditions.visibilityOf(menu));
        menu.findElement(loginLink).click();
        switchToLoginIFrame();
        return new LoginSingUpIFrame(driver);
    }

    private void switchToLoginIFrame() {
        driver.switchTo().frame(loginIFrameId);
    }
}
