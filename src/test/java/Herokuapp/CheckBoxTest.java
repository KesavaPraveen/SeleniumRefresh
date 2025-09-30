package Herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBoxTest {
    @Test
    public void checkBoxesTest()
    {
        WebDriver driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Checkboxes")).click();
        String expectedHeaderText="Checkboxes";
        WebElement headerElement=driver.findElement(By.tagName("h3"));
        String actualHeaderText=headerElement.getText();
        // Verify the Header Text is displayed
        Assert.assertEquals(actualHeaderText,expectedHeaderText);
        List<WebElement> checkBoxes=
                driver.findElements(By.xpath("//form[@id='checkboxes']//input"));
        for(WebElement e: checkBoxes)
        {
            // Verifying if the checkboxes are displayed
            Assert.assertTrue(e.isDisplayed());
            if(e.getText().equalsIgnoreCase("checkbox 2"))
            {
                // Verifying by default the second checkbox is selected
                Assert.assertTrue(e.isSelected());
            }
            if(e.getText().equalsIgnoreCase("checkbox 1"))
            {
                // Verifying if first check box is not selected and checking it
                e.click();
                Assert.assertTrue(e.isSelected());
            }
            if(e.isSelected())
            {
                // Verifying both the checkboxes are not selected
                e.click();
                Assert.assertFalse(e.isSelected());
            }
        }
        driver.quit();
    }
}
