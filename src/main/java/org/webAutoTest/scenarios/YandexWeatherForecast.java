package org.webAutoTest.scenarios;

import org.openqa.selenium.By;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.enums.WebAddresses;

public class YandexWeatherForecast {
    //Так как по факту это еще не тесты, а просто сценарии действий пользователя, решил расположить классы НЕ в test

    public static void openCityPage() throws InterruptedException {
        WebDriverUtils webDriverUtils = new WebDriverUtils();

        webDriverUtils.getDriver().get(WebAddresses.YANDEX_WEATHER.getWebAddress());

        webDriverUtils.getDriver().findElement(By.xpath("//header//input")).sendKeys("Ростов-на-Дону");

        Thread.sleep(2000);

        webDriverUtils
                .getDriver()
                .findElement(By.xpath("//li[@data-text='Ростов-на-Дону, Ростовская область']"))
                .click();

        Thread.sleep(2000);
        webDriverUtils.getDriver().quit();

        //Далее шли два сравенения по сценарию, пока сюда не пишем
    }

    public static void openCityMapPage() throws InterruptedException {
        //Не выношу объявление webDriverUtils из методов, что бы не делать его априори статичным
        //Вероятно в будущем потребуеться распараллеливать выполенние методов
        WebDriverUtils webDriverUtils = new WebDriverUtils();

        webDriverUtils.getDriver().get(WebAddresses.YANDEX_WEATHER.getWebAddress());

        webDriverUtils.getDriver().findElement(By.xpath("//header//input")).sendKeys("Ростов-на-Дону");

        Thread.sleep(2000);

        webDriverUtils
                .getDriver()
                .findElement(By.xpath("//li[@data-text='Ростов-на-Дону, Ростовская область']"))
                .click();

        Thread.sleep(2000);

        webDriverUtils
                .getDriver()
                .findElement(By.xpath("//a//div[contains(., 'Показать на карте')]"))
                .click();


        Thread.sleep(2000);
        webDriverUtils.getDriver().quit();

        //Далее шли два сравенения по сценарию, пока сюда не пишем
    }


}
