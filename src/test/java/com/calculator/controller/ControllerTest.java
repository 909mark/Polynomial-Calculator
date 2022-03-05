package com.calculator.controller;

import com.calculator.constants.CONSTANTS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    public static List<List<Object>> data() {
        return Arrays.asList(
                Arrays.asList("X^2", true),
                Arrays.asList("X^26-", false),
                Arrays.asList("-.56X^100", true),
                Arrays.asList("X^03", true),
                Arrays.asList("X^-8", false),
                Arrays.asList("+1.00X^2+5.6X^110", true),
                Arrays.asList("X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2+X^2", false),
                Arrays.asList("12", false),
                Arrays.asList("12X^5.6", false),
                Arrays.asList("", false),
                Arrays.asList("45.88X^", false),
                Arrays.asList("-6331.50X^147-67.00X^110-321.60X^109+37.19X^101+545076.00X^60+614.25X^49+4583.25X^48" +
                        "+5768.00X^23+27686.40X^22-3201.24X^14+6.50X^12+79.70X^11+232.80X^10" +
                        "-3.61X^3-26.92X^2", true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Should add two polynomials")
    void parseInput(List<Object> data) {
        assertEquals(data.get(1), Pattern.compile(CONSTANTS.INPUT_PATTERN).matcher((String) data.get(0)).matches());
    }
}