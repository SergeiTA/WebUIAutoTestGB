package org.webAutoTest.scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;


public class CRMPlatform {

    public static void createNewProject(String projectName, WebDriver webDriver, WebDriverWait webDriverWait) {

        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//ul[@class='nav nav-multilevel main-menu']/li/a")));

        openAllProjects(webDriver);

        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//div[@class='pull-left btn-group icons-holder']//a[contains(., 'Создать проект')]")));

        webDriver
                .findElement(By.xpath("//div[@class='pull-left btn-group icons-holder']//a[contains(., 'Создать проект')]"))
                .click();

        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//input[contains(@id, 'rm_project_name')]")));

        webDriver
                .findElement(By.xpath("//input[contains(@id, 'rm_project_name')]"))
                .sendKeys(projectName);


        Select selectBusinessUnit = new Select( webDriver
                .findElement(By.xpath("//select[contains(@id, 'crm_project_businessUnit')]")));
        selectBusinessUnit.selectByVisibleText("Research & Development");

        Select selectProjectCurator = new Select( webDriver
                .findElement(By.xpath("//select[contains(@id, 'crm_project_curator')]")));
        selectProjectCurator.selectByVisibleText("Амелин Владимир");

        Select selectProjectSeniorManager = new Select( webDriver
                .findElement(By.xpath("//select[contains(@id, 'crm_project_rp')]")));
        selectProjectSeniorManager.selectByVisibleText("Авласёнок Денис");

        Select selectProjectManager = new Select( webDriver
                .findElement(By.xpath("//select[contains(@id, 'crm_project_manager')]")));
        selectProjectManager.selectByVisibleText("Амелин Владимир");

        webDriver
                .findElement(By.xpath("//button[contains(., 'Сохранить')]"))
                .click();
    }

    public static void login(String login, String password, WebDriver webDriver) {

        webDriver.get(WebAddresses.CRM_GB_MAIN.getWebAddress());

        webDriver
                .findElement(By.xpath("//input[@id='prependedInput']"))
                //Логин пока хардкожу, что бы не перекидывать папку с ресурсами из main в test
                .sendKeys(login);

        webDriver
                .findElement(By.xpath("//input[@id='prependedInput2']"))
                //Пароль пока хардкожу, что бы не перекидывать папку с ресурсами из main в test
                .sendKeys(password);

        webDriver
                .findElement(By.xpath("//button[@id='_submit']"))
                .click();
    }

    public static void openAllProjects(WebDriver webDriver) {
        Actions actions = new Actions(webDriver);
        List<WebElement> menuBar = webDriver
                .findElements(By.xpath("//ul[@class='nav nav-multilevel main-menu']/li/a"));

        actions
                .moveToElement(menuBar.stream().filter(e -> e.getText().equals("Проекты")).findFirst().get())
                .build().perform();

        webDriver
                .findElement(By.xpath("//a//span[contains(text(), 'Все проекты')]"))
                .click();
    }

}
