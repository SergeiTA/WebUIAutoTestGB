package org.webAutoTest.engine.models.crmGB;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CRMLoginPage {

    private SelenideElement loginInputField = $(By.xpath("//input[@id='prependedInput']"));
    private SelenideElement passwordInputField = $(By.xpath("//input[@id='prependedInput2']"));
    private SelenideElement submitButton = $(By.xpath("//button[@id='_submit']"));

    public SelenideElement getLoginInputField() {
        return loginInputField;
    }

    public SelenideElement getPasswordInputField() {
        return passwordInputField;
    }

    public SelenideElement getSubmitButton() {
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
