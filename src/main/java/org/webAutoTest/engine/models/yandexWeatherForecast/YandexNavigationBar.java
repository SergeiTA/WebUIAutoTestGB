package org.webAutoTest.engine.models.yandexWeatherForecast;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;
import org.webAutoTest.enums.GeographicalLocations;

import java.util.List;

public class YandexNavigationBar extends BasePageObject {

    @FindBy(xpath = "//header//input")
    private WebElement searchInputField;

    @FindBy(xpath = "//li[@data-text]")
    private List<WebElement> suggestionItems;

    public YandexNavigationBar(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public List<WebElement> getSuggestionItems() {
        return suggestionItems;
    }

    public WebElement getSearchInputField() {
        return searchInputField;
    }

    public YandexNavigationBar fillSearchInputField(String text) {
        searchInputField.sendKeys(text);
        return this;
    }

    public WebElement findSuggestionByGeographicalLocation(GeographicalLocations geographicalLocations) {
        String suggestionText = geographicalLocations.getLocationName()
                + ", " + geographicalLocations.getRegionName();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//li[@data-text='" + suggestionText + "']")));
        return suggestionItems.stream()
                .filter(e -> e.getText().equals(suggestionText)).findFirst().get();
    }

    public void clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations geographicalLocations) {
        findSuggestionByGeographicalLocation(geographicalLocations).click();
    }

    public void pressEnterOnSearchInputField() {
        searchInputField.sendKeys(Keys.ENTER);
    }


}
