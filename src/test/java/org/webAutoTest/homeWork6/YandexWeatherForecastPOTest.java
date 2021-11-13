package org.webAutoTest.homeWork6;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexNavBarSearchResultsPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityMapPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastMainPage;
import org.webAutoTest.enums.GeographicalLocations;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;

@Epic("Работа страницы прогноза погоды с привязкой к населенным пунктам")
public class YandexWeatherForecastPOTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        webDriverUtils.getDriver().get(WebAddresses.YANDEX_WEATHER.getWebAddress());
    }


    @Test
    @Feature("Открытие страницы прогноза погоды")
    @DisplayName("Открытие страницы прогноза погоды из результатов поиска")
    @Description("Открытие страницы прогноза погоды по результатам поиска через панель поиска")
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
    @Feature("Открытие карты прогноза погоды для населенного пункта")
    @DisplayName("Открытие проноза покгоды на карте населенного пункта")
    @Description("Открытие карты прогноза погоды по ссылке на странице населенного пункта")
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
    @Feature("Поиск прогноза погоды в панели поиска")
    @DisplayName("Негативный тест поиска по названию населенного пункта")
    @Description("Поиск по НЕ валдиным данным")
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

    @ParameterizedTest(name = "Позитивный тест поиска по названию населенного пункта {index}")
    @Feature("Поиск прогноза погоды в панели поиска")
    @DisplayName("Позитивный тест поиска по названию населенного пункта")
    @Description("Поиск по валдиным данным")
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
        List<LogEntry> browserLogs = webDriverUtils.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry logItem: browserLogs) {
            Allure.addAttachment("Записть в логе браузера: ", logItem.getMessage());
        }
        webDriverUtils.getDriver().manage().deleteAllCookies();
    }


    @AfterAll
    static void afterAll() {
        webDriverUtils.getDriver().quit();
    }

}
