package org.webAutoTest.engine.models.crmGB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

import java.util.List;

//К сожалению не могу себе позволить сейчас глубоко расписывать универсальную структуру вложенных меню/элементов
// , на работе близиться релиз, по этому аврал. Выберу более лаконичный и менее универсальный путь описания
public class CRMNavigationBar extends BasePageObject {

    private final Actions action;

    private final String xPathMenuItems = "//ul[@class='nav nav-multilevel main-menu']/li/a";

    @FindBy(xpath = xPathMenuItems)
    private List<WebElement> menuItems;

    @FindBy(xpath = "//a//span[contains(text(), 'Все проекты')]")
    private WebElement allProjects;

    public CRMNavigationBar(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
        action = new Actions(webDriver);
    }

    public List<WebElement> getMenuItems() {
        return menuItems;
    }

    @Step("Навести курсор мышки на элемент меню навигации")
    public CRMNavigationBar mouseOverOnItemFoundedByText(String text) {
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath(xPathMenuItems)));

        action
                .moveToElement(menuItems.stream().filter(e -> e.getText().equals(text)).findFirst().get())
                .build().perform();
        return this;
    }

    @Step("Кликнуть элементу выпадающего меню навигации \"Все проекты\"")
    public void clickOnAllProjectsItem() {
        allProjects.click();
    }


}
