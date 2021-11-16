package org.webAutoTest.engine.models.crmGB;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CRMAllProjects {

    private final String xPathCreateProjectButton
            = "//div[@class='pull-left btn-group icons-holder']//a[contains(., 'Создать проект')]";
    private SelenideElement createProjectButton = $(By.xpath(xPathCreateProjectButton));

    @Step("Кликнуть на кнопку \"Создать\"")
    public void clickCreateProjectButton() {
        createProjectButton.click();
    }
}
