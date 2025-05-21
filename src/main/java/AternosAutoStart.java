import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class AternosAutoStart {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://aternos.org/go/");
            driver.findElement(By.name("user")).sendKeys(System.getenv("ATERNOS_USER"));
            driver.findElement(By.name("password")).sendKeys(System.getenv("ATERNOS_PASS"));
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(5000);

            WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
            if (startButton.isDisplayed()) {
                startButton.click();
                System.out.println("Server started.");
            } else {
                System.out.println("Start button not visible.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
