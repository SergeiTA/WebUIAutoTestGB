package org.webAutoTest.engine.models.yandexWeatherForecast;

public class YandexBasePageObject {

    public YandexNavigationBar yandexNavigationBar;

    public YandexBasePageObject() {
       this.yandexNavigationBar = new YandexNavigationBar();
    }
}
