import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class WebTableTest {
    WebDriver driver;
    @Test
    public void staticTableTest()
    {
        driver=new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> tableElements=
                driver.findElements(By.xpath("//table[@name='BookTable']//tr"));
        // Printing all the values in the table
        for(WebElement e: tableElements)
        {
            System.out.println(e.getText());
        }
        // Printing only the Headers in the table
        List<WebElement> columnElements=
                driver.findElements(By.xpath("//table[@name='BookTable']//tr/th"));
        for(WebElement e: columnElements)
        {
            System.out.print(e.getText()+ " | ");
        }
        System.out.println();
        // Printing Specific cell value in the table
        WebElement bookName=driver.findElement(By.xpath("//table[@name='BookTable']//tr[2]/td[1]"));
        System.out.println(bookName.getText());
        System.out.println(getCellValue(3,4));
        // Printing all cell values
        for(int i=2;i<=tableElements.size();i++)
        {
            for(int j=1;j<=columnElements.size();j++)
            {
                System.out.print(getCellValue(i,j)+" | ");
            }
            System.out.println();
        }
        // Printing price value with a book name
        List<WebElement> bookColumnElements=
                driver.findElements(By.xpath("//table[@name='BookTable']//tr/td[1]"));
        List<WebElement> priceColumnElements=
                driver.findElements(By.xpath("//table[@name='BookTable']//tr/td[4]"));
        for(int i=2;i<bookColumnElements.size();i++)
        {
            if(bookColumnElements.get(i).getText().equalsIgnoreCase("Master In Java"))
            {
                System.out.println(priceColumnElements.get(i).getText());
            }
        }
        // Verifying Table Headers are in correct order
        List<String> expectedHeaderList= Arrays.asList("BookName","Author","Subject","Price");
        List<String> actualHeaderList=new LinkedList<>();
        for(WebElement e: columnElements)
        {
            String text=e.getText();
            actualHeaderList.add(text);
        }
        Assert.assertEquals(actualHeaderList,expectedHeaderList);
        // Calculating the sum of all prices
        int sum=0;
        for(WebElement e: priceColumnElements)
        {
            sum=sum+Integer.parseInt(e.getText());
        }
        System.out.println("Total Price: " +sum);
        driver.quit();
    }
public String getCellValue(int row,int col)
{
    WebElement element=
            driver.findElement(By.xpath("//table[@name='BookTable']" +
                    "//tr[" + row + "]/td[ " + col + "]"));
    return element.getText();
}

@Test
    public void dynamicTableTest()
{
    driver=new ChromeDriver();
    driver.get("https://testautomationpractice.blogspot.com/");
    driver.manage().window().maximize();
    // Printing Column Header Texts
    List<WebElement> columns=
            driver.findElements(By.xpath("//tr[@id='headers']/th"));
    //WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
    for(WebElement e:columns)
    {
        //wait.until(ExpectedConditions.textToBePresentInElementValue(e,e.getText()));
        System.out.println("Column Headers: " +e.getText());
    }

    // Printing all the Row Values
    List<WebElement> rows=
            driver.findElements(By.xpath("//tbody[@id='rows']/tr"));
    List<WebElement> allRows=
            driver.findElements(By.xpath("//tbody[@id='rows']/tr/td"));
    for(WebElement e: allRows)
    {
        System.out.println(e.getText());
    }
    String text=driver.findElement(By.xpath("//tbody[@id='rows']/tr[2]/td[2]")).getText();
    System.out.println(text);
    for(int i=1;i<=rows.size();i++)
    {
        for(int j=1;j<=columns.size();j++)
        {
            System.out.print(getCellValueInDynamicTable(i,j)+ " | ");
        }
        System.out.println();
    }
    // Verifying the column headers name
    Set<String> expectedColumnHeaders=new HashSet<>(Arrays.asList("Name","Memory (MB)","Disk (MB/s)",
            "CPU (%)","Network (Mbps)"));
    Set<String> actualColumnHeaders=new HashSet<>();
    for(WebElement e:columns)
    {
        String text1=e.getText();
        actualColumnHeaders.add(text1);
    }
    Assert.assertEquals(actualColumnHeaders,expectedColumnHeaders);
    // Printing all the Column and its values one by one
    Set<String> actualNameColumnTexts=getAllTextOfAColumn("Name");
    System.out.println("Name:" +actualNameColumnTexts);

    Set<String> actualMemoryColumnTexts=getAllTextOfAColumn("Memory (MB)");
    System.out.println("Memory: " +actualMemoryColumnTexts);

    Set<String> actualDiskColumnTexts=getAllTextOfAColumn("Disk (MB/s)");
    System.out.println("Disk: " +actualDiskColumnTexts);

    Set<String> actualCpuPercentColumnTexts=getAllTextOfAColumn("CPU (%)");
    System.out.println("CPU Percent: " +actualCpuPercentColumnTexts);

    Set<String> actualNetworkColumnTexts=getAllTextOfAColumn("Network (Mbps)");
    System.out.println("Network: " +actualNetworkColumnTexts);

    //WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    // Printing the value of CPU Percent by giving the value of "Chrome"
    String cpuPercent=getCpuPercentOfName("Chrome");
    System.out.println(cpuPercent);
    driver.quit();
}
public String getCellValueInDynamicTable(int row,int col)
{
    WebElement element=
            driver.findElement(By.xpath("//tbody[@id='rows']/tr["+ row + "]/td[" + col + "]"));
    return element.getText();
}

public int getColumnIndex(String columnName)
{
    int columnIndex=0;
    List<WebElement> columns=
            driver.findElements(By.xpath("//tr[@id='headers']/th"));
    for(int i=0;i<columns.size();i++)
    {
        if(columns.get(i).getText().equalsIgnoreCase(columnName))
        {
            columnIndex= i + 1 ;
        }
    }
    return columnIndex;
}
public Set<String> getAllTextOfAColumn(String columnName)
{
    System.out.println("Index of the " +columnName+ " is : " +getColumnIndex(columnName));
    List<WebElement> column=
            driver.findElements(By.xpath(
                    "//tbody[@id='rows']/tr/td["+ getColumnIndex(columnName) + "]"));
    Set<String> actualColumnText=new HashSet<>();
    for(WebElement e: column)
    {
        String text=e.getText();
        actualColumnText.add(text);
    }
    return actualColumnText;
}
public String getCpuPercentOfName(String browserName)
{
    int cpuColIndex=getColumnIndex("CPU (%)");
    List<WebElement> rows=
            driver.findElements(By.xpath("//tbody[@id='rows']/tr"));
    List<WebElement> nameElement=
            driver.findElements(By.xpath("//tbody[@id='rows']/tr/td[1]"));
    List<WebElement> cpuColumnElements=
            driver.findElements(By.xpath("//tbody[@id='rows']/tr/td["+cpuColIndex+"]"));
    for(int i=1;i< rows.size();i++)
    {
        if(nameElement.get(i).getText().equalsIgnoreCase(browserName))
        {
            return cpuColumnElements.get(i).getText();
        }
    }
    return "not found";
}

@Test
    public void paginationTableTest()
{
    driver=new ChromeDriver();
    driver.get("https://testautomationpractice.blogspot.com/");
    driver.manage().window().maximize();
    List<WebElement> columns=
            driver.findElements(By.xpath("//table[@id='productTable']//th"));
    List<WebElement> rowsPerPage=
            driver.findElements(By.xpath("//table[@id='productTable']//tr"));
    WebElement cell=
            driver.findElement(By.xpath("//table[@id='productTable']//tr[2]/td[4]"));
    List<WebElement> eachRow=
            driver.findElements(By.xpath("//table[@id='productTable']//tr[1]/td"));
    WebElement singlePage=
            driver.findElement(By.xpath("//ul[@id='pagination']/li[1]/a"));
    List<WebElement> allPages=
            driver.findElements(By.xpath("//ul[@id='pagination']//a"));
    WebElement selectCheckBox=
            driver.findElement(By.xpath("//table[@id='productTable']//tr[1]//input[@type='checkbox']"));
    // Printing all values in the pagination table
    int totalPages=allPages.size();
    for(int i=1;i<=totalPages;i++)
    {
        System.out.println("Page " +i+":");
        singlePage=
                driver.findElement(By.xpath("//ul[@id='pagination']/li["+i+"]/a"));
        singlePage.click();
        rowsPerPage=
                driver.findElements(By.xpath("//table[@id='productTable']//tr"));
        for(int j=1;j< rowsPerPage.size();j++)
        {
            System.out.println(" Row " +j+ ": ");
            columns=
                    driver.findElements(By.xpath("//table[@id='productTable']//th"));
            // Selecting each rows select check box
            selectCheckBox=
                    driver.findElement(By.xpath(
                            "//table[@id='productTable']//tr["+j+"]//input[@type='checkbox']"));
            selectCheckBox.click();
            if(selectCheckBox.isSelected())
            {
                System.out.println("Check Box of row " +j+ " is checked");
            }
            for(int k=1;k<columns.size();k++)
            {
                cell=
                        driver.findElement(By.xpath(
                                "//table[@id='productTable']//tr["+j+"]/td["+k+"]"));
                System.out.print(cell.getText()+" | ");

            }
            System.out.println();
        }
        System.out.println();
    }
    driver.quit();
}
}
