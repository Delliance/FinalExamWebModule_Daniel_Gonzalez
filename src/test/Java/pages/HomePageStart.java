package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.hovers.UserMenuHoverStart;

public class HomePageStart {

    private WebDriver driver;

    private Actions actions;

    private WebDriverWait wait;

    private By hoverTrigger = By.id("global-user-trigger");

    private By hoverMenu = By.cssSelector(".user .global-user");

    private By header = By.cssSelector(".container h1 a"); //check if it is ESPN

    private By leftLoginMenu = By.id("sideLogin-left-rail"); //check if it is visible

    public HomePageStart(WebDriver driver) {
        this.driver =  driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public String getPageHeader() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return  d.findElement(header).getText().length() != 0; //if it takes too long to charge the page, this text does not load, so I use this condition
            }
        });
        return driver.findElement(header).getText();
    }

    public boolean isLeftLoginMenuVisible() {
        wait.until((ExpectedConditions.presenceOfElementLocated(leftLoginMenu)));
        try {
            wait.until(ExpectedConditions.attributeToBe(leftLoginMenu, "style", "display: block;"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public UserMenuHoverStart hoverUserMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(hoverTrigger));
        WebElement trigger = driver.findElement(hoverTrigger);
        actions.moveToElement(trigger).build().perform();
        return new UserMenuHoverStart(driver.findElement(hoverMenu), driver);
    }

}
