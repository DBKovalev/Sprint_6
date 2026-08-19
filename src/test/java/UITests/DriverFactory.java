package UITests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private WebDriver driver;

    public void setUpDriver() {
        if ("firefox".equalsIgnoreCase(System.getProperty("browser"))){
            setUpFirefox();
        }
        else{
            setUpChrome();
        }

    }

    public void setUpChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.education-services.ru/");
    }

    public void setUpFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.education-services.ru/");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
