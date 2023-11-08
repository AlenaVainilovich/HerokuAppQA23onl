import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SortableDataTablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sortableDataTables() {
        driver.get("https://the-internet.herokuapp.com/tables");
        String cellFirstName = driver.findElement(By.xpath("//table[1]//tr[3]/td[1]")).getText();
        Assert.assertEquals(cellFirstName, "Doe", "The values don't match");
        String cellLastName = driver.findElement(By.xpath("//table[1]//tr[2]/td[2]")).getText();
        Assert.assertEquals(cellLastName, "Frank", "The values don't match");
        String cellEmail = driver.findElement(By.xpath("//table[2]//tr[4]/td[3]")).getText();
        Assert.assertEquals(cellEmail, "tconway@earthlink.net", "The values don't match");
        String cellDue = driver.findElement(By.xpath("//table[2]//tr[2]/td[4]")).getText();
        Assert.assertEquals(cellDue, "$51.00", "The values don't match");
        String cellWebSite = driver.findElement(By.xpath("//table[1]//tr[3]/td[5]")).getText();
        Assert.assertEquals(cellWebSite, "http://www.jdoe.com", "The values don't match");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
