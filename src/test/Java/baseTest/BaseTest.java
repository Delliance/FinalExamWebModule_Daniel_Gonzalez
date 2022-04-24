package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.homePageStart;
import static org.testng.Assert.*;

public class BaseTest {

    private WebDriver driver;
    protected homePageStart homePageStart;

    @BeforeClass
    @Parameters({"browserC"}) //browserC = chrome, browserF = firefox, browserE = edge
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

        driver.get("https://www.espnqa.com/?src=com&_adblock=true&espn=cloud");
        System.out.println(driver.getTitle());
        homePageStart = new homePageStart(driver);
        driver.manage().window().maximize();
        assertEquals(homePageStart.getPageTitle(), "ESPN", "Web page Title does not match");
        assertTrue(homePageStart.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
