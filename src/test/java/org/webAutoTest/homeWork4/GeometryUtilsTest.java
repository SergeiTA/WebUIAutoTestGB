package org.webAutoTest.homeWork4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GeometryUtilsTest {
    static GeometryUtils geometryUtils;

    @BeforeAll
    static void createNewGeometryUtils() {
        geometryUtils = new GeometryUtils();
    }

    @ParameterizedTest
    @CsvSource({
            "7F, 2F, 8F, 6.437196594791867",
            "15.2F, 14.3F, 7.5F, 53.10847390012257"})
    void differentSideTriangleSqrPositiveTest(float aSide, float bSide, float cSide, double sqrResult) {
        Assertions.assertEquals( Math.ceil(sqrResult * Math.pow(10, 4)) / Math.pow(10, 4)
                , geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

    @ParameterizedTest
    @CsvSource({
            "3F, 7.2F, 7.2F, 10.563025134874952",
            "9F, 17.0F, 17.0F, 73.77118339839751"})
    void isoscelesTriangleSqrPositiveTest(float aSide, float bSide, float cSide, double sqrResult) {
        Assertions.assertEquals( Math.ceil(sqrResult * Math.pow(10, 4)) / Math.pow(10, 4)
                , geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

    @ParameterizedTest
    @CsvSource({
            "5F, 12F, 13F, 30",
            "150.25F, 150.25F, 212.485587747F, 11287.532945688354"})
    void rightTriangleSqrPositiveTest(float aSide, float bSide, float cSide, double sqrResult) {
        Assertions.assertEquals( Math.ceil(sqrResult * Math.pow(10, 4)) / Math.pow(10, 4)
                , geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

    @ParameterizedTest
    @CsvSource({
            "0F, 12F, 13F",
            "150.25F, 0F, 212.485587747F",
            "5F, 12F, 0F"})
    void oneSideNullNegativeTest(float aSide, float bSide, float cSide) {
        Assertions.assertThrows(RuntimeException.class, () -> geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

    @Test
    void allSidesNullNegativeTest() {
        Assertions.assertThrows(RuntimeException.class, () -> geometryUtils.triangleSquareCalculator(0, 0, 0));
    }

    @ParameterizedTest
    @CsvSource({
            "-4F, 10F, 12F",
            "150.09F, -147.99F, 212.485587747F",
            "7F, 15F, -19.01F"})
    void oneSideNegativeNegativeTest(float aSide, float bSide, float cSide) {
        Assertions.assertThrows(RuntimeException.class, () -> geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

    @ParameterizedTest
    @CsvSource({
            "-4F, -10F, -12F",
            "-150.09F, -147.99F, -212.485587747F",
            "-7F, -15F, -19.01F"})
    void allSidesNegativeNegativeTest(float aSide, float bSide, float cSide) {
        Assertions.assertThrows(RuntimeException.class, () -> geometryUtils.triangleSquareCalculator(aSide, bSide, cSide));
    }

}
