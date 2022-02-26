package com.example.controller;

import com.example.model.Monomial;
import com.example.model.Polynomial;

import java.util.ListIterator;


public class PolMath {

    private PolMath() {
        throw new IllegalStateException("PolMath is utility class");
    }

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        for (Monomial m : p1.getTerms()) {
            addMonom(res, m);
        }
        for (Monomial m : p2.getTerms()) {
            addMonom(res, m);
        }
        return res;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        for (Monomial m : p1.getTerms()) {
            addMonom(res, m);
        }
        for (Monomial m : p2.getTerms()) {
            subtractMono(res, m);
        }
        return res;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();
        for (Monomial m1 : p1.getTerms()) {
            for (Monomial m2 : p2.getTerms()) {
                PolMath.addMonom(res,
                        new Monomial(
                                m1.getGrad() + m2.getGrad(),
                                m1.getCoeff() * m2.getCoeff()));
            }
        }
        return res;
    }

    public static Polynomial integralOf(Polynomial p) {
        Polynomial res = new Polynomial();
        for (Monomial m : p.getTerms()) {
            res.getTerms().add(new Monomial(
                    m.getGrad() + 1,
                    m.getCoeff() / (m.getGrad() + 1)
            ));
        }
        return res;
    }

    public static Polynomial derivativeOf(Polynomial p) {
        Polynomial res = new Polynomial();
        for (Monomial m : p.getTerms()) {
            if (m.getGrad() != 0) {
                res.getTerms().add(new Monomial(
                        m.getGrad() - 1,
                        m.getCoeff() * m.getGrad()
                ));
            }
        }
        return res;
    }

    public static void addMonom(Polynomial p, Monomial m) {
        try {
            Monomial temp = p.getTerms().get(containsMonom(p, m));
            temp.setCoeff(temp.getCoeff() + m.getCoeff());
        } catch (IndexOutOfBoundsException e) {
            // avoiding shallow copy
            p.getTerms().add(new Monomial(m.getGrad(), m.getCoeff()));
        }
    }

    public static void subtractMono(Polynomial p, Monomial m) {
        try {
            Monomial temp = p.getTerms().get(containsMonom(p, m));
            temp.setCoeff(temp.getCoeff() - m.getCoeff());
        } catch (IndexOutOfBoundsException e) {
            // creating new Monomial to avoiding shallow copy
            p.getTerms().add(new Monomial(m.getGrad(), -m.getCoeff()));
        }
    }

    private static int containsMonom(Polynomial p, Monomial m) {
        ListIterator<Monomial> iterator = p.getTerms().listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getGrad().equals(m.getGrad())) {
                return iterator.nextIndex() - 1;
            }
        }
        return -1;
    }

}
