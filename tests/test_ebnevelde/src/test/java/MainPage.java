
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class MainPage extends PageBase {

    private By regMenuBy = By.xpath("//a[@href='reg/']");
    private By ebKorBy = By.xpath("//div[@id='eb_kor']");
    private By usernameBy = By.xpath("//div[@class='ubox']//input[@type='text'][@name='login_name']");
    private By passwordBy = By.xpath("//div[@class='ubox']//input[@type='password'][@name='login_pass']");
    private By loginButtonBy = By.xpath("//div[@class='ubox']//input[@type='submit']");
    private By logoutButtonBy = By.xpath("//div[@id='eb_menu']//a[@href='logout/']");
    private By adDismissButtonBy = By.xpath("//div[@id='dismiss-button']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://ebnevelde.hu/");
    }

    public void login() {
        waitAndReturnElement(usernameBy).sendKeys(properties.getProperty("username"));
        waitAndReturnElement(passwordBy).sendKeys(properties.getProperty("password"));
    
        waitAndReturnElement(loginButtonBy).click();
    }

    public void logout() {
        waitAndReturnElement(logoutButtonBy).click();
    }

    public boolean isRegVisible() {
        return waitAndReturnElement(regMenuBy).isDisplayed();
    }

    public void dismissAd() {
        waitAndReturnElement(adDismissButtonBy).click();
    }
    
    public String getEbKorText() {
        return this.waitAndReturnElement(ebKorBy).getText();
    }
}
