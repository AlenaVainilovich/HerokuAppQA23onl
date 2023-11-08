import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HoversTest {
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
    public void hovers() {
        Actions action = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/hovers");
        List<WebElement> profiles = driver.findElements(By.className("figure"));
        action.moveToElement(profiles.get(0)).perform();
        String userName1 = profiles.get(0).findElement(By.tagName("h5")).getText();
        Assert.assertEquals(userName1, "name: user1", "Username is wrong");
        driver.findElement(By.linkText("View profile")).click();
        String message1 = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(message1, "Found", "404 Error: Not Found");
        driver.navigate().back();

        action.moveToElement(profiles.get(1)).perform();
        String userName2 = profiles.get(1).findElement(By.tagName("h5")).getText();
        Assert.assertEquals(userName2, "name: user2", "Username is wrong");
        driver.findElement(By.linkText("View profile")).click();
        String message2 = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(message2, "Found", "404 Error: Not Found");
        driver.navigate().back();

        action.moveToElement(profiles.get(2)).perform();
        String userName3 = profiles.get(2).findElement(By.tagName("h5")).getText();
        Assert.assertEquals(userName3, "name: user3", "Username is wrong");
        driver.findElement(By.linkText("View profile")).click();
        String message3 = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(message3, "Found", "404 Error: Not Found");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
