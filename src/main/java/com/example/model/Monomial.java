package com.example.model;

public class Monomial {
    private final Integer grad;
    private Double coeff;

    public Monomial(Integer grad, Double coeff) {
        this.grad = grad;
        this.coeff = coeff;
    }

    public Monomial(Integer grad, Integer coeff) {
        this(grad, coeff.doubleValue());
    }

    public Integer getGrad() {
        return grad;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }
}