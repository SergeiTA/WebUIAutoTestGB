package org.webAutoTest.engine.models.yandexWeatherForecast;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class YandexBasePageObject extends BasePageObject {

    public YandexNavigationBar yandexNavigationBar;

    public YandexBasePageObject(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
        this.yandexNavigationBar = new YandexNavigationBar(webDriver, webDriverWait);
    }
}
