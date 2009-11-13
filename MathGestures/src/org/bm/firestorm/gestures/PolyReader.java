/*
 * PolyReader.java
 * Created on Oct 24, 2009
 */
package org.bm.firestorm.gestures;

import org.bm.firestorm.functionspace.Function;
import org.bm.firestorm.functionspace.FunctionUtils;
import org.bm.firestorm.functionspace.ONLegendre;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *   Converts a path into polynomial coefficients, using an orthonormal basis
 *   of polynomials, and compares to a library of stored values in order to
 *   produce the most likely response.
 * </p>
 *
 * @author Elisha Peterson
 */
public class PolyReader implements PathReader {
    final static int DEGREE = 15;

    public double[][] convertPath(GeneralPath path) {
        ONLegendre l = new ONLegendre();
        List<Point2D.Double[]> paths = PathUtils.splitPath(path);
        double[][] result = new double[2][DEGREE+1];
        for (Point2D.Double[] p : paths) {
            PathUtils.IFunc pfunc = new PathUtils.IFunc(p);
            result[0] = FunctionUtils.getCoefficients(pfunc.xFunc, l, DEGREE);
            result[1] = FunctionUtils.getCoefficients(pfunc.yFunc, l, DEGREE);
//            System.out.println("plot[List" + Arrays.toString(result[0])+",List"+ Arrays.toString(result[1])+"]");
//            System.out.println(" diffx = " + Arrays.toString(diffPolys(result[0], DEGREE)));
//            int orderx = diffArray(diffPolys(result[0],10));
//            System.out.println(" diffy = " + Arrays.toString(diffPolys(result[1], DEGREE)));
//            int ordery = diffArray(diffPolys(result[1],10));
//            System.out.println(tryToClassify(orderx, ordery));
        }
        return result;
    }

    private String tryToClassify(int orderx, int ordery) {
        if (orderx == 0)
            return "ONE";
        if ((orderx == 3 || orderx == 5) && ordery >= 2 && (ordery % 2 == 0))
            return "ZERO or SIX or NINE";
        if (ordery == 3 && orderx >= 3 && (orderx % 2 == 1))
            return "TWO or SEVEN";
        if ((ordery == 1 || ordery ==3 || ordery == 5) && orderx >= 4 && (orderx % 2 == 0))
            return "THREE";
        if (orderx == 2)
            return "SEVEN";
        if (ordery == 5)
            return "FOUR";
        return "UNKNOWN";
    }

    final static int BASE_VALUE = 50;

    /** Returns difference from the equation of an nth-order polynomial (ignores 0-order). */
    public static double diffPoly(double[] coeffs, int n) {
        double tot = 0.0;
        double dtot = 0.0;
        for (int i = 0; i < coeffs.length; i++) {
            if (i == 0)
                tot += BASE_VALUE * BASE_VALUE;
            else
                tot += coeffs[i] * coeffs[i];
            if (i > n) {
                dtot += coeffs[i] * coeffs[i];
            }
        }
        return 1-Math.sqrt(dtot / tot);
    }

    /** Returns vector with differences from the equation of up to nth-order polynomial. */
    public static double[] diffPolys(double[] coeffs, int nmax) {
        double[] result = new double[nmax];
        for (int i = 0; i < nmax; i++) {
            result[i] = diffPoly(coeffs, i);
        }
        return result;
    }

    /** Determines significant difference in a list of coefficients. */
    public static int diffArray(double[] diffs) {
        double lastDiff=0.04;
        double nextDiff=0.0;
        int estOrder=0;
        if (diffs[0] > 0.95) {
            System.out.println("  high probability of constant");
            estOrder=0;
        }

        for (int i = 1; i < diffs.length; i++) {
            nextDiff = Math.abs(diffs[i] - diffs[i-1]);
            if (nextDiff >= 1.5*lastDiff && nextDiff > .05) {
                System.out.println("  significant diff @ "+(i-1)+"->"+i);
                estOrder = i;
            }
            lastDiff = nextDiff;
        }
        if (diffs[diffs.length - 1] < 0.9) {
            estOrder = diffs.length;
            System.out.println("  order estimated at " + diffs.length + " or higher");
        } else {
            System.out.println("  order estimated at " + estOrder);
        }
        return estOrder;
    }

    public static void main(String[] args) {
        ONLegendre l = new ONLegendre();
        Function f = new Function() { public double getValue(double x) { return Math.cos(3*x)+x*x*x; }; };
        System.out.println(Arrays.toString(FunctionUtils.getCoefficients(f, l, 10)));
    }
}
