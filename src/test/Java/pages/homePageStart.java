package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.hovers.UserMenuHover;

public class homePageStart {

    private WebDriver driver;

    private Actions actions;

    private WebDriverWait wait;

    private By hoverTrigger = By.id("global-user-trigger");

    private By hoverMenu = By.cssSelector(".user .global-user");

    private By title = By.cssSelector(".container h1 a"); //check if it is ESPN

    private By loginMenuHeader = By.id("sideLogin-left-rail"); //check if it is visible

    public homePageStart(WebDriver driver) {
        this.driver =  driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public String getPageTitle() {
        return driver.findElement(title).getText();
    }

    public boolean isLeftLoginMenuVisible() {
        return driver.findElement(loginMenuHeader).isDisplayed();
    }

    public UserMenuHover hoverUserMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(hoverTrigger));
        WebElement trigger = driver.findElement(hoverTrigger);
        actions.moveToElement(trigger).build().perform();
        return new UserMenuHover(driver.findElement(hoverMenu), driver);
    }

}
