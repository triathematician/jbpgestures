/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bm.firestorm.functionspace;

/**
 *
 * @author ae3263
 */
public class VectorUtils {

    /** Chops off front part of array. */
    public static double[] subArr(double[] arr, int start) {
        if (arr.length <= start) {
            return new double[]{};
        }
        double[] result = new double[arr.length - start];
        for (int i = start; i < result.length; i++) {
            result[i - start] = arr[i];
        }
        return result;
    }

    /** Computes magnitude of an arbitrary length vector */
    public static double magnitude(double[] vec) {
        double tot = 0;
        for (int i = 0; i < vec.length; i++) {
            tot += vec[i] * vec[i];
        }
        return Math.sqrt(tot);
    }

    /** Computes magnitude of an arbitrary length vector */
    public static double magnitude(double[] vec, int start) {
        return magnitude(subArr(vec, start));
    }

    /** Computes cos of angle between two vectors; okay for vectors to have different lengths. */
    public static double cosTheta(double[] co1, double[] co2) {
        double tot = 0;
        for (int i = 0; i < Math.min(co1.length, co2.length); i++) {
            tot += co1[i] * co2[i];
        }
        return tot / magnitude(co1) / magnitude(co2);
    }

    /**
     * Computes the distance between two vectors, after normalizing them.
     * Okay for them to have different lengths.
     */
    public static double normDistance(double[] co1, double[] co2, int start) {
        return normDistance(subArr(co1, start), subArr(co2, start));
    }

    /**
     * Computes the distance between two vectors, after normalizing them.
     * Okay for them to have different lengths.
     */
    public static double normDistance(double[] co1, double[] co2) {
        double totDiff = 0;
        double tot1 = magnitude(co1);
        double tot2 = magnitude(co2);
        for (int i = 0; i < Math.min(co1.length, co2.length); i++) {
            totDiff += (co2[i]/tot2 - co1[i]/tot1) * (co2[i]/tot2 - co1[i]/tot1);
        }
        if (co1.length > co2.length) {
            for (int i = co2.length; i < co1.length; i++) {
                totDiff += co1[i] * co1[i] / (tot1 * tot1);
            }
        } else if (co2.length > co1.length) {
            for (int i = co1.length; i < co2.length; i++) {
                totDiff += co2[i] * co2[i] / (tot2 * tot2);
            }

        }
        return Math.sqrt(totDiff);
    }

    /** Computes the distance between two vectors, possibly having different lengths. */
    public static double distance(double[] co1, double[] co2) {
        double totDiff = 0;
        for (int i = 0; i < Math.min(co1.length, co2.length); i++) {
            totDiff += (co2[i] - co1[i]) * (co2[i] - co1[i]);
        }
        if (co1.length > co2.length) {
            for (int i = co2.length; i < co1.length; i++) {
                totDiff += co1[i] * co1[i];
            }
        } else if (co2.length > co1.length) {
            for (int i = co1.length; i < co2.length; i++) {
                totDiff += co2[i] * co2[i];
            }
        }
        return Math.sqrt(totDiff);
    }

}
