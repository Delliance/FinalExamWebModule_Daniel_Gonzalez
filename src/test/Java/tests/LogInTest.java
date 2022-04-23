package tests;

import baseTest.BaseTest;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {

    @Test
    public void testLogin() {
        espnHomePageNewUser.hoverUserMenu();

        espnHomePageNewUser.clickLoginLink();
    }
}
