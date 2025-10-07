package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ActionsTest {
    WebDriver driver;

    @Test
    public void mouseOverTest() {
        driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        WebElement mouseOverButton=
                driver.findElement(By.id("mousehover"));
        Actions actions=new Actions(driver);
        actions.moveToElement(mouseOverButton).perform();
        WebElement topLink=
                driver.findElement(By.linkText("Top"));
        topLink.click();
    }

    @Test
    public void doubleClickTest()
    {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/#");
        driver.manage().window().maximize();
        WebElement doubleClickBtn=
                driver.findElement(By.xpath("//button[@ondblclick='myFunction1()']"));
        Actions actions=new Actions(driver);
        actions.doubleClick(doubleClickBtn).perform();
    }

    @Test
    public void dragAndDropTest()
    {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/#");
        driver.manage().window().maximize();
        WebElement drag=
                driver.findElement(By.id("draggable"));
        WebElement drop=
                driver.findElement(By.id("droppable"));
        System.out.println(drop.getText());
        String expectedText="Dropped!";
        Actions actions=new Actions(driver);
        actions.dragAndDrop(drag,drop).perform();
        System.out.println(drop.getText());
        Assert.assertEquals(drop.getText(),expectedText);
    }

    @Test
    public void scrollToElementTest() throws InterruptedException {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/#");
        driver.manage().window().maximize();
        WebElement dropDown=
                driver.findElement(By.id("comboBox"));
        dropDown.click();
        List<WebElement> itemsList=
                driver.findElements(By.xpath("//div[@id='dropdown']/div"));
        for(int i=1;i<=itemsList.size();i++)
        {
            Actions actions=new Actions(driver);
            WebElement options=
                    driver.findElement(By.xpath("//div[@id='dropdown']/div[text()='Item "+i+"']"));
            actions.scrollToElement(options).perform();
            if (options.isDisplayed() && options.isEnabled()) {
                options.click();
                System.out.println("Clicked: Item " + i);
            } else {
                System.out.println("Skipping: Item " + i + " (not visible)");
            }
            dropDown.click();
        }

    }
    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}
