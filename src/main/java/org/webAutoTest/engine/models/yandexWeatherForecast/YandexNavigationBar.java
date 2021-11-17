package org.webAutoTest.engine.models.yandexWeatherForecast;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.webAutoTest.enums.GeographicalLocations;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YandexNavigationBar {

    private SelenideElement searchInputField = $(By.xpath("//header//input"));
    private ElementsCollection suggestionItems = $$(By.xpath("//li[@data-text]"));

    public ElementsCollection  getSuggestionItems() {
        return suggestionItems;
    }

    public SelenideElement getSearchInputField() {
        return searchInputField;
    }

    public YandexNavigationBar fillSearchInputField(String text) {
        searchInputField.sendKeys(text);
        return this;
    }

    @Step("Ищем подсказки поиска по географической локации")
    public SelenideElement findSuggestionByGeographicalLocation(GeographicalLocations geographicalLocations) {
        String suggestionText = geographicalLocations.getLocationName()
                + ", " + geographicalLocations.getRegionName();
        return suggestionItems.findBy(Condition.text(suggestionText));
    }

    @Step("Кликнуть на подсказку поиска по географической локации")
    public void clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations geographicalLocations) {
        findSuggestionByGeographicalLocation(geographicalLocations).click();
    }

    @Step("Нажать клавишу \"Enter\", когда поле поиска в фокусе ")
    public void pressEnterOnSearchInputField() {
        searchInputField.sendKeys(Keys.ENTER);
    }


}
