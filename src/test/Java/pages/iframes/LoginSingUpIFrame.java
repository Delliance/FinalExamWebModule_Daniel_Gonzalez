package pages.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageLoggedIn;

public class LoginSingUpIFrame {

    private WebDriver driver;

    private WebDriverWait wait;

    private By LoginUsernameInput = By.cssSelector(".ng-pristine[type=\"email\"]");

    private By LoginPasswordInput = By.cssSelector(".ng-pristine[type=\"password\"]");

    private By loginButton = By.cssSelector("button[type=\"submit\"]");

    private By singUpButton = By.cssSelector("a.btn");

    private By singUpFirstNameInput = By.name("firstName");

    private By singUpLastNameInput = By.name("lastName");

    private By singUpEmailInput = By.name("email");

    private By singUpPasswordInput = By.name("newPassword");

    private By confirmSingUpButton = By.cssSelector("button[type=\"submit\"]"); //yes it is the exact same as the login button

    private By logo = By.cssSelector("h1.logo");

    private By activatedIFrame = By.cssSelector("body[class=\"recaptcha-activated\"]");

    private By passwordHiddenMessage = By.cssSelector("[class=\"password-rules message\"]");


    public LoginSingUpIFrame(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void setUsername (String username) {
        wait.until(ExpectedConditions.presenceOfElementLocated(LoginUsernameInput));
        driver.findElement(LoginUsernameInput).sendKeys(username);
    }

    public void setPassword (String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(LoginPasswordInput));
        driver.findElement(LoginPasswordInput).sendKeys(password);
    }

    public HomePageLoggedIn clickLogin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        switchToMainArea();
        return new HomePageLoggedIn(driver);
    }

    public void clickSingUp() {
        wait.until(ExpectedConditions.presenceOfElementLocated(singUpButton));
        driver.findElement(singUpButton).click();
    }

    public void setSingUpFirstName(String firstName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(singUpFirstNameInput));
        driver.findElement(singUpFirstNameInput).sendKeys(firstName);
    }

    public void setSingUpLastName(String lastName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(singUpLastNameInput));
        driver.findElement(singUpLastNameInput).sendKeys(lastName);
    }

    public void setSingUpEmail(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(singUpEmailInput));
        driver.findElement(singUpEmailInput).sendKeys(email);
    }

    public void setSingUpPassword(String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(singUpPasswordInput));
        driver.findElement(singUpPasswordInput).sendKeys(password);
    }

    public HomePageLoggedIn clickConfirmSingUpButton() {
//        NOTE: for some reason this method sometimes work and other times not, I don't know the reason
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmSingUpButton));
        driver.findElement(confirmSingUpButton).click();
        switchToMainArea();
        return new HomePageLoggedIn(driver);
    }

    public boolean isTheIFrameActive() {
        try {
            driver.findElement(activatedIFrame);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isIFrameLogoVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(logo));
        return driver.findElement(logo).isDisplayed();
    }

    private void switchToMainArea() {
        driver.switchTo().parentFrame();
    }

}
