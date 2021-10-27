package org.webAutoTest.scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.webAutoTest.engine.WebDriverUtils;
import org.webAutoTest.enums.WebAddresses;

import java.util.List;
import java.util.UUID;

public class CRMPlatform {

    static WebDriverUtils webDriverUtils = new WebDriverUtils();

    public static void createNewProject() throws InterruptedException {

        //Креды пока хардкожу, что бы не перекидывать папку с ресурсами из main в test
        login("Applanatest1", "Student2020!");

        Thread.sleep(3000);

        openAllProjects();

        Thread.sleep(5000);

        webDriverUtils.getDriver()
                .findElement(By.xpath("//div[@class='pull-left btn-group icons-holder']//a[contains(., 'Создать проект')]"))
                .click();

        Thread.sleep(5000);

        webDriverUtils.getDriver()
                .findElement(By.xpath("//input[contains(@id, 'rm_project_name')]"))
                .sendKeys(UUID.randomUUID().toString());


        Select selectBusinessUnit = new Select( webDriverUtils.getDriver()
                .findElement(By.xpath("//select[contains(@id, 'crm_project_businessUnit')]")));
        selectBusinessUnit.selectByVisibleText("Research & Development");

        Select selectProjectCurator = new Select( webDriverUtils.getDriver()
                .findElement(By.xpath("//select[contains(@id, 'crm_project_curator')]")));
        selectProjectCurator.selectByVisibleText("Амелин Владимир");

        Select selectProjectSeniorManager = new Select( webDriverUtils.getDriver()
                .findElement(By.xpath("//select[contains(@id, 'crm_project_rp')]")));
        selectProjectSeniorManager.selectByVisibleText("Авласёнок Денис");

        Select selectProjectManager = new Select( webDriverUtils.getDriver()
                .findElement(By.xpath("//select[contains(@id, 'crm_project_manager')]")));
        selectProjectManager.selectByVisibleText("Амелин Владимир");

        webDriverUtils.getDriver()
                .findElement(By.xpath("//button[contains(., 'Сохранить')]"))
                .click();

        Thread.sleep(20000);

        webDriverUtils.getDriver().quit();

        //Далее шли два сравенения по сценарию, пока сюда не пишем
    }

    private static void login(String login, String password) {

        webDriverUtils.getDriver().get(WebAddresses.CRM_GB_MAIN.getWebAddress());

        webDriverUtils.getDriver()
                .findElement(By.xpath("//input[@id='prependedInput']"))
                //Логин пока хардкожу, что бы не перекидывать папку с ресурсами из main в test
                .sendKeys(login);

        webDriverUtils.getDriver()
                .findElement(By.xpath("//input[@id='prependedInput2']"))
                //Пароль пока хардкожу, что бы не перекидывать папку с ресурсами из main в test
                .sendKeys(password);

        webDriverUtils.getDriver()
                .findElement(By.xpath("//button[@id='_submit']"))
                .click();
    }

    private static void openAllProjects() {
        Actions actions = new Actions(webDriverUtils.getDriver());
        List<WebElement> menuBar = webDriverUtils
                .getDriver()
                .findElements(By.xpath("//ul[@class='nav nav-multilevel main-menu']/li/a"));

        actions
                .moveToElement(menuBar.stream().filter(e -> e.getText().equals("Проекты")).findFirst().get())
                .build().perform();

        webDriverUtils.getDriver()
                .findElement(By.xpath("//a//span[contains(text(), 'Все проекты')]"))
                .click();
    }

}
