import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class FramesTest {
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
    public void frames() {
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.xpath("//a[@href='/iframe']")).click();
        driver.switchTo().frame("mce_0_ifr");
        assertEquals(driver.findElement(By.id("tinymce")).getText(),
                "Your content goes here.",
                "Your message should be: Your content goes here.");
        driver.switchTo().defaultContent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
