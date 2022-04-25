package tests.LogInTest;

import org.testng.annotations.BeforeGroups;
import tests.baseTest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePageLoggedIn;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;
import static org.testng.Assert.*;

public class LogInTest extends BaseTest {

    @Test
    @Parameters({"singUpEmail", "singUpPassword"})
    public void testLogin(String username, String password) {
        assertEquals(homePageStart.getPageTitle(), "ESPN", "Web page Title does not match");
        assertTrue(homePageStart.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
        UserMenuHoverStart menuHover = homePageStart.hoverUserMenu();
        assertTrue(menuHover.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHover.getHeader(), "Welcome!", "Menu Header Incorrect");
        LoginSingUpIFrame singUpIFrame = menuHover.clickLoginLink();
        assertTrue(singUpIFrame.isTheIFrameActive(), "The IFrame is not active");
        assertTrue(singUpIFrame.isIFrameLogoVisible(), "IFrame is not visible");
        singUpIFrame.setUsername(username);
        singUpIFrame.setPassword(password);
        HomePageLoggedIn homePageLoggedIn = singUpIFrame.clickLogin();
//        TODO: after the previous method work for the Iframe, assert to confirm that you're back to the main page but not the starting one
    }
}
