package org.webAutoTest.engine.models.crmGB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMCreateProject extends BasePageObject {

    //Это поле можно былобы вынести в класс родителя
    // , но для этого нужно знатоь структуру сайта, иначе действие будет лишнее
    CRMNavigationBar crmNavigationBar;

    private final String xPathInputFieldProjectName = "//input[contains(@id, 'rm_project_name')]";

    @FindBy(xpath = xPathInputFieldProjectName)
    private WebElement inputFieldProjectName;

    @FindBy(xpath = "//select[contains(@id, 'crm_project_businessUnit')]")
    private WebElement selectBusinessUnit;

    @FindBy(xpath = "//select[contains(@id, 'crm_project_curator')]")
    private WebElement selectProjectCurator;

    @FindBy(xpath = "//select[contains(@id, 'crm_project_rp')]")
    private WebElement selectProjectSeniorManager;

    @FindBy(xpath = "//select[contains(@id, 'crm_project_manager')]")
    private WebElement selectProjectManager;

    @FindBy(xpath = "//button[contains(., 'Сохранить')]")
    private WebElement saveProjectButton;

    public CRMCreateProject(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
        this.crmNavigationBar = new CRMNavigationBar(webDriver, webDriverWait);
    }

    @Step("Ввести имя проекта в поле ввода")
    public CRMCreateProject inputProjectName(String projectName) {
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath(xPathInputFieldProjectName)));
        inputFieldProjectName.sendKeys(projectName);
        return this;
    }

    @Step("Выбрать бизнес юнит в выпадающем списке")
    public CRMCreateProject selectBusinessUnitByText(String selectText) {
        new Select(selectBusinessUnit).selectByVisibleText(selectText);
        return this;
    }

    @Step("Выбрать куратора проекта в выпадающем списке")
    public CRMCreateProject selectProjectCuratorByText(String selectText) {
        new Select(selectProjectCurator).selectByVisibleText(selectText);
        return this;
    }

    @Step("Выбрать руководителя проекта проекта в выпадающем списке")
    public CRMCreateProject selectProjectSeniorManagerByText(String selectText) {
        new Select(selectProjectSeniorManager).selectByVisibleText(selectText);
        return this;
    }

    @Step("Выбрать менеджера проекта в выпадающем списке")
    public CRMCreateProject selectProjectManagerByText(String selectText) {
        new Select(selectProjectManager).selectByVisibleText(selectText);
        return this;
    }

    @Step("Кликнуть по кнопке \"Сохранить\"")
    public void clickSaveProjectButton() {
        saveProjectButton.click();
    }
}
