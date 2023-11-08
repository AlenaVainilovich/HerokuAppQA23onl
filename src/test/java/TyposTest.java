import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TyposTest {
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
    public void typos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        WebElement typos = driver.findElement(By.xpath("//p[2]"));
        Assert.assertEquals(typos.getText(), "Sometimes you'll see a typo, other times you won't.",
                "There is an error in the spelling of the word 'won't.'");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
