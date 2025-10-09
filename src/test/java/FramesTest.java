import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FramesTest {
    WebDriver driver;

    @Test
    public void handlingFrames() {
        driver = new ChromeDriver();
        driver.get("https://ui.vision/demo/webtest/frames/");
        driver.manage().window().maximize();
        List<WebElement> frames =
                driver.findElements(By.tagName("frame"));
        System.out.println("Total frames: " + frames.size());
        // Iterating to each frame and switching to it and typing the input and getting the label of the text
        for (int i = 1; i <=frames.size(); i++)
        {
                    driver.switchTo().frame(
                            driver.findElement(
                                    By.xpath("//frame[contains(@src,'frame_" + i + "')]")));
                    System.out.println(driver.findElement(
                            By.xpath("//form[@id='id" + i + "']/div")).getText());
                    driver.findElement(By.xpath(
                            "//form[@id='id" + i + "']//input")).sendKeys("123");
                    driver.switchTo().defaultContent();
        }
        driver.quit();
        }
    }
