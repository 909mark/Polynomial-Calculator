package com.calculator.model;

import java.util.Objects;

public class Monomial implements Comparable<Monomial> {
    private final Integer degree;
    private Double coeff;

    public Monomial(Integer degree, Double coeff) {
        this.degree = degree;
        this.coeff = coeff;
    }

    public Monomial(Integer degree, Integer coeff) {
        this(degree, coeff.doubleValue());
    }

    public Integer getDegree() {
        return degree;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }

    @Override
    public int compareTo(Monomial o) {
        return o.getDegree() - this.getDegree();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return Objects.equals(this.getDegree(), ((Monomial) obj).getDegree()) &&
                Math.abs(this.getCoeff() - ((Monomial) obj).getCoeff()) <= 0.000001;
    }
}
