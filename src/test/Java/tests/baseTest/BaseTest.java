package tests.baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePageLoggedIn;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;

import static org.testng.Assert.*;

public class BaseTest {

    private WebDriver driver;
    protected HomePageStart homePageStart;

    protected HomePageLoggedIn homePageLoggedIn;

    @BeforeSuite(groups = {"logIn", "logOut", "cancelAccount"})
    @Parameters({"singUpFirstName", "singUpLastName", "singUpEmail", "singUpPassword"})
    public void createAccount(String firstName, String lastName, String email, String password){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        goHome();
        homePageStart = new HomePageStart(driver);
        driver.manage().window().maximize();
        // creating a new account for the whole suite
        UserMenuHoverStart hoverStart = homePageStart.hoverUserMenu();
        LoginSingUpIFrame singUpIFrame = hoverStart.clickLoginLink();
        singUpIFrame.clickSingUp();
        singUpIFrame.setSingUpFirstName(firstName);
        singUpIFrame.setSingUpLastName(lastName);
        singUpIFrame.setSingUpEmail(email);
        singUpIFrame.setSingUpPassword(password);
        HomePageLoggedIn homePageLoggedIn1 = singUpIFrame.clickConfirmSingUpButton();
        homePageLoggedIn1.getPageHeader();
        driver.quit();

    }

    @BeforeTest(groups = {"logIn", "logOut", "cancelAccount"})
    @Parameters({"browserF"}) //browserC = chrome, browserF = firefox, browserE = edge
    public void setUp(String browser) {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalStateException("That driver does not exist, check if it it was written correctly or install it");
        }

        goHome();
        System.out.println(driver.getTitle());
        homePageStart = new HomePageStart(driver);
        homePageLoggedIn = new HomePageLoggedIn(driver);
        driver.manage().window().maximize();
    }

    @BeforeMethod(groups = {"logIn", "logOut", "cancelAccount"})
//    @Parameters({"webPage"})
    public void goHome() {
        driver.get("https://www.espnqa.com/?src=com&_adblock=true&espn=cloud");
    }

    @BeforeMethod(groups = {"logOut", "cancelAccount"})
    public void login(String username, String password) {
        homePageStart.getPageHeader();
        homePageStart.isLeftLoginMenuVisible();
        UserMenuHoverStart menuHover = homePageStart.hoverUserMenu();
        menuHover.isMenuDisplayed();
        menuHover.getHeader();
        LoginSingUpIFrame singUpIFrame = menuHover.clickLoginLink();
        singUpIFrame.isTheIFrameActive();
        singUpIFrame.isIFrameLogoVisible();
        singUpIFrame.setUsername(username);
        singUpIFrame.setPassword(password);
        HomePageLoggedIn homePageLoggedIn1 = singUpIFrame.clickLogin();
        homePageLoggedIn1.getPageHeader();
//        assertFalse(homePageLoggedIn1.isLeftLoginMenuVisible(), "The login menu is visible, you're not logged in");
    }


    @AfterMethod(groups = {"logIn"})
    public void logOut() {
        homePageLoggedIn.getPageHeader();
        homePageLoggedIn.isLeftLoginMenuVisible();
        UserMenuHoverLoggedIn menuHoverLoggedIn =  homePageLoggedIn.hoverUserMenu();
        menuHoverLoggedIn.isMenuDisplayed();
        menuHoverLoggedIn.getHeader();
        HomePageStart homePageStart1 = menuHoverLoggedIn.clickLogOutLink();
        homePageStart1.getPageHeader();
    }

    @AfterTest(groups = {"logIn", "logOut", "cancelAccount"})
    public void quit() {
        driver.quit();
    }

}
