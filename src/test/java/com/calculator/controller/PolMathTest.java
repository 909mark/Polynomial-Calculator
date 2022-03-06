package com.calculator.controller;

import com.calculator.model.Polynomial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolMathTest {

    // data for testing addition, subtraction, multiplication, derivation, integration
    // first two elements are input polynomials
    public static List<List<String>> data() {
        return Arrays.asList(
                Arrays.asList(
                        "0.2X^4+5.6X^1-X^100+.88X^23",
                        "-8.88X^2+74.881X^6+34X^45-X^7",
                        "-1.00X^100+34.00X^45+0.88X^23-1.00X^7+74.88X^6+0.20X^4-8.88X^2+5.60X^1",
                        "-1.00X^100-34.00X^45+0.88X^23+1.00X^7-74.88X^6+0.20X^4+8.88X^2+5.60X^1",
                        "-34.00X^145+1.00X^107-74.88X^106+8.88X^102+29.92X^68+6.80X^49+190.40X^46-0.88X^30+65.90X^29" +
                                "-7.81X^25-0.20X^11+14.98X^10-5.60X^8+419.33X^7-1.78X^6" +
                                "-49.73X^3",
                        "-100.00X^99+20.24X^22+0.80X^3+5.60X^0",
                        "-0.01X^101+0.04X^24+0.04X^5+2.80X^2"),
                Arrays.asList(
                        "94.5X^48+X^11+4.8X^10-0X^8-.555X^2",
                        "5768X^12-67X^99+48.5X^0+6.50X^1",
                        "-67.00X^99+94.50X^48+5768.00X^12+1.00X^11+4.80X^10-0.56X^2+6.50X^1+48.50X^0",
                        "+67.00X^99+94.50X^48-5768.00X^12+1.00X^11+4.80X^10-0.56X^2-6.50X^1-48.50X^0",
                        "-6331.50X^147-67.00X^110-321.60X^109+37.19X^101+545076.00X^60+614.25X^49+4583.25X^48" +
                                "+5768.00X^23+27686.40X^22-3201.24X^14+6.50X^12+79.70X^11+232.80X^10" +
                                "-3.61X^3-26.92X^2",
                        "+4536.00X^47+11.00X^10+48.00X^9-1.11X^1",
                        "+1.93X^49+0.08X^12+0.44X^11-0.19X^3"),
                Arrays.asList(
                        "", "", "0", "0", "0", "0", "0"
                ),
                Arrays.asList(
                        "X^2-3X^0", "", "+1.00X^2-3.00X^0", "+1.00X^2-3.00X^0", "0", "+2.00X^1", "+0.33X^3-3.00X^1"
                ),
                Arrays.asList(
                        "5X^2+3X^0-58.77X^12-15.4X^25",
                        "5X^2+6X^0-58.769X^12-15.4X^25",
                        "-30.80X^25-117.54X^12+10.00X^2+9.00X^0",
                        "-0.00X^12-3.00X^0",
                        "+237.16X^50+1810.10X^37-154.00X^27-138.60X^25+3453.85X^24-587.69X^14-528.93X^12+25.00X^4+45.00X^2+18.00X^0",
                        "-385.00X^24-705.24X^11+10.00X^1",
                        "-0.59X^26-4.52X^13+1.67X^3+3.00X^1"
                )
        );
    }

    public static List<List<String>> dataDivision() {
        return Arrays.asList(
                Arrays.asList(
                        "X^2+5X^1-2X^0", "X^1-3X^0", "+1.00X^1+8.00X^0", "+22.00X^0"
                ),
                Arrays.asList(
                        "67X^99+94X^48-5768X^12+X^11+5X^10-0X^2-7X^1-49X^0",
                        "94X^48+X^11+5X^10-0X^8-1X^2",
                        "+0.71X^51-0.01X^14-0.04X^13+0.01X^5+1.00X^0",
                        "+0.01X^25+0.08X^24+0.19X^23-0.02X^16-0.08X^15-5768.00X^12+0.01X^7+1.00X^2-7.00X^1-49.00X^0"
                ),
                Arrays.asList(
                        "", "X^2+1X^0", "0", "0"
                ),
                Arrays.asList(
                        "5.66X^1+X^4", "", "0", "0"
                ),
                Arrays.asList(
                        "X^2+3X^4", "X^6-2X^5", "0", "+3.00X^4+1.00X^2"
                ),
                Arrays.asList(
                        "1.3X^2-2.2X^1+X^0", "X^1-1X^0", "+1.30X^1-0.90X^0", "+0.10X^0"
                ),
                Arrays.asList(
                        "2.5X^4-3X^3-15X^2+32X^1-2X^0", "X^2-4X^1-12X^0",
                        "+2.50X^2+7.00X^1+43.00X^0", "+288.00X^1+514.00X^0"
                )
        );
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should add two polynomials")
    void add(List<String> data) {
        assertEquals(data.get(2), PolMath.add(Builder.polynomial(data.get(0)), Builder.polynomial(data.get(1))).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should subtract two polynomials")
    void subtract(List<String> data) {
        assertEquals(data.get(3), PolMath.subtract(Builder.polynomial(data.get(0)), Builder.polynomial(data.get(1))).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should multiply two polynomials")
    void multiply(List<String> data) {
        assertEquals(data.get(4), PolMath.multiply(Builder.polynomial(data.get(0)), Builder.polynomial(data.get(1))).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should calculate derivative of the first polynomials")
    void derivativeOf(List<String> data) {
        assertEquals(data.get(5), PolMath.derivativeOf(Builder.polynomial(data.get(0))).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should integrate the first polynomials")
    void integralOf(List<String> data) {
        assertEquals(data.get(6), PolMath.integralOf(Builder.polynomial(data.get(0))).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "dataDivision")
    @DisplayName("Should divide the two polynomials")
    void divide(List<String> data) {
        List<Polynomial> result = PolMath.divide(Builder.polynomial(data.get(0)), Builder.polynomial(data.get(1)));
        assertEquals(data.get(2), result.get(0).toString());
        assertEquals(data.get(3), result.get(1).toString());
    }
}