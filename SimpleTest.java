import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void googleSearchtest(){
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("mohamad salah");
        searchBox.submit();

        // انتظر ظهور النتايج
        try { Thread.sleep(2000); } catch (Exception e){}

        // Assert على وجود كلمة salah في أول نتيجة
        WebElement results = driver.findElement(By.id("search"));
        String text = results.getText().toLowerCase();

        Assert.assertTrue(text.contains("salah"), "Result does not contain salah!");
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}

