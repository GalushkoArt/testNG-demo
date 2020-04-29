import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MailTestJunit {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:/configs/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://account.mail.ru/signup");
    }

    private static Stream<Arguments> mailRegistrationTest() throws IOException {
        return Files.lines(Paths.get("src/test/java/resources/testdata.csv"))
                .map(line-> line.split(","))
                .map(arr -> Arguments.of(arr[0], arr[1], arr[2], arr[3], arr[4]));
    }

    @ParameterizedTest
    @MethodSource
    //@CsvFileSource(resources = "/resources/testdata.csv")
    public void mailRegistrationTest(String firstName, String lastName, String email, String password, String phone) {
        driver.findElement(By.id("fname")).sendKeys(firstName);
        driver.findElement(By.id("lname")).sendKeys(lastName);
        driver.findElement(By.id("aaa__input")).sendKeys(email);
        driver.findElement(By.id("passwordField")).sendKeys(password);
        driver.findElement(By.id("phone-number__phone-input")).sendKeys(phone);
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assertions.assertTrue(driver.findElements(By.xpath(".//*[@data-test-id='required']")).size() > 0);
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
