package org.webAutoTest.engine.models.yandexWeatherForecast;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class YandexNavBarSearchResultsPage extends YandexBasePageObject {

    private final String xPathResultsArea = "//div[@class='content content_ancient-design_yes']//h1";
    private final String xPathResultsTitle
            = "//div[@class='content content_ancient-design_yes']/h1[@class='title title_level_1']";
    private final String xPathResultsRows = "//div[@class='grid clearfix']//div[@class='grid__row clearfix']//li";

    private SelenideElement resultsArea = $(By.xpath(xPathResultsArea));
    private SelenideElement resultsTitle = $(By.xpath(xPathResultsTitle));
    private SelenideElement resultsRows = $(By.xpath(xPathResultsRows));

    public YandexNavBarSearchResultsPage() {
        super();
    }

    public SelenideElement getResultsArea() {
        return resultsArea;
    }

    public SelenideElement getResultsTitle() {
        return resultsTitle;
    }

    public SelenideElement getResultsRows() {
        return resultsRows;
    }

    @Step("Проверяем текст результатов поиска")
    public String getResultsAreaText() {
        return resultsArea.getText();
    }

    @Step("Проверяем заголовок поля для результатов поиска")
    public String getResultsTitleText() {
        return resultsTitle.getText();
    }
}
