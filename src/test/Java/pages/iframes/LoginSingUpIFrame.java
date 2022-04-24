package pages.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import pages.ESPNHomePageLoggedIn;

public class LoginSingUpIFrame {

    private WebDriver driver;

    By usernameInput = By.cssSelector(".ng-pristine[type=\"email\"]");

    By passwordInput = By.cssSelector(".ng-pristine[type=\"password\"]");

    By loginButton = By.cssSelector(".ng-pristine .btn-group button");


    public LoginSingUpIFrame(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername (String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void setPassword (String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public ESPNHomePageLoggedIn clickLogin() {
        driver.findElement(loginButton);
        return new ESPNHomePageLoggedIn(driver);
    }
}
