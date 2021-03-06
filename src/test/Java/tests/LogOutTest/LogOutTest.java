package tests.LogOutTest;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import tests.baseTest.BaseTest;

import static org.testng.Assert.*;

public class LogOutTest extends BaseTest{

    public Logger logger = Logger.getLogger(LogOutTest.class);

    @Test(groups = {"logOut"})
    public void logOutTest() {
        assertEquals(homePageLoggedIn.getPageHeader(), "ESPN", "Web page Title does not match");
        assertFalse(homePageLoggedIn.isLeftLoginMenuVisible(), "The login menu is visible, confirm that you're logged in");
        logger.info("You logged in correctly and are now in the home page logged in");
        UserMenuHoverLoggedIn menuHoverLoggedIn =  homePageLoggedIn.hoverUserMenu();
        assertTrue(menuHoverLoggedIn.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHoverLoggedIn.getHeader(), "WelcomerandomName!", "Menu Header Incorrect"); //this assertt different from the login has the name of the user
        logger.info("The hover menu is correctly displayed for logged user");
        HomePageStart homePageStart1 = menuHoverLoggedIn.clickLogOutLink();
        assertEquals(homePageStart1.getPageHeader(), "ESPN", "Web page Title does not match");
        assertTrue(homePageStart1.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
        logger.info("You were correctly logged out");
    }
}
