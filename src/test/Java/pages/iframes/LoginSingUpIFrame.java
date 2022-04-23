package pages.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

public class LoginSingUpIFrame {

    private WebDriver driver;

    By usernameInput = By.cssSelector(".ng-pristine[type=\"email\"]");

    By passwordInput = By.cssSelector(".ng-pristine[type=\"password\"]");


    public LoginSingUpIFrame(WebDriver driver) {
        this.driver = driver;
    }

    @Parameters({"username"})
    public void setUsername (String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    @Parameters({"password"})
    public void setPassword (String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

//    public ESPNHomePage clickLogin
}
