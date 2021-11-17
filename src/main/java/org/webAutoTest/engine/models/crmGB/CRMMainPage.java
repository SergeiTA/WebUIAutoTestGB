package org.webAutoTest.engine.models.crmGB;

public class CRMMainPage {

    private CRMNavigationBar crmNavigationBar;

    public CRMMainPage() {
        this.crmNavigationBar = new CRMNavigationBar();
    }

    public CRMNavigationBar getCrmNavigationBar() {
        return crmNavigationBar;
    }
}
