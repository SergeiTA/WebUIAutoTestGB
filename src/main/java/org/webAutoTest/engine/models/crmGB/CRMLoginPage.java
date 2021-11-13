package org.webAutoTest.engine.models.crmGB;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMLoginPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='prependedInput']")
    private WebElement loginInputField;

    @FindBy(xpath = "//input[@id='prependedInput2']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@id='_submit']")
    private WebElement submitButton;

    public CRMLoginPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    public WebElement getLoginInputField() {
        return loginInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    @Step("Заполнить поле ввода \"Имя пользователя\"")
    public CRMLoginPage fillLoginInputField(String text) {
        loginInputField.sendKeys(text);
        return this;
    }

    @Step("Заполнить поле ввода \"Пароль\"")
    public CRMLoginPage fillPasswordInputField(String text) {
        passwordInputField.sendKeys(text);
        return this;
    }

    @Step("Кликнуть по кнопке \"Войти\"")
    public void clickSubmitButton() {
        submitButton.click();
    }

}
