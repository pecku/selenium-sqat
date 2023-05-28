
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


class ProfilePage extends PageBase {
    private By introTextareaBy = By.xpath("//textarea[@id='adatlapszoveg']");
    private By saveButtonBy = By.xpath("//form[@name='adatlap']//input[@type='submit']");
    private By picUploadBy = By.xpath("//div[@id='eb_body_real']//input[@type='file'][@name='new_image']");
    
    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://ebnevelde.hu/adatlap/");
    }
    
    public void writeIntroduction(String introText){
        WebElement textArea = waitAndReturnElement(introTextareaBy);
        textArea.clear();
        textArea.sendKeys(introText);
        waitAndReturnElement(saveButtonBy).click();
    }

    public void uploadPic(String path){
        WebElement picUploadElement = waitAndReturnElement(picUploadBy);
        picUploadElement.sendKeys(path);
        waitAndReturnElement(saveButtonBy).click();
    }
}
