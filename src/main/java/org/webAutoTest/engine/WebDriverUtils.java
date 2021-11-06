package org.webAutoTest.engine;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
    //Если не сделаем static сравнения в тестовых классов не убудут искать элементы к классах утилит и сценариев
    //Можно было бы обыграть экзеплярами, вовращаемыми из сценариев, но пока не вижу больших преимуществ
    private static org.openqa.selenium.WebDriver driver;
    private static WebDriverWait webDriverWait;

//Не нелаю экземпляр драйвера статиком, возможно потом прийдетя распараллеливать выполнение тестов с этим драйвером
    public WebDriverUtils() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox")
                .addArguments("--disable-notification");
        driver = new ChromeDriver(chromeOptions);

        webDriverWait = new WebDriverWait(getDriver(), 10);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}
