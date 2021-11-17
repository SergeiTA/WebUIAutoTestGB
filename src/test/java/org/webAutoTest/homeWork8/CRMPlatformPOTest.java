package org.webAutoTest.homeWork8;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogType;
import org.webAutoTest.engine.PropertiesReader;
import org.webAutoTest.engine.models.crmGB.*;
import org.webAutoTest.enums.PropertiesFields;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;
import java.util.UUID;

@Story("Зарегистрированный пользователь может создать проект")
public class CRMPlatformPOTest {

//    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    @BeforeEach
    void beforeEach() {
        Selenide.open(WebAddresses.CRM_GB_MAIN.getWebAddress());
        new CRMLoginPage()
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
        new CRMMainPage()
                .getCrmNavigationBar().mouseOverOnItemFoundedByText("Проекты")
                .clickOnAllProjectsItem();

        new CRMAllProjects().clickCreateProjectButton();

        String newProjectName = UUID.randomUUID().toString();
        new CRMCreateProject()
                .inputProjectName(newProjectName)
                .selectBusinessUnitByText("Research & Development")
                .selectProjectCuratorByText("Амелин Владимир")
                .selectProjectSeniorManagerByText("Авласёнок Денис")
                .selectProjectManagerByText("Амелин Владимир")
                .clickSaveProjectButton();

        Assertions.assertEquals(newProjectName, new CRMProjectPage().getProjectName());
    }


    @AfterEach
    void afterEach() {
        List<String> browserLogs = Selenide.getWebDriverLogs(LogType.BROWSER);
        for (String logItem: browserLogs) {
            Allure.addAttachment("Записть в логе браузера: ", logItem);
        }
        Selenide.closeWebDriver();
    }

}
