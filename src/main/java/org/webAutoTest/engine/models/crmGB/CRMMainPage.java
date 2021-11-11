package org.webAutoTest.engine.models.crmGB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMMainPage extends BasePageObject {

    private CRMNavigationBar crmNavigationBar;

    public CRMMainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
        this.crmNavigationBar = new CRMNavigationBar(webDriver, webDriverWait);
    }

    public CRMNavigationBar getCrmNavigationBar() {
        return crmNavigationBar;
    }
}
