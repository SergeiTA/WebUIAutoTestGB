package org.webAutoTest.engine.models.crmGB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMAllProjects extends BasePageObject {

    private final String xPathCreateProjectButton
            = "//div[@class='pull-left btn-group icons-holder']//a[contains(., 'Создать проект')]";

    @FindBy(xpath = xPathCreateProjectButton)
    WebElement createProjectButton;

    public CRMAllProjects(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public void clickCreateProjectButton() {
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath(xPathCreateProjectButton)));
        createProjectButton.click();
    }
}
