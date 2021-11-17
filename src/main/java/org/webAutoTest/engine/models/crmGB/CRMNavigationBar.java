package org.webAutoTest.engine.models.crmGB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class CRMNavigationBar {

    private final String xPathMenuItems = "//ul[@class='nav nav-multilevel main-menu']/li/a";

    private ElementsCollection menuItems = $$(By.xpath(xPathMenuItems));
    private SelenideElement allProjects = $(By.xpath("//a//span[contains(text(), 'Все проекты')]"));

    public ElementsCollection getMenuItems() {
        return menuItems;
    }

    @Step("Навести курсор мышки на элемент меню навигации")
    public CRMNavigationBar mouseOverOnItemFoundedByText(String text) {
        menuItems.findBy(Condition.text(text)).hover();
        return this;
    }

    @Step("Кликнуть элементу выпадающего меню навигации \"Все проекты\"")
    public void clickOnAllProjectsItem() {
        allProjects.click();
    }


}
