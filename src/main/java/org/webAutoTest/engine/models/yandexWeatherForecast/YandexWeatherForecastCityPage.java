package org.webAutoTest.engine.models.yandexWeatherForecast;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class YandexWeatherForecastCityPage extends YandexBasePageObject {

    private final String xPathCityCardTitle = "//h1[@id='main_title']";
    private final String xPathShowOnTheMap = "//a//div[contains(., 'Показать на карте')]";

    private SelenideElement cityCardTitle = $(By.xpath(xPathCityCardTitle));
    private SelenideElement showOnTheMap = $(By.xpath(xPathShowOnTheMap));

    public YandexWeatherForecastCityPage() {
        super();
    }

    public SelenideElement getCityCardTitle() {
        return cityCardTitle;
    }

    public SelenideElement getShowOnTheMap() {
        return showOnTheMap;
    }

    @Step("Проверяем текс заголовка карточки города")
    public String getTextCityCardTitle() {
        return cityCardTitle.getText();
    }

    @Step("Кликнуть по ссылке \"Показать на карте\"")
    public void clickOnShowOnTheMap() {
        showOnTheMap.click();
    }
}
