package org.webAutoTest.engine.models.crmGB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CRMProjectPage {

    private SelenideElement projectName = $(By.xpath("//div[@class='customer-content']//h1[@class='user-name']"));

    @Step("Проверяем имя проекта")
    public String getProjectName() {
        return projectName.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
    }
}
