package org.webAutoTest.homeWork6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webAutoTest.engine.PropertiesReader;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.engine.models.crmGB.*;
import org.webAutoTest.enums.PropertiesFields;
import org.webAutoTest.enums.WebAddresses;

import java.util.UUID;

public class CRMPlatformPOTest {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        webDriverUtils.getDriver().get(WebAddresses.CRM_GB_MAIN.getWebAddress());
        new CRMLoginPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .fillLoginInputField(PropertiesReader.getProperties()
                        .getProperty(PropertiesFields.CRM_USER_LOGIN.getPropertyFieldName()))
                .fillPasswordInputField(PropertiesReader
                        .getProperties().getProperty(PropertiesFields.CRM_USER_PASSWORD.getPropertyFieldName()))
                .clickSubmitButton();
    }

    @Test
    void positiveCRMCreateProjectPOTest() {
        new CRMMainPage(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .getCrmNavigationBar().mouseOverOnItemFoundedByText("Проекты")
                .clickOnAllProjectsItem();

        new CRMAllProjects(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait()).clickCreateProjectButton();

        String newProjectName = UUID.randomUUID().toString();
        new CRMCreateProject(webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait())
                .inputProjectName(newProjectName)
                .selectBusinessUnitByText("Research & Development")
                .selectProjectCuratorByText("Амелин Владимир")
                .selectProjectSeniorManagerByText("Авласёнок Денис")
                .selectProjectManagerByText("Амелин Владимир")
                .clickSaveProjectButton();

        Assertions.assertEquals(newProjectName
                , new CRMProjectPage(
                        webDriverUtils.getDriver(), webDriverUtils.getWebDriverWait()).getProjectName().getText());
    }


    @AfterEach
    void afterEach() {
        webDriverUtils.getDriver().quit();
    }

}
