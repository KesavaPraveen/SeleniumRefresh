package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropDownTest {
    @Test
    public void dropDownsTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        WebElement countryDropDown=
                driver.findElement(By.xpath("//select[@id='country']"));
        // Verifying Country Drop Down is Displayed
        Assert.assertTrue(countryDropDown.isDisplayed());
        List<WebElement> countriesList=
                driver.findElements(By.xpath("//select[@id='country']/option"));
        int actualCountriesCount= countriesList.size();
        int expectedCountriesCount=10;
        // Verifying the Count of the Options in Country Drop Down
        Assert.assertEquals(actualCountriesCount,expectedCountriesCount);
        // Verifying the default option selected in the country drop down
        Select select=new Select(countryDropDown);
        String expectedSelectedOption="United States";
        String actualSelectedOption=select.getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelectedOption,expectedSelectedOption,"Default Selected Option");
        // Verifying the Options in the Country drop down
        List<String> expectedOptions= Arrays.asList("United States","Canada","United Kingdom","Germany",
                                                    "France","Australia","Japan","China","Brazil","India");
        List<WebElement> countryOptions=select.getOptions();
        List<String> actualOptions=new ArrayList<>();
        for(WebElement e: countryOptions)
        {
            String text=e.getText();
            actualOptions.add(text);
        }
        Assert.assertEquals(actualOptions,expectedOptions,"Options validation");
        // Selecting the options in country drop down by index
        for(int i=0;i<actualCountriesCount;i++)
        {
            // Selecting the value by index
            select.selectByIndex(i);
        }
        // Selecting the options in country drop down by visible text and verifying is it selected
        for(String text: expectedOptions)
        {
            select.selectByVisibleText(text);
            if(countryDropDown.getText().equalsIgnoreCase(text))
            {
                Assert.assertTrue(countryDropDown.isSelected());
            }
        }

        WebElement colorsDropDown=driver.findElement(By.id("colors"));
        Select select1=new Select(colorsDropDown);
        // Verify colors drop down is multi select drop down
        Assert.assertTrue(select1.isMultiple());
        // Verify options in colors drop down
        List<WebElement> colorsOptions=driver
                .findElements(By.xpath("//select[@id='colors']/option"));
        List<String> actualColorsOptionsText=new ArrayList<>();
        for(WebElement e: colorsOptions)
        {
            String text=e.getText();
            actualColorsOptionsText.add(text);
        }
        List<String> expectedColorsOptionsText=Arrays.asList("Red","Blue","Green","Yellow","Red","White","Green");
        Assert.assertEquals(actualColorsOptionsText,expectedColorsOptionsText);
        // Verify all options are selectable and deselecting yellow color and at last deselecting all colors
        for (String e : expectedColorsOptionsText) {
            select1.selectByVisibleText(e);
            if(e.equalsIgnoreCase("Yellow"))
            {
                select1.deselectByVisibleText(e);
                System.out.println("Yellow is Deselected");
            }
        }
        Thread.sleep(2000);
        System.out.println(select1.getFirstSelectedOption().getText());
        select1.deselectAll();

        driver.quit();
    }
}
