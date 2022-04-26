package tests.CancelAccountTest;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import pages.iframes.MyAccountIFrame;
import tests.baseTest.BaseTest;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class CancelAccountTest extends BaseTest {

    public Logger logger = Logger.getLogger(CancelAccountTest.class);

    @Test(groups = {"cancelAccount"})
    public void cancelAccountTest() {
        assertEquals(homePageLoggedIn.getPageHeader(), "ESPN", "Web page Title does not match");
        assertFalse(homePageLoggedIn.isLeftLoginMenuVisible(), "The login menu is visible, confirm that you're logged in");
        logger.info("Login successful, you're logged in in the home page");
        UserMenuHoverLoggedIn menuHoverLoggedIn =  homePageLoggedIn.hoverUserMenu();
        assertTrue(menuHoverLoggedIn.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHoverLoggedIn.getHeader(), "WelcomerandomName!", "Menu Header Incorrect"); //this assertt different from the login has the name of the user
        logger.info("The hover menu was correctly displayed");
        MyAccountIFrame myAccountIFrame = menuHoverLoggedIn.clickMyAccountLink();
        assertTrue(myAccountIFrame.isTheIFrameActive());
        logger.info("The my account IFrame is correctly displayed");
        myAccountIFrame.clickCancelAccount();
        myAccountIFrame.clickConfirmCancelAccount();
        HomePageStart homePageStart1 = myAccountIFrame.clickLastConfirm();
        assertEquals(homePageStart1.getPageHeader(), "ESPN", "Web page Title does not match");
        logger.info("The account was correctly deleted and you're back to the main page");
    }
}
