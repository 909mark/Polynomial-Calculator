package com.calculator.controller;

import com.calculator.model.Monomial;
import com.calculator.model.Polynomial;

import java.util.Arrays;
import java.util.List;

public class Builder {
    private Builder() {
        throw new IllegalStateException("Builder is a utility class!");
    }

    public static Polynomial polynomial(String input) {
        Polynomial res = new Polynomial();
        if (input == null || input.isBlank())
            return res;
        List<String> monoms = Arrays.stream(input.split("(?=[+-])")).toList();
        double coeff;
        for (String monom : monoms) {
            String[] splitMono = monom.split("X\\^");
            try {
                coeff = Double.parseDouble(splitMono[0]);
            } catch (NumberFormatException e) {
                coeff = "-".equals(splitMono[0]) ? -1.0 : 1.0;
            }
            PolMath.addMonom(res, new Monomial(
                    Integer.parseInt(splitMono[1]),
                    coeff)
            );
        }
        return res.refactor().sort();
    }

    public static Monomial monomial(Integer degree, Double coefficient) {
        if (degree == null || degree <= 0)
            degree = 0;
        if (coefficient == null || coefficient.isNaN() || coefficient.isInfinite())
            coefficient = 1.0;
        return new Monomial(degree, coefficient);
    }

}
