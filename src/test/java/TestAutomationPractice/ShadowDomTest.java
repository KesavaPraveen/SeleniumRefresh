package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShadowDomTest {
    WebDriver driver;
    @Test
    public void shadowTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/#");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Getting the shadow root element
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));

        // Printing the text inside the shadow root
        WebElement mobiles = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('#shadow_content .info')", shadowHost);
        System.out.println(mobiles.getText()); // Mobiles

        // Retrieving laptop text inside a nested shadow root
        WebElement laptops = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot" +
                        ".querySelector('#nested_shadow_host').shadowRoot.querySelector('#nested_shadow_content div')",
                shadowHost);
        System.out.println(laptops.getText()); // Laptops

        // Clicking on a link inside shadow root
        WebElement blogLink= (WebElement) js.executeScript(
                "return arguments[0]." +
                        "shadowRoot.querySelector('a[href=\"https://www.pavantestingtools.com/\"]')",shadowHost);
        js.executeScript("arguments[0].click();", blogLink);
        Thread.sleep(2000);
        driver.navigate().back();

        // Typing an input in the input text box inside shadow root
        WebElement inputBox = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('input[type=text]')", shadowHost);
        inputBox.sendKeys("Selenium Shadow DOM");

        // Clicking checkbox inside shadow root
        WebElement checkbox = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('input[type=checkbox]')", shadowHost);
        checkbox.click();
        // Validating checkbox inside shadow root is checked
        Assert.assertTrue(checkbox.isSelected());

        driver.quit();
    }
}
