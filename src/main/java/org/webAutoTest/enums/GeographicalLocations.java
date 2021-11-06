package org.webAutoTest.enums;

public enum GeographicalLocations {
    ROSTOV_ON_DON("Ростов-на-Дону", "Ростовская область"),
    MOSCOW("Москва", "Московская область");

    private final String locationName;
    private final String regionName;

    public String getLocationName() {
        return locationName;
    }

    public String getRegionName() {
        return regionName;
    }

    GeographicalLocations(String locationName, String regionName) {
        this.locationName = locationName;
        this.regionName = regionName;
    }
}
