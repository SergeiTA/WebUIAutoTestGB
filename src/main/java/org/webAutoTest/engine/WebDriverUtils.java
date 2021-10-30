package org.webAutoTest.engine;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {

    private org.openqa.selenium.WebDriver driver;

//Не нелаю экземпляр драйвера статиком, возможно потом прийдетя распараллеливать выполнение тестов с этим драйвером
    public WebDriverUtils() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox")
                .addArguments("--disable-notification");
        driver = new ChromeDriver(chromeOptions);
    }


    public WebDriver getDriver() {
        return driver;
    }
}
