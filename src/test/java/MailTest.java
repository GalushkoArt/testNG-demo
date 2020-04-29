import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Collectors;

public class MailTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/configs/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://account.mail.ru/signup");
    }

    @DataProvider
    private Iterator<Object[]> getTestData() throws IOException {
        return Files.lines(Paths.get("src/test/java/resources/testdata.csv"))
                .map(line -> (Object[]) line.split(","))
                .collect(Collectors.toList())
                .iterator();
    }

    @Test(dataProvider = "getTestData")
    public void mailRegistrationTest(String firstName, String lastName, String email, String password, String phone) {
        driver.findElement(By.id("fname")).sendKeys(firstName);
        driver.findElement(By.id("lname")).sendKeys(lastName);
        driver.findElement(By.id("aaa__input")).sendKeys(email);
        driver.findElement(By.id("passwordField")).sendKeys(password);
        driver.findElement(By.id("phone-number__phone-input")).sendKeys(phone);
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assert.assertTrue(driver.findElements(By.xpath(".//*[@data-test-id='required']")).size() > 0);
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
