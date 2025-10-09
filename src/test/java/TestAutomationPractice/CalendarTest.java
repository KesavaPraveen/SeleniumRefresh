package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalendarTest {
    WebDriver driver;
    @Test
    public void handlingCalendarTest()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/#");
        WebElement calendar=
                driver.findElement(By.id("datepicker"));
        calendar.click();
        selectFutureDate("March 2026","14");
        calendar.click();
        selectPastDate("March 2024","14");
        driver.quit();
    }

    public void selectFutureDate(String monthYear,String date)
    {

        String actualMonthYear=
                driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();

        while(!actualMonthYear.equalsIgnoreCase(monthYear))
        {

            WebElement nextButton=
                    driver.findElement(By.cssSelector("a.ui-datepicker-next.ui-corner-all"));
            nextButton.click();
            actualMonthYear=
                    driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();
            System.out.println(actualMonthYear);
        }
        WebElement day=
                driver.findElement(By.xpath("//a[text()='"+date+"']"));
        day.click();
    }

    public void selectPastDate(String monthYear,String date)
    {
        String actualMonthYear=
                driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();
        while(!actualMonthYear.equalsIgnoreCase(monthYear))
        {

            WebElement prevButton=
                    driver.findElement(By.cssSelector("a.ui-datepicker-prev.ui-corner-all"));
            prevButton.click();
            actualMonthYear=
                    driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();
            System.out.println(actualMonthYear);
        }
        WebElement day=
                driver.findElement(By.xpath("//a[text()='"+date+"']"));
        day.click();
    }
    }
