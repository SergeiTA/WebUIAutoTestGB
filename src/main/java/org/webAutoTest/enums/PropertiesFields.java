package org.webAutoTest.enums;

public enum PropertiesFields {
    CRM_USER_LOGIN("CRMLogin"),
    CRM_USER_PASSWORD("CRMPassword");

    private final String propertyFieldName;

    public String getPropertyFieldName() {
        return propertyFieldName;
    }

    PropertiesFields(String propertyFieldName) {
        this.propertyFieldName = propertyFieldName;
    }


}
