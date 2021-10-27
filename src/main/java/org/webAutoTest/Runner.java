package org.webAutoTest;

import org.webAutoTest.scenarios.CRMPlatform;
import org.webAutoTest.scenarios.YandexWeatherForecast;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        //Запускаем сценарий окрытия страницы города в прогнозе погоды
        YandexWeatherForecast.openCityPage();

        //Запускаем сценарий окрытия карты города с функционалом прогноза погоды
        YandexWeatherForecast.openCityMapPage();

        //Запускаем сценарий создание проекта в CRM
        CRMPlatform.createNewProject();
    }

}
