package org.webAutoTest.homeWork5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.enums.GeographicalLocations;
import org.webAutoTest.enums.WebAddresses;
import org.webAutoTest.scenarios.YandexWeatherForecast;

public class YandexWeatherForecastTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    //Так как будет использован параметризированный тест, - перенес закрытие драйвера в afterAll
    // , в afterEach буду чистить куки
    @BeforeEach
    void beforeEach() {
        webDriverUtils.getDriver().get(WebAddresses.YANDEX_WEATHER.getWebAddress());
    }


    @Test
    void positiveOpenRostovOnDonCityPage() {
        YandexWeatherForecast.openCityPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait()
                , GeographicalLocations.ROSTOV_ON_DON);
        //В изначальном сценарии Selenium IDE была проверка на присутствие
        // , это мы сможем проверить через ожидаение появления
        webDriverUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//h1[@id='main_title' and contains(., ', "
                        + GeographicalLocations.ROSTOV_ON_DON.getLocationName() + "')]")));

        Assertions.assertTrue(webDriverUtils.getDriver()
                .findElement(By.xpath("//nav//li[position()=last()]//span[@class='breadcrumbs__title']")).getText()
                .contains(GeographicalLocations.ROSTOV_ON_DON.getLocationName()));
    }


    @Test
    void positiveCityMapPageTest() {
        YandexWeatherForecast.openCityMapPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait()
                , GeographicalLocations.ROSTOV_ON_DON);
        //В изначальном сценарии Selenium IDE была проверка на присутствие
        // , это мы сможем проверить через ожидаение появления
        webDriverUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='weather-maps__map']")));

        Assertions.assertEquals(GeographicalLocations.ROSTOV_ON_DON.getLocationName()
                , webDriverUtils.getDriver().findElement(By.xpath("//div[@class='weather-maps-fact']//h2")).getText());
    }


    @Test
    void negativeFindCityBySpecSymbolsTest() {
        YandexWeatherForecast.openCityPageByStringAndPressEnter(webDriverUtils.getDriver()
                , "~!@#$%^&*()_+}{|\"?><");

        webDriverUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='content content_ancient-design_yes']")));

        Assertions.assertEquals("По вашему запросу ничего не нашлось", webDriverUtils.getDriver()
                .findElement(By
                        .xpath("//div[@class='content content_ancient-design_yes']/h1[@class='title title_level_1']"))
                .getText());
    }


    @ParameterizedTest
    @CsvSource({
            "ROSTOV", "москва", "New-York"
    })
    void positiveFindCityViaInputNameAndPressEnter(String cityName) {
        YandexWeatherForecast.openCityPageByStringAndPressEnter(webDriverUtils.getDriver(), cityName);

        webDriverUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='content content_ancient-design_yes']")));

        Assertions.assertEquals("Вы искали: " + cityName, webDriverUtils.getDriver()
                .findElement(By
                        .xpath("//div[@class='content content_ancient-design_yes']//h1"))
                .getText());

        Assertions.assertTrue(webDriverUtils.getDriver().findElement(By
                .xpath("//div[@class='grid clearfix']//div[@class='grid__row clearfix']//li")).isDisplayed());
    }


    @AfterEach
    void afterEach() {
        webDriverUtils.getDriver().manage().deleteAllCookies();
    }


    @AfterAll
    static void afterAll() {
        webDriverUtils.getDriver().quit();
    }

}
