
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class UserPage extends PageBase {
    private By userButtonBy = By.xpath("//form[@name='adatlap']//input[@type='submit']/following-sibling::a");
    private By introTextBy = By.xpath("//div[@class='adatlap']");

    public UserPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://ebnevelde.hu/adatlap/");

        waitAndReturnElement(userButtonBy).click();
    }

    public String getIntroText(){
        return waitAndReturnElement(introTextBy).getText();
    }
}
