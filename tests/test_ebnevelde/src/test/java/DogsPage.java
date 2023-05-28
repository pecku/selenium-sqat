
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;


class DogsPage extends PageBase {
    private By dogImgBy = By.xpath("//div[@id='eb_body_real']//img");
    
    public DogsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.ebnevelde.hu/dogs/");
    }

    public void downloadDogImages(String path){
        List<WebElement> dogImgs = waitAndReturnElements(dogImgBy);
        dogImgs.forEach(dogImg -> {
            try{
                URL imageURL = new URL(dogImg.getAttribute("src"));
                System.out.println(imageURL);
                BufferedImage saveImage = ImageIO.read(imageURL.openStream());            
                ImageIO.write(saveImage, "jpg", new File(path + saveImage.hashCode() + ".jpg"));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }
}
