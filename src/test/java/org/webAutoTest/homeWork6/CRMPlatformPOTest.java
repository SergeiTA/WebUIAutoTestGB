package org.webAutoTest.homeWork6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.webAutoTest.engine.PropertiesReader;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.engine.models.crmGB.CRMAllProjects;
import org.webAutoTest.engine.models.crmGB.CRMCreateProject;
import org.webAutoTest.engine.models.crmGB.CRMLoginPage;
import org.webAutoTest.engine.models.crmGB.CRMMainPage;
import org.webAutoTest.enums.PropertiesFields;
import org.webAutoTest.enums.WebAddresses;

import java.util.UUID;

public class CRMPlatformPOTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        webDriverUtils.getDriver().get(WebAddresses.CRM_GB_MAIN.getWebAddress());
        new CRMLoginPage(webDriverUtils.getDriver())
                .fillLoginInputField(PropertiesReader.getProperties()
                        .getProperty(PropertiesFields.CRM_USER_LOGIN.getPropertyFieldName()))
                .fillPasswordInputField(PropertiesReader
                        .getProperties().getProperty(PropertiesFields.CRM_USER_PASSWORD.getPropertyFieldName()))
                .clickSubmitButton();
    }

    @Test
    void positiveCRMCreateProjectPOTest() {
        new CRMMainPage(webDriverUtils.getDriver())
                .getCrmNavigationBar().mouseOverOnItemFoundedByText("Проекты")
                .clickOnAllProjectsItem();

        new CRMAllProjects(webDriverUtils.getDriver()).clickCreateProjectButton();

        String newProjectName = UUID.randomUUID().toString();
        new CRMCreateProject(webDriverUtils.getDriver())
                .inputProjectName(newProjectName)
                .selectBusinessUnitByText("Research & Development")
                .selectProjectCuratorByText("Амелин Владимир")
                .selectProjectSeniorManagerByText("Авласёнок Денис")
                .selectProjectManagerByText("Амелин Владимир")
                .clickSaveProjectButton();

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
