package tests.baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePageLoggedIn;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;

import static org.testng.Assert.*;

public class BaseTest {

    private WebDriver driver;
    protected HomePageStart homePageStart;

    protected HomePageLoggedIn homePageLoggedIn;

    @BeforeSuite
    @Parameters({"singUpFirstName", "singUpLastName", "singUpEmail", "singUpPassword"})
    public void createAccount(String firstName, String lastName, String email, String password){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
        singUpIFrame.clickConfirmSingUpButton();
        driver.quit();

    }

    @BeforeClass
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
//        homePageLoggedIn = new HomePageLoggedIn(driver);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goHome() {
        driver.get("https://www.espnqa.com/?src=com&_adblock=true&espn=cloud");
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
