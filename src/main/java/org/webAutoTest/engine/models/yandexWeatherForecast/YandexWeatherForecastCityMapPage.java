package org.webAutoTest.engine.models.yandexWeatherForecast;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class YandexWeatherForecastCityMapPage extends YandexBasePageObject {

    private final String xPathMapLayer = "//div[@class='weather-maps__map']";
    private final String xPathCityDescription = "//div[@class='weather-maps-fact']//h2";

    private SelenideElement mapLayer = $(By.xpath(xPathMapLayer));
    private SelenideElement cityDescription = $(By.xpath(xPathCityDescription));

    public YandexWeatherForecastCityMapPage() {
        super();
    }

    public SelenideElement getMapLayer() {
        return mapLayer;
    }

    public SelenideElement getCityDescription() {
        return cityDescription;
    }

    @Step("Проверяем текст описания географической локации")
    public String getCityDescriptionText() {
        return cityDescription.getText();
    }
}
