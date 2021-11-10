package org.webAutoTest.engine.models.crmGB;

import org.openqa.selenium.WebDriver;
import org.webAutoTest.engine.models.BasePageObject;

public class CRMMainPage extends BasePageObject {

    CRMNavigationBar crmNavigationBar;

    public CRMMainPage(WebDriver webDriver) {
        super(webDriver);
        this.crmNavigationBar = new CRMNavigationBar(webDriver);
    }

    public CRMNavigationBar getCrmNavigationBar() {
        return crmNavigationBar;
    }
}
