package org.webAutoTest.engine.models.yandexWeatherForecast;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexWeatherForecastCityMapPage extends YandexBasePageObject {

    private final String xPathMapLayer = "//div[@class='weather-maps__map']";
    private final String xPathCityDescription = "//div[@class='weather-maps-fact']//h2";

    @FindBy(xpath = xPathMapLayer)
    private WebElement mapLayer;

    @FindBy(xpath = xPathCityDescription)
    private WebElement cityDescription;

    public YandexWeatherForecastCityMapPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public WebElement getMapLayer() {
        return mapLayer;
    }

    public WebElement getCityDescription() {
        return cityDescription;
    }

    @Step("Ожидаем появления карты географической локации")
    public YandexWeatherForecastCityMapPage isMapLayerVisible() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathMapLayer)));
        return this;
    }

    @Step("Проверяем текст описания географической локации")
    public String getCityDescriptionText() {
        return cityDescription.getText();
    }
}
