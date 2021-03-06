package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.hovers.UserMenuHoverLoggedIn;

public class HomePageLoggedIn {

    private WebDriver driver;

    private Actions actions;

    private WebDriverWait wait;

    private By hoverTrigger = By.id("global-user-trigger");

    private By hoverMenu = By.cssSelector(".user .global-user");

    private By header = By.cssSelector(".container h1 a"); //check if it is ESPN

    private By leftLoginMenu = By.id("sideLogin-left-rail"); //check if it is not visible, logged in this menu is there but not visible

    private By loginIframe = By.cssSelector("#disneyid-wrapper");

    public HomePageLoggedIn(WebDriver driver) {
        this.driver =  driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public String getPageHeader() {

        wait.until(ExpectedConditions.attributeToBe(leftLoginMenu, "style", "display: none;"));
        wait.until((ExpectedCondition<Boolean>) d -> {
            return  d.findElement(header).getText().length() != 0; //if it takes too long to charge the page, this text does not load, so I use this condition
        });
        return driver.findElement(header).getText();
    }

    public boolean isLeftLoginMenuVisible() {
        wait.until((ExpectedConditions.presenceOfElementLocated(leftLoginMenu)));
        try {
            wait.until(ExpectedConditions.attributeToBe(leftLoginMenu, "style", "display: none;"));
            return false;
        }
        catch (TimeoutException e){
            return true;
        }
    }

    public UserMenuHoverLoggedIn hoverUserMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(hoverTrigger));
        WebElement trigger = driver.findElement(hoverTrigger);
        actions.moveToElement(trigger).build().perform();
        return new UserMenuHoverLoggedIn(driver.findElement(hoverMenu), driver);
    }


}
