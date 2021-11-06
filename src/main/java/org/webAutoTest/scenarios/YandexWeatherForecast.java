package org.webAutoTest.scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.enums.GeographicalLocations;

public class YandexWeatherForecast {

    public static void openCityPage(WebDriver webDriver, WebDriverWait webDriverWait
            , GeographicalLocations geographicalLocations) {

        webDriver.findElement(By.xpath("//header//input"))
                .sendKeys(geographicalLocations.getLocationName());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//li[@data-text='" + geographicalLocations.getLocationName()
                        + ", " + geographicalLocations.getRegionName() + "']")));

        webDriver
                .findElement(By.xpath("//li[@data-text='" + geographicalLocations.getLocationName()
                        + ", " + geographicalLocations.getRegionName() + "']"))
                .click();



    }

    public static void openCityMapPage(WebDriver webDriver, WebDriverWait webDriverWait
            , GeographicalLocations geographicalLocations) {

        webDriver.findElement(By.xpath("//header//input")).sendKeys("Ростов-на-Дону");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//li[@data-text='" + geographicalLocations.getLocationName()
                        + ", " + geographicalLocations.getRegionName() + "']")));

        webDriver
                .findElement(By.xpath("//li[@data-text='" + geographicalLocations.getLocationName()
                        + ", " + geographicalLocations.getRegionName() + "']"))
                .click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a//div[contains(., 'Показать на карте')]")));

        webDriver
                .findElement(By.xpath("//a//div[contains(., 'Показать на карте')]"))
                .click();
    }

    public static void openCityPageByStringAndPressEnter(WebDriver webDriver, String inputString) {
        WebElement webElement = webDriver.findElement(By.xpath("//header//input"));
        webElement.sendKeys(inputString);
        webElement.sendKeys(Keys.ENTER);
    }


}
