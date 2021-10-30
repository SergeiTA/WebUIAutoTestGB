package org.webAutoTest.enums;

public enum WebAddresses {
    YANDEX_MAIN_FULL("https://yandex.ru"),
    YANDEX_WEATHER("https://yandex.ru/pogoda"),
    CRM_GB_MAIN("https://crm.geekbrains.space");


    private final String webAddress;


    WebAddresses(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

}
