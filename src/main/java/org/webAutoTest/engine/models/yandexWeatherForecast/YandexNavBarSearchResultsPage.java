package org.webAutoTest.engine.models.yandexWeatherForecast;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexNavBarSearchResultsPage extends YandexBasePageObject {

    private final String xPathResultsArea = "//div[@class='content content_ancient-design_yes']//h1";
    private final String xPathResultsTitle
            = "//div[@class='content content_ancient-design_yes']/h1[@class='title title_level_1']";
    private final String xPathResultsRows = "//div[@class='grid clearfix']//div[@class='grid__row clearfix']//li";

    @FindBy(xpath = xPathResultsArea)
    private WebElement resultsArea;

    @FindBy(xpath = xPathResultsTitle)
    private WebElement resultsTitle;

    @FindBy(xpath = xPathResultsRows)
    private WebElement resultsRows;

    public YandexNavBarSearchResultsPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public WebElement getResultsArea() {
        return resultsArea;
    }

    public WebElement getResultsTitle() {
        return resultsTitle;
    }

    public WebElement getResultsRows() {
        return resultsRows;
    }

    public YandexNavBarSearchResultsPage isResultsAreaDisplayed() {
       webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathResultsArea)));
       return this;
    }

    public YandexNavBarSearchResultsPage isResultsRowsDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathResultsRows)));
        return this;
    }

    public String getResultsAreaText() {
        return resultsArea.getText();
    }

    public String getResultsTitleText() {
        return resultsTitle.getText();
    }
}
