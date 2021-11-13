package org.webAutoTest.engine.models.crmGB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMProjectPage extends BasePageObject {

    private final String xPathProjectName = "//div[@class='customer-content']//h1[@class='user-name']";

    @FindBy(xpath = xPathProjectName)
    WebElement projectName;


    public CRMProjectPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step("Проверяем имя проекта")
    public WebElement getProjectName() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath(xPathProjectName)));
        return projectName;
    }
}
