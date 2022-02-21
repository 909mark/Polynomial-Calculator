package com.example.controller;

import com.example.model.Monomial;
import com.example.model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class PolMath {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        ArrayList<Monomial> p1Terms = (ArrayList<Monomial>) p1.getTerms();
        ArrayList<Monomial> p2Terms = (ArrayList<Monomial>) p2.getTerms();
        int p1it = p1Terms.size();
        int p2it = p2Terms.size();
        int grad = Math.max(p1Terms.get(0).getGrad(), p2Terms.get(0).getGrad());
        while (grad >= 0) {
            if (p1Terms.get(p1it).getGrad() == grad && p2Terms.get(p2it).getGrad() == grad) {
                res.addMonom(new Monomial(grad, p1Terms.get(p1it).getCoeff() + p2Terms.get(p2it).getCoeff()));
            }

            grad--;
        }
        return res;
    }
}
