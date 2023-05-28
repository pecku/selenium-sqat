import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;


public class SeleniumTest {
    public WebDriver driver;
    private MainPage mainPage;
    
    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        //driver.manage().window().maximize();

        mainPage = new MainPage(this.driver);
    }

    @Test
    public void testMain() {
        Assert.assertTrue(mainPage.getTitle().contains("Ebnevelde"));
        Assert.assertTrue(mainPage.getEbKorText().contains(". k"));
    }
    
    @Test
    public void fillSimpleFormAndSend() {
        mainPage.login();
        Assert.assertTrue(mainPage.isRegVisible());
    }

    @Test
    public void formSendingWithUser() {
        mainPage.login();
        ProfilePage profilePage = new ProfilePage(this.driver);
        profilePage.writeIntroduction("This introduction text is written by Selenium.");
        UserPage userPage = new UserPage(this.driver);
        Assert.assertTrue(userPage.getIntroText().contains("This introduction text is written by Selenium."));
    } 

    @Test
    public void logout() {
        mainPage.login();
        mainPage.logout();
        Assert.assertTrue(mainPage.isRegVisible());
    }

    @Test
    public void staticPageTest() {
        Page helpPage = new Page(this.driver, "https://www.ebnevelde.hu/help/0/");
        helpPage.getBodyText().contains("Mi az Ebnevelde");
    }

    @Test
    public void multiplePageTestFromArray() {
        List<PageInfo> pagesToTest = new ArrayList<>();
        pagesToTest.add(new PageInfo("https://www.ebnevelde.hu", "Nevelj te is"));
        pagesToTest.add(new PageInfo("https://www.ebnevelde.hu/info/", "Az Ebnevelde egy folyamatosan"));
        pagesToTest.add(new PageInfo("https://www.ebnevelde.hu/info/irc/", "tartsd tiszteletben az ebnevelde"));

        for (PageInfo page : pagesToTest) {
            String url = page.getUrl();
            String expectedText = page.getExpectedText();

            Page pageObj = new Page(this.driver, url);
            Assert.assertTrue(pageObj.getBodyText().contains(expectedText));
        }
    }

    @Test
    public void downloadMultipleFiles() {
        mainPage.login();
        DogsPage dogsPage = new DogsPage(this.driver);
        dogsPage.downloadDogImages("src/test/dl/");
    }

    @Test
    public void fileUpload() {
        mainPage.login();
        ProfilePage profilePage = new ProfilePage(this.driver);
        profilePage.uploadPic("/home/selenium/tests/test_ebnevelde/src/test/resources/pic.jpg");
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static class PageInfo {
        private String url;
        private String expectedText;

        public PageInfo(String url, String expectedText) {
            this.url = url;
            this.expectedText = expectedText;
        }

        public String getUrl() {
            return url;
        }

        public String getExpectedText() {
            return expectedText;
        }
    }
}
