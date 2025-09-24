import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Navigation {
    WebDriver driver;


    @Test
    public void TC01() {
        /*
        ATC_01: Verify homepage loads with correct title.
         */
        driver= new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedTitle = "The Internet";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void TC02(){
        /*
        ATC_02: Verify the links count.
         */
        driver= new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        By linksList=By.xpath("//div[@id='content']//li/a");
        int actualLinksCount=driver.findElements(linksList).size();
        int expectedLinksCount=44;
        Assert.assertEquals(actualLinksCount,expectedLinksCount);
    }

    @Test
    public void TC03(){
        /*
        ATC_03: Verify broken and challenging dom links are accessible
                Verify on clicking the links it navigates to the correct page and URL.
         */
        driver= new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        By linksList=By.xpath("//div[@id='content']//li/a");
        List<WebElement> links=driver.findElements(linksList);
        List<String> actualList=new ArrayList<>();
        String url="";
        for(WebElement e: links)
        {
            if(e.getText().contains("Broken") || e.getText().contains("Challenging"))
            {
                e.click();
                url=driver.getCurrentUrl();
                System.out.println(url);
                actualList.add(url);
                driver.navigate().back();
                Assert.assertTrue(e.isDisplayed());
            }
        }
        List<String> expectedList=new ArrayList<>();
        expectedList.add("https://the-internet.herokuapp.com/broken_images");
        expectedList.add("https://the-internet.herokuapp.com/challenging_dom");
        Assert.assertEquals(actualList,expectedList);
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
