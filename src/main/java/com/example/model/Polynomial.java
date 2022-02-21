package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private final List<Monomial> terms;

    public Polynomial() {
        this.terms = new ArrayList<>();
    }

    public void addMonom(Monomial m) {
        this.terms.add(m);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polynomial=");
        for (Monomial m : this.terms) {
            sb.append(m.getCoeff()).append("X^").append(m.getGrad()).append("+");
        }
        return sb.substring(0, sb.length()-1);
    }

    public List<Monomial> getTerms() {
        return terms;
    }
}
