package org.webAutoTest.homeWork8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.logging.LogType;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexNavBarSearchResultsPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityMapPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastCityPage;
import org.webAutoTest.engine.models.yandexWeatherForecast.YandexWeatherForecastMainPage;
import org.webAutoTest.enums.GeographicalLocations;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;

@Epic("Работа страницы прогноза погоды с привязкой к населенным пунктам")
public class YandexWeatherForecastPOTest {

    @BeforeEach
    void beforeEach() {
        Selenide.open(WebAddresses.YANDEX_WEATHER.getWebAddress());
    }

    @Test
    @Feature("Открытие страницы прогноза погоды")
    @DisplayName("Открытие страницы прогноза погоды из результатов поиска")
    @Description("Открытие страницы прогноза погоды по результатам поиска через панель поиска")
    void positiveOpenRostovOnDonCityPagePOTest() {
        new YandexWeatherForecastMainPage()
                .yandexNavigationBar.fillSearchInputField(GeographicalLocations.ROSTOV_ON_DON.getLocationName())
                .clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations.ROSTOV_ON_DON);

        Assertions.assertTrue(
                new YandexWeatherForecastCityPage()
                    .getTextCityCardTitle()
                    .contains(GeographicalLocations.ROSTOV_ON_DON.getLocationName()));
    }

    @Test
    @Feature("Открытие карты прогноза погоды для населенного пункта")
    @DisplayName("Открытие проноза покгоды на карте населенного пункта")
    @Description("Открытие карты прогноза погоды по ссылке на странице населенного пункта")
    void positiveCityMapPagePOTest() {
        new YandexWeatherForecastMainPage()
                .yandexNavigationBar.fillSearchInputField(GeographicalLocations.ROSTOV_ON_DON.getLocationName())
                .clickOnSuggestionFoundedByGeographicalLocation(GeographicalLocations.ROSTOV_ON_DON);

        new YandexWeatherForecastCityPage()
                .clickOnShowOnTheMap();

        new YandexWeatherForecastCityMapPage()
                .getMapLayer().shouldBe(Condition.visible);

        Assertions.assertEquals(GeographicalLocations.ROSTOV_ON_DON.getLocationName()
                , new YandexWeatherForecastCityMapPage()
                        .getCityDescriptionText());
    }


    @Test
    @Feature("Поиск прогноза погоды в панели поиска")
    @DisplayName("Негативный тест поиска по названию населенного пункта")
    @Description("Поиск по НЕ валдиным данным")
    void negativeFindCityBySpecSymbolsPOTest() {
        new YandexWeatherForecastMainPage()
                .yandexNavigationBar.fillSearchInputField("~!@#$%^&*()_+}{|\"?><")
                .pressEnterOnSearchInputField();

        new YandexNavBarSearchResultsPage()
                .getResultsArea().shouldBe(Condition.visible);

        Assertions.assertEquals("По вашему запросу ничего не нашлось"
                , new YandexNavBarSearchResultsPage()
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
        new YandexWeatherForecastMainPage()
                .yandexNavigationBar.fillSearchInputField(cityName)
                .pressEnterOnSearchInputField();

        new YandexNavBarSearchResultsPage()
                .getResultsArea().shouldBe(Condition.visible);

        new YandexNavBarSearchResultsPage()
                .getResultsRows().shouldBe(Condition.visible);

        Assertions.assertEquals("Вы искали: " + cityName
                , new YandexNavBarSearchResultsPage()
                        .getResultsAreaText());
    }

    @AfterEach
    void afterEach() {
        List<String> browserLogs = Selenide.getWebDriverLogs(LogType.BROWSER);
        for (String logItem: browserLogs) {
            Allure.addAttachment("Записть в логе браузера: ", logItem);
        }
        Selenide.clearBrowserCookies();
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }

}
