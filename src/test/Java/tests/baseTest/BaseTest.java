package tests.baseTest;

import dataProviders.UserDataProvider;
import listeners.EventReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import pages.HomePageLoggedIn;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;

public class BaseTest {

    private EventFiringWebDriver driver;
    protected HomePageStart homePageStart;

    protected HomePageLoggedIn homePageLoggedIn;

    private UserDataProvider userDataProvider;

    public BaseTest() {
        this.userDataProvider = new UserDataProvider();
    }

    @BeforeSuite(groups = {"logIn", "logOut", "cancelAccount"})
    public void createAccount(){
        String firstName = userDataProvider.getFirstName();
        String lastName = userDataProvider.getLastName();
        String email = userDataProvider.getEmail();
        String password = userDataProvider.getPassword();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new EventFiringWebDriver(new FirefoxDriver());
        driver.register(new EventReporter());
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
    @Parameters({"browserC"}) //browserC = chrome, browserF = firefox, browserE = edge
    public void setUp(String browser) {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new EventFiringWebDriver(new ChromeDriver());
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new EventFiringWebDriver(new FirefoxDriver());
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                driver = new EventFiringWebDriver(new EdgeDriver());
                break;

            default:
                throw new IllegalStateException("That driver does not exist, check if it it was written correctly or install it");
        }

        driver.register(new EventReporter());
        goHome();
        System.out.println(driver.getTitle());
        homePageStart = new HomePageStart(driver);
        homePageLoggedIn = new HomePageLoggedIn(driver);
        driver.manage().window().maximize();
    }

    @BeforeMethod(groups = {"logIn", "logOut", "cancelAccount"})
    public void goHome() {
        driver.get("https://www.espnqa.com/?src=com&_adblock=true&espn=cloud");
    }

    @BeforeMethod(groups = {"logOut", "cancelAccount"})
    public void login() {
        String username = userDataProvider.getEmail();
        String password = userDataProvider.getPassword();
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
        homePageLoggedIn1.isLeftLoginMenuVisible();
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
