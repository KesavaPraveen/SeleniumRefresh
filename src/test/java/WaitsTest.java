import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {
    WebDriver driver;
    @Test
    public void dynamicLoadedElement()
    {
        /*
        Wait until the web element is visible
         */
        driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.manage().window().maximize();
        WebElement startButton=
                driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();
        By finishElement=
                By.xpath("//div[@id='finish']/h4");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishElement));
        String actualText=driver.findElement(finishElement).getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText,"Hello World!");
    }

    @Test
    public void dynamicPageElement()
    {
        /*
        Wait until the Loader gets hidden from the DOM or Screen
         */
        driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.manage().window().maximize();
        WebElement startButton=
                driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();
        By loadingSpinner=By.id("loading");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        By finishElement=
                By.xpath("//div[@id='finish']/h4");
        String actualText=driver.findElement(finishElement).getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText,"Hello World!");

    }

    @Test
    public void enableDisableTest()
    {
        /*
        Wait for the web element to be visible and text to be present in the web element
         */
        driver=new ChromeDriver();
        driver.get("https://demoqa.com/dynamic-properties");
        driver.manage().window().maximize();
        WebElement enableButton=
                driver.findElement(By.id("enableAfter"));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(enableButton));
        Assert.assertTrue(enableButton.isEnabled());
        Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement(
                enableButton,"Will enable 5 seconds")));
        System.out.println(enableButton.getText());
        WebElement visibleButton=
                driver.findElement(By.id("visibleAfter"));
        wait.until(ExpectedConditions.visibilityOf(visibleButton));
        Assert.assertTrue(visibleButton.isDisplayed());
    }

    @Test
    public void alertWait()
    {
        /*
        Wait until the alert pop up is displayed
         */
        driver=new ChromeDriver();
        driver.get("https://demoqa.com/alerts");
        driver.manage().window().maximize();
        WebElement timerAlertButton=
                driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlertText=driver.switchTo().alert().getText();
        System.out.println(actualAlertText);
        Assert.assertEquals(actualAlertText,"This alert appeared after 5 seconds");
        driver.switchTo().alert().accept();
    }

    @Test
    public void fastTest()
    {
        /*
        Using Fluent Wait to print the internet speed from the moment the webpage is launched
        Waiting for the Show More info button to display and click
        Using Fluent Wait to print the Upload Speed
         */
        driver=new ChromeDriver();
        driver.get("https://fast.com/#");
        WebElement speedElement=
                driver.findElement(By.id("speed-value"));

        final String[] lastValue={""};
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            wait.until(driver1 -> {
                String currentValue = speedElement.getText();
                if (!currentValue.equals(" ") && !currentValue.equals(lastValue[0])) {
                    System.out.println(currentValue);
                    lastValue[0] = currentValue;
                }
                return false;
            });
        }
        catch (TimeoutException e)
        {}

        System.out.println("Final Value:" +speedElement.getText());
        By show=By.id("show-more-details-link");
        WebElement showMoreButton=driver.findElement(show);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(show));
        showMoreButton.click();
        final String[] lastUploadValue={""};
        WebElement uploadSpeed=driver.findElement(By.id("upload-value"));
        WebElement uploadSpeedUnits=driver.findElement(By.id("upload-units"));
        try {
            FluentWait<WebDriver> wait1 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            wait1.until(driver2 -> {
                String currentUploadValue = uploadSpeed.getText();
                if (!currentUploadValue.equals(" ") && !currentUploadValue.equals(lastValue[0])) {
                    System.out.println(currentUploadValue+" " +uploadSpeedUnits.getText());
                    lastUploadValue[0] = currentUploadValue;
                }
                return false;
            });
        }
        catch (TimeoutException e)
        {}

        System.out.println("Final Value: " +uploadSpeed.getText()+ " " +uploadSpeedUnits.getText());
    }


    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }

}
