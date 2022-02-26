package com.example.controller.builder;

import com.example.controller.PolMath;
import com.example.model.Monomial;
import com.example.model.Polynomial;

import java.util.Arrays;
import java.util.List;

public class Builder {
    private Builder() {
        throw new IllegalStateException("Builder is a utility class!");
    }

    public static Polynomial polynomial(String input) {
        Polynomial res = new Polynomial();
        List<String> monoms = Arrays.stream(input.split("[+-]+")).filter(o -> !o.isEmpty()).toList();
        double coeff;
        for (String monom : monoms) {
            try {
                coeff = Double.parseDouble(monom.split("X\\^")[0]);
            } catch (NumberFormatException e) {
                coeff = 1.0;
            }
            PolMath.addMonom(res, new Monomial(
                    Integer.parseInt(monom.split("X\\^")[1]),
                    coeff)
            );
        }
        return res;
    }
}
