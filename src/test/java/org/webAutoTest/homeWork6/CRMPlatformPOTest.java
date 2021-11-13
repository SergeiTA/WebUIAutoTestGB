package org.webAutoTest.homeWork6;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.webAutoTest.engine.PropertiesReader;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.engine.models.crmGB.*;
import org.webAutoTest.enums.PropertiesFields;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;
import java.util.UUID;

@Story("Зарегистрированный пользователь может создать проект")
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
    @Feature("Создание проекта")
    @DisplayName("Создание нового проекта")
    @Description("Создание нового проекта зарегистрированным пользователем с шага авторизации пользователя")
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
        List<LogEntry> browserLogs = webDriverUtils.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry logItem: browserLogs) {
            Allure.addAttachment("Записть в логе браузера: ", logItem.getMessage());
        }
        webDriverUtils.getDriver().quit();
    }

}
