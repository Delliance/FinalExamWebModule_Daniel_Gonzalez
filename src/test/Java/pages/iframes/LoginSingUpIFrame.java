package pages.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageLoggedIn;

public class LoginSingUpIFrame {

    private WebDriver driver;

    private WebDriverWait wait;

    By usernameInput = By.cssSelector(".ng-pristine[type=\"email\"]");

    By passwordInput = By.cssSelector(".ng-pristine[type=\"password\"]");

    By loginButton = By.cssSelector("button[type=\"submit\"]");


    public LoginSingUpIFrame(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void setUsername (String username) {
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void setPassword (String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public HomePageLoggedIn clickLogin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        return new HomePageLoggedIn(driver);
    }
}
