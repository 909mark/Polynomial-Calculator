package com.calculator.model;

import com.calculator.constants.CONSTANTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polynomial {
    private final List<Monomial> terms;

    public Polynomial() {
        this.terms = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (terms.isEmpty())
            return "0";
        StringBuilder sb = new StringBuilder();
        this.terms.forEach(
                o -> sb.append(String.format("%+.2f", o.getCoeff()))
                        .append("X^")
                        .append(o.getDegree())
        );
        return sb.toString();
    }

    public List<Monomial> getTerms() {
        return terms;
    }

    public Polynomial sort() {
        Collections.sort(this.terms);
        return this;
    }

    public Double firstCoefficient() {
        return terms.get(0).getCoeff();
    }

    public Integer firstDegree() {
        if (terms.isEmpty())
            return -1;
        return terms.get(0).getDegree();
    }

    public Polynomial refactor() {
        terms.removeIf(monomial -> Math.abs(monomial.getCoeff()) < CONSTANTS.ZERO);
        return this;
    }

    public boolean isEmpty() {
        return terms.isEmpty();
    }

}
