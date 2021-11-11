package org.webAutoTest.homeWork6;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexNavBarSearchResultsPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityMapPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastMainPage;
import org.webAutoTest.enums.GeographicalLocations;
import org.webAutoTest.enums.WebAddresses;

public class YandexWeatherForecastPOTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        webDriverUtils.getDriver().get(WebAddresses.YANDEX_WEATHER.getWebAddress());
    }


    @Test
    void positiveOpenRostovOnDonCityPagePOTest() {
        new YandexWeatherForecastMainPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .yandexNavigationBar.fillSearchInputField(GeographicalLocations.ROSTOV_ON_DON.getLocationName())
                .clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations.ROSTOV_ON_DON);

        Assertions.assertTrue(
                new YandexWeatherForecastCityPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                    .getTextCityCardTitle()
                    .contains(GeographicalLocations.ROSTOV_ON_DON.getLocationName()));
    }

    @Test
    void positiveCityMapPagePOTest() {
        new YandexWeatherForecastMainPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .yandexNavigationBar.fillSearchInputField(GeographicalLocations.ROSTOV_ON_DON.getLocationName())
                .clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations.ROSTOV_ON_DON);

        new YandexWeatherForecastCityPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .clickOnShowOnTheMap();

        new YandexWeatherForecastCityMapPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .isMapLayerVisible();

        Assertions.assertEquals(GeographicalLocations.ROSTOV_ON_DON.getLocationName()
                , new YandexWeatherForecastCityMapPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                        .getCityDescriptionText());
    }


    @Test
    void negativeFindCityBySpecSymbolsPOTest() {
        new YandexWeatherForecastMainPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .yandexNavigationBar.fillSearchInputField("~!@#$%^&*()_+}{|\"?><")
                .pressEnterOnSearchInputField();

        new YandexNavBarSearchResultsPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .isResultsAreaDisplayed();

        Assertions.assertEquals("По вашему запросу ничего не нашлось"
                , new YandexNavBarSearchResultsPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                        .getResultsTitleText());

    }

    @ParameterizedTest
    @CsvSource({
            "ROSTOV", "москва", "New-York"
    })
    void positiveFindCityViaInputNameAndPressEnterPOTest(String cityName) {
        new YandexWeatherForecastMainPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .yandexNavigationBar.fillSearchInputField(cityName)
                .pressEnterOnSearchInputField();

        new YandexNavBarSearchResultsPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .isResultsAreaDisplayed().isResultsRowsDisplayed();

        Assertions.assertEquals("Вы искали: " + cityName
                , new YandexNavBarSearchResultsPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                        .getResultsAreaText());
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
