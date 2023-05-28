
import org.openqa.selenium.WebDriver;

class Page extends PageBase {

    public Page(WebDriver driver, String url) {
        super(driver);
        this.driver.get(url);
    }
}
