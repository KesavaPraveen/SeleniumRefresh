package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButtonTest {
    @Test
    public void radioButtonsTest()
    {
        WebDriver driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement maleRadioButton=
                driver.findElement(By.xpath("//input[@id='male']"));
        WebElement femaleRadioButton=
                driver.findElement(By.xpath("//input[@id='female']"));
        // Verifying two Radio buttons are displayed
        Assert.assertTrue(maleRadioButton.isDisplayed(),"Male Radio Button is displayed");
        Assert.assertTrue(femaleRadioButton.isDisplayed(),"Female Radio Button is displayed");
        if(maleRadioButton.isDisplayed())
        {
            maleRadioButton.click();
        }
        // Verifying Male Radio button is selectable
        Assert.assertTrue(maleRadioButton.isSelected(),"Male Radio Button is Selected");
        if(femaleRadioButton.isDisplayed())
        {
            femaleRadioButton.click();
        }
        // Verifying Female Radio button is selectable
        Assert.assertTrue(femaleRadioButton.isSelected(),"Female Radio Button is Selected");
        // Verifying the Text of First Radio Button is Male
        String expectedTextOfMale="Male";
        String actualTextOfMale=
                driver.findElement(By.xpath("//input[@id='male']/following-sibling::label"))
                        .getText();
        Assert.assertEquals(actualTextOfMale,expectedTextOfMale,"Male Label is displayed near to First " +
                "Radio Button");
        // Verifying the Text of Second Radio Button is Female
        String expectedTextOfFemale="Female";
        String actualTextOfFemale=
                driver.findElement(By.xpath("//input[@id='female']/following-sibling::label"))
                        .getText();
        Assert.assertEquals(actualTextOfFemale,expectedTextOfFemale,
                "Female Label is displayed near to Second Radio Button");
        driver.quit();
    }
}
