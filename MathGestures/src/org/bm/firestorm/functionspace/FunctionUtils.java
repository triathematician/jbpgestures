/*
 * FunctionUtils.java
 * Created on Oct 26, 2009
 */

package org.bm.firestorm.functionspace;

/**
 * <p>
 * Contains utility static methods for computations with functions.
 * </p>
 * @author ae3263
 */
public class FunctionUtils {

    //
    //
    // GENERAL FUNCTION SPACES
    //
    //

    /** 
     * Returns the inner product of two functions, by integrating their product over the specified range.
     * Uses numeric integration.
     *
     * @param f1 first function
     * @param f2 second function
     * @param range range of comparison
     *
     * @return value representing the inner product
     */
    public static double innerProduct(Function f1, Function f2, double[] range) {
        double step = (range[1] - range[0]) / 1000.0;
        double result = 0.0;
        double val0 = 0.0;
        double val1 = 0.0;
        for (int i = 0; i < 1000; i++) {
            val0 = f1.getValue(range[0] + i * step) * f2.getValue(range[0] + i * step);
            val1 = f1.getValue(range[0] + (i + 1) * step) * f2.getValue(range[0] + (i + 1) * step);
            result += step * (val0 + val1) / 2.0;
        }
        return result;
    }

    /**
     * Returns the distance between two functions over the specified range.
     * Specifically, it is (integral_range |f1-f2|^p dx)^(1/p)
     * Uses numeric integration.
     *
     * @param f1 first function
     * @param f2 second function
     * @param range range of comparison
     * @param p power of the norm command
     *
     * @return value representing the distance
     */
    public static double lpDistance(Function f1, Function f2, double[] range, double p) {
        double step = (range[1] - range[0]) / 1000.0;
        double result = 0.0;
        double val0 = 0.0;
        double val1 = 0.0;
        for (int i = 0; i < 1000; i++) {
            val0 = Math.pow(Math.abs(f1.getValue(range[0] + i * step) - f2.getValue(range[0] + i * step)), p);
            val1 = Math.pow(Math.abs(f1.getValue(range[0] + (i + 1) * step) - f2.getValue(range[0] + (i + 1) * step)), p);
            result += step * (val0 + val1) / 2.0;
        }
        return Math.pow(result, 1/p);
    }

    //
    //
    // ORTHOGONAL POLYNOMIAL STUFF
    //
    //

    /**
     * Returns function for the nth basis element of a space of orthogonal polynomials
     *
     * @param op the family of orthogonal polynomials
     * @param n the degree of polynomial to retrieve
     *
     * @return function associated with the nth polynomial in specified family
     */
    public static Function getFunction(final OrthogonalPolynomials op, final int n) {
        return new Function() {
            public double getValue(double x) {
                return op.getValue(n, x);
            }
        };
    }

    /**
     * Computes coefficients up through the specified degree of the function with respect to the
     * given basis of orthogonal polynomials.
     *
     * @param f the function
     * @param op family of orthogonal polynomails
     * @param n
     * @return
     */
    public static double[] getCoefficients(Function f, OrthogonalPolynomials op, int n) {
        double[] result = new double[n+1];
        for (int i = 0; i < result.length; i++) {
            result[i] = innerProduct(f, getFunction(op, i), op.getRange());
        }
        return result;
    }

    /**
     * Given an array of coefficients, represents the function associated with those coefficients
     * for the given space of orthogonal polynomials.
     */
    public static class CFunction implements Function {
        OrthogonalPolynomials op;
        double[] coeffs;

        public CFunction(OrthogonalPolynomials op, double[] coeffs) {
            this.op = op;
            this.coeffs = coeffs;
        }

        public double getValue(double x) {
            double result = 0.0;
            for (int i = 0; i < coeffs.length; i++) {
                result += op.getValue(i, x) * coeffs[i];
            }
            return result;
        }
    }
}
