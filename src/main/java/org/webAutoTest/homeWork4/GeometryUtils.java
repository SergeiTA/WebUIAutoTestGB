package org.webAutoTest.homeWork4;

public class GeometryUtils {

    //Так как в задании явно не заданы ограничения на типы данных, посчитаю что числа могут быть дробными
    public double triangleSquareCalculator(float aSide, float bSide, float cSide) {

        if (aSide <= 0 || bSide <= 0 || cSide <= 0) {
            throw new RuntimeException("Side length can't be eql or lessen than ZERO");
        }

        float halfPerimeter = (aSide + bSide + cSide) / 2;
        double sqrt = Math.sqrt(halfPerimeter * (halfPerimeter - aSide) * (halfPerimeter - bSide) * (halfPerimeter - cSide));

        return Math.ceil(sqrt * Math.pow(10, 4)) / Math.pow(10, 4);

    }

}
