package org.webAutoTest.engine.models.yandexWeatherForecast;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexWeatherForecastCityPage extends YandexBasePageObject {

    private final String xPathCityCardTitle = "//h1[@id='main_title']";
    private final String xPathShowOnTheMap = "//a//div[contains(., 'Показать на карте')]";

    @FindBy(xpath = xPathCityCardTitle)
    private WebElement cityCardTitle;

    @FindBy(xpath = xPathShowOnTheMap)
    private WebElement showOnTheMap;

    public YandexWeatherForecastCityPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public WebElement getCityCardTitle() {
        return cityCardTitle;
    }

    public WebElement getShowOnTheMap() {
        return showOnTheMap;
    }

    @Step("Проверяем текс заголовка карточки города")
    public String getTextCityCardTitle() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathCityCardTitle)));
        return cityCardTitle.getText();
    }

    @Step("Кликнуть по ссылке \"Показать на карте\"")
    public void clickOnShowOnTheMap() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathShowOnTheMap)));
        showOnTheMap.click();
    }
}
