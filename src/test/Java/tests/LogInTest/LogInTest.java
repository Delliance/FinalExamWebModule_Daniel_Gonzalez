package tests.LogInTest;

import pages.HomePageLoggedIn;
import tests.baseTest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;
import static org.testng.Assert.*;

public class LogInTest extends BaseTest {

    @Test(groups = {"logIn"})
    @Parameters({"singUpEmail", "singUpPassword"})
    public void testLogin(String username, String password) {
        assertEquals(homePageStart.getPageHeader(), "ESPN", "Web page Title does not match");
        assertTrue(homePageStart.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
        UserMenuHoverStart menuHover = homePageStart.hoverUserMenu();
        assertTrue(menuHover.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHover.getHeader(), "Welcome!", "Menu Header Incorrect");
        LoginSingUpIFrame singUpIFrame = menuHover.clickLoginLink();
        assertTrue(singUpIFrame.isTheIFrameActive(), "The IFrame is not active");
        assertTrue(singUpIFrame.isIFrameLogoVisible(), "IFrame is not visible");
        singUpIFrame.setUsername(username);
        singUpIFrame.setPassword(password);
        HomePageLoggedIn homePageLoggedIn1 = singUpIFrame.clickLogin();
        assertEquals(homePageLoggedIn1.getPageHeader(), "ESPN", "Web page Title does not match");
        assertFalse(homePageLoggedIn1.isLeftLoginMenuVisible(), "The login menu is visible, you're not logged in");
    }
}
