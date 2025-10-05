package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AlertsTest {
    WebDriver driver;
    @Test
    public void handleAlerts()
    {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        WebElement simpleAlertButton=
                driver.findElement(By.id("alertBtn"));
        simpleAlertButton.click();
        // Getting the Text in the Alert
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        WebElement confirmAlertButton=
                driver.findElement(By.id("confirmBtn"));
        confirmAlertButton.click();
        // Accepting the alert and printing
        driver.switchTo().alert().accept();
        WebElement demoTextLabel=
                driver.findElement(By.id("demo"));
        System.out.println(demoTextLabel.getText());
        // Dismissing the alert and priting
        confirmAlertButton.click();
        driver.switchTo().alert().dismiss();
        System.out.println(demoTextLabel.getText());
        // Typing the text in the alert and accepting the alert and priting
        WebElement promptAlertButton=
                driver.findElement(By.id("promptBtn"));
        promptAlertButton.click();
        driver.switchTo().alert().sendKeys("Kesava Praveen");
        driver.switchTo().alert().accept();
        System.out.println(demoTextLabel.getText());
        // Dismissing the prompt alert and printing
        promptAlertButton.click();
        driver.switchTo().alert().dismiss();
        System.out.println(demoTextLabel.getText());
        driver.quit();

    }

}
