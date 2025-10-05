package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class TabsAndWindowsTest {

    WebDriver driver;
    @Test
    public void multipleTabsTest()
    {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        WebElement tabButton=
                driver.findElement(By.xpath("//button[@onclick='myFunction()']"));
        // Opening the new tab three times
        for(int i=1;i<=3;i++)
        {
            tabButton.click();
        }
        // Getting the Parent Window Id and Child Windows ids and switching to it, getting the tile and closing
        String parentWindow=driver.getWindowHandle();
        Set<String> allWindows=driver.getWindowHandles();
        for(String window: allWindows)
        {
            if(!window.equals(parentWindow))
            {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        System.out.println(driver.getTitle());
        // Opening a new Pop-up window three times
        WebElement popUpButton=
                driver.findElement(By.id("PopUp"));
        popUpButton.click();
        popUpButton.click();
        Set<String> childWindows=driver.getWindowHandles();
        for(String window: childWindows)
        {
            if(!window.equals(parentWindow))
            {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        System.out.println(driver.getTitle());

        driver.quit();
    }
}
