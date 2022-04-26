package tests.CancelAccountTest;

import org.testng.annotations.Test;
import pages.HomePageStart;
import pages.hovers.UserMenuHoverLoggedIn;
import pages.iframes.MyAccountIFrame;
import tests.baseTest.BaseTest;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class CancelAccountTest extends BaseTest {

    @Test(groups = {"cancelAccount"})
    public void cancelAccountTest() {
        assertEquals(homePageLoggedIn.getPageHeader(), "ESPN", "Web page Title does not match");
        assertFalse(homePageLoggedIn.isLeftLoginMenuVisible(), "The login menu is visible, confirm that you're logged in");
        UserMenuHoverLoggedIn menuHoverLoggedIn =  homePageLoggedIn.hoverUserMenu();
        assertTrue(menuHoverLoggedIn.isMenuDisplayed(), "Menu is not displayed");
        assertEquals(menuHoverLoggedIn.getHeader(), "WelcomerandomName!", "Menu Header Incorrect"); //this assertt different from the login has the name of the user
        MyAccountIFrame myAccountIFrame = menuHoverLoggedIn.clickMyAccountLink();
        assertTrue(myAccountIFrame.isTheIFrameActive());
        myAccountIFrame.clickCancelAccount();
        myAccountIFrame.clickConfirmCancelAccount();
        HomePageStart homePageStart1 = myAccountIFrame.clickLastConfirm();
        assertEquals(homePageStart1.getPageHeader(), "ESPN", "Web page Title does not match");
//        assertTrue(homePageStart1.isLeftLoginMenuVisible(), "Left login menu is not visible, check you're logged out");
    }
}
