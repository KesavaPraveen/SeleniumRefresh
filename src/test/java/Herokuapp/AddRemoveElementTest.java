package Herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class AddRemoveElementTest {
    @Test
    public void addRemoveElementTest()
    {
        WebDriver driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElementBtn=
            driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        addElementBtn.click();
        WebElement deleteBtn=
                driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        // Verifying Delete Button is displayed
        Assert.assertTrue(deleteBtn.isDisplayed());
        // Verifying on Clicking Add Button three times three Delete buttons are displayed
        for(int i=1;i<=3;i++)
        {
            addElementBtn.click();
        }
        List<WebElement> deleteBtnList=driver
                .findElements(By.xpath("//div[@id='elements']/button"));
        int deleteBtnCount=deleteBtnList.size();
        Assert.assertEquals(deleteBtnCount,4);
        // Verify on clicking Delete Button it is removed but at least one delete button remains
        for(int i=1;i<deleteBtnCount;i++)
        {
            deleteBtn=driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
            deleteBtn.click();
        }
        deleteBtn=driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        Assert.assertTrue(deleteBtn.isDisplayed());
        driver.quit();
    }

}
