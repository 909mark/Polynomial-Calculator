package com.calculator.model;

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
        for (Monomial monomial : terms) {
            if (Math.abs(monomial.getCoeff()) > 0.00001)
                return monomial.getCoeff();
        }
        return null;
    }

    public Integer firstDegree() {
        for (Monomial monomial : terms) {
            if (Math.abs(monomial.getCoeff()) > 0.00001)
                return monomial.getDegree();
        }
        return -1;
    }

    public Polynomial refactor() {
        terms.removeIf(monomial -> Math.abs(monomial.getCoeff()) < 0.0001);
        return this;
    }

    public boolean isEmpty() {
        return terms.isEmpty();
    }

}
