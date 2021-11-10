package org.webAutoTest.engine.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    public BasePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 5);
        PageFactory.initElements(webDriver, this);
    }
}
