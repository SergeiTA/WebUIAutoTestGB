package org.webAutoTest.engine.models.crmGB;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CRMCreateProject {

    CRMNavigationBar crmNavigationBar;

    private final String xPathInputFieldProjectName = "//input[contains(@id, 'rm_project_name')]";

    private SelenideElement inputFieldProjectName = $(By.xpath(xPathInputFieldProjectName));
    private SelenideElement selectBusinessUnit = $(By.xpath("//select[contains(@id, 'crm_project_businessUnit')]"));
    private SelenideElement selectProjectCurator = $(By.xpath("//select[contains(@id, 'crm_project_curator')]"));
    private SelenideElement selectProjectSeniorManager = $(By.xpath("//select[contains(@id, 'crm_project_rp')]"));
    private SelenideElement selectProjectManager = $(By.xpath("//select[contains(@id, 'crm_project_manager')]"));
    private SelenideElement saveProjectButton = $(By.xpath("//button[contains(., 'Сохранить')]"));

    public CRMCreateProject() {
        this.crmNavigationBar = new CRMNavigationBar();
    }

    @Step("Ввести имя проекта в поле ввода")
    public CRMCreateProject inputProjectName(String projectName) {
        inputFieldProjectName.sendKeys(projectName);
        return this;
    }

    @Step("Выбрать бизнес юнит в выпадающем списке")
    public CRMCreateProject selectBusinessUnitByText(String selectText) {
        selectBusinessUnit.selectOptionContainingText(selectText);
        return this;
    }

    @Step("Выбрать куратора проекта в выпадающем списке")
    public CRMCreateProject selectProjectCuratorByText(String selectText) {
        selectProjectCurator.selectOptionContainingText(selectText);
        return this;
    }

    @Step("Выбрать руководителя проекта проекта в выпадающем списке")
    public CRMCreateProject selectProjectSeniorManagerByText(String selectText) {
        selectProjectSeniorManager.selectOptionContainingText(selectText);
        return this;
    }

    @Step("Выбрать менеджера проекта в выпадающем списке")
    public CRMCreateProject selectProjectManagerByText(String selectText) {
        selectProjectManager.selectOptionContainingText(selectText);
        return this;
    }

    @Step("Кликнуть по кнопке \"Сохранить\"")
    public void clickSaveProjectButton() {
        saveProjectButton.click();
    }
}
