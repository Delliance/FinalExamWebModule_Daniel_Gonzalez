package tests;

import baseTest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ESPNHomePageLoggedIn;
import pages.iframes.LoginSingUpIFrame;

public class LogInTest extends BaseTest {

    @Test
    @Parameters({"username", "password"})
    public void testLogin(String username, String password) {
        //TODO: assert to confirm that you are in the home page, two methods for this
        espnHomePageStart.hoverUserMenu();
        LoginSingUpIFrame singUpIFrame = espnHomePageStart.clickLoginLink();
        //TODO: assert to confirm that the iframe was opened, two methods for this too
//        singUpIFrame.setUsername(username);
//        singUpIFrame.setPassword(password);
//        ESPNHomePageLoggedIn homePageLoggedIn = singUpIFrame.clickLogin();
//        TODO: after the previous method work for the Iframe, assert to confirm that you're back to the main page but not the starting one
    }
}
