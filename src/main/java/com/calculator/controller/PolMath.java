package com.calculator.controller;

import com.calculator.model.Monomial;
import com.calculator.model.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class PolMath {

    private PolMath() {
        throw new IllegalStateException("PolMath is utility class");
    }

    public static Polynomial add(final Polynomial p1, final Polynomial p2) {
        Polynomial res = Builder.polynomial("");
        p1.getTerms().forEach(o -> addMonom(res, o));
        p2.getTerms().forEach(o -> addMonom(res, o));
        return res.refactor().sort();
    }

    public static Polynomial subtract(final Polynomial p1, final Polynomial p2) {
        Polynomial res = Builder.polynomial("");
        p1.getTerms().forEach(o -> addMonom(res, o));
        p2.getTerms().forEach(o -> subtractMono(res, o));
        return res.refactor().sort();
    }

    public static Polynomial multiply(final Polynomial p1, final Polynomial p2) {
        Polynomial res = Builder.polynomial("");
        for (Monomial m1 : p1.getTerms()) {
            for (Monomial m2 : p2.getTerms()) {
                addMonom(res, Builder.monomial(
                        m1.getDegree() + m2.getDegree(),
                        m1.getCoeff() * m2.getCoeff()));
            }
        }
        return res.refactor().sort();
    }

    public static Polynomial multiply(final Polynomial p, final Monomial m) {
        Polynomial res = Builder.polynomial("");
        for (Monomial mono : p.getTerms()) {
            addMonom(res, Builder.monomial(
                    mono.getDegree() + m.getDegree(),
                    mono.getCoeff() * m.getCoeff())
            );
        }
        return res.refactor().sort();
    }

    public static List<Polynomial> divide(Polynomial p1, Polynomial p2) {
        Polynomial res = Builder.polynomial("");
        if (p1.isEmpty() || p2.isEmpty())
            return new ArrayList<>(List.of(res, res));
        Polynomial remainder = p1.sort();
        p2 = p2.sort();
        final Integer tempDegree = p2.firstDegree();
        while (remainder.firstDegree() >= tempDegree) {
            Monomial tempMono = Builder.monomial(
                    remainder.firstDegree() - tempDegree,
                    remainder.firstCoefficient() / p2.firstCoefficient()
            );
            addMonom(res, tempMono);
            remainder = subtract(remainder, multiply(p2, tempMono));
        }
        return new ArrayList<>(List.of(res.refactor().sort(), remainder.refactor().sort()));
    }

    public static Polynomial integralOf(final Polynomial p) {
        Polynomial res = Builder.polynomial("");
        for (Monomial m : p.getTerms()) {
            addMonom(res, Builder.monomial(
                    m.getDegree() + 1,
                    m.getCoeff() / (m.getDegree() + 1)
            ));
        }
        return res.refactor().sort();
    }

    public static Polynomial derivativeOf(final Polynomial p) {
        Polynomial res = Builder.polynomial("");
        for (Monomial m : p.getTerms()) {
            if (m.getDegree() != 0) {
                addMonom(res, Builder.monomial(
                        m.getDegree() - 1,
                        m.getCoeff() * m.getDegree()
                ));
            }
        }
        return res.refactor().sort();
    }

    protected static void addMonom(final Polynomial p, final Monomial m) {
        try {
            Monomial temp = p.getTerms().get(findMonom(p, m));
            temp.setCoeff(temp.getCoeff() + m.getCoeff());
        } catch (IndexOutOfBoundsException e) {
            // avoiding shallow copy
            p.getTerms().add(Builder.monomial(m.getDegree(), m.getCoeff()));
        }
        p.refactor();
    }

    protected static void subtractMono(final Polynomial p, final Monomial m) {
        m.setCoeff(-m.getCoeff());
        addMonom(p, m);
    }

    private static int findMonom(final Polynomial p, final Monomial m) {
        ListIterator<Monomial> iterator = p.getTerms().listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getDegree().equals(m.getDegree())) {
                return iterator.previousIndex();
            }
        }
        return -1;
    }

}
