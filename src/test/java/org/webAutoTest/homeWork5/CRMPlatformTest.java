package org.webAutoTest.homeWork5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.webAutoTest.engine.PropertiesReader;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.enums.PropertiesFields;
import org.webAutoTest.scenarios.CRMPlatform;
import java.util.UUID;

public class CRMPlatformTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        //Тут можно было бы добавить обвертку на getProperty
        // , но тогда прийдеться пересматривать применение static/не static
        CRMPlatform.login(
                PropertiesReader.getProperties().getProperty(PropertiesFields.CRM_USER_LOGIN.getPropertyFieldName())
                , PropertiesReader.getProperties().getProperty(PropertiesFields.CRM_USER_PASSWORD.getPropertyFieldName())
                , webDriverUtils.getDriver());
    }

    @Test
    void positiveCRMCreateProjectTest() throws InterruptedException {
       //Решил не переписыват и удалять старую реализацию, а преобразовать переиспользуемый набор методов
        String newProjectName = UUID.randomUUID().toString();
        CRMPlatform.createNewProject(newProjectName, webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait());


        webDriverUtils.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='customer-content']//h1[@class='user-name']")));

        Assertions.assertEquals(newProjectName
                , webDriverUtils.getDriver()
                        .findElement(By.xpath("//div[@class='customer-content']//h1[@class='user-name']")).getText());
    }

    @AfterEach
    void afterEach() {
        webDriverUtils.getDriver().quit();
    }


}
