package TestAutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SvgElementsTest {
    WebDriver driver;

    @Test
    public void handleSvgElements()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/#");
        WebElement headerSvg=
                driver.findElement(By.xpath("//h2[text()='SVG Elements']"));
        System.out.println(headerSvg.getText());
        // Printing the Stroke Width attribute value of SVG Elements
        // --- Circle ----
        WebElement circleSvg=
                driver.findElement(By.xpath("//*[local-name()='svg']//*[local-name()='circle']"));
        String circleStrokeWidth=circleSvg.getDomAttribute("stroke-width");
        System.out.println("Circle: " +circleStrokeWidth);
        // ---- Rectangle ----
        WebElement rectangleSvg=
                driver.findElement(By.xpath("//*[local-name()='svg']//*[local-name()='rect']"));
        String rectStrokeWidth=rectangleSvg.getDomAttribute("stroke-width");
        System.out.println("Rectangle: " +rectStrokeWidth);
        // ---- Polygon ----
        WebElement polySvg=
                driver.findElement(By.xpath("//*[local-name()='svg']//*[local-name()='polygon']"));
        String polygonStrokeWidth=circleSvg.getDomAttribute("stroke-width");
        System.out.println("Polygon: " +polygonStrokeWidth);
        driver.quit();
    }
}
