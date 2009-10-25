/**
 * OrthogonalPolynomials.java
 * Created on Oct 24, 2009
 */
package org.bm.firestorm.functionspace;

/**
 * <p>
 *   Used to prescribe a space of orthogonal polynomials.
 * </p>
 */
public interface OrthogonalPolynomials {

    /** Returns the value of the nth basis function at specified input. */
    public double getValue(int n, double x);

    /** Returns the range for which the space is orthogonal. */
    public double[] getRange();

}
