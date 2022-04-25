package tests.LogOutTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePageLoggedIn;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import pages.hovers.UserMenuHoverStart;
import pages.iframes.LoginSingUpIFrame;
import tests.baseTest.BaseTest;

import static org.testng.Assert.*;

public class LogOutTest extends BaseTest{

    @Test(groups = {"logOut"})
    public void logOutTest() {
        assertEquals(homePageLoggedIn.getPageHeader(), "ESPN", "Web page Title does not match");
        assertFalse(homePageLoggedIn.isLeftLoginMenuVisible(), "The login menu is visible, confirm that you're logged in");
        UserMenuHoverLoggedIn menuHoverLoggedIn =  homePageLoggedIn.hoverUserMenu();
        assertTrue(menuHoverLoggedIn.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHoverLoggedIn.getHeader(), "Welcome!", "Menu Header Incorrect");
        HomePageStart homePageStart1 = menuHoverLoggedIn.clickLogOutLink();
        assertEquals(homePageStart1.getPageHeader(), "ESPN", "Web page Title does not match");
        assertTrue(homePageStart1.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
    }
}
