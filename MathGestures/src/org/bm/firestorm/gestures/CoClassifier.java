/*
 * CoClassifier.java
 * Created on Oct 24, 2009
 */

package org.bm.firestorm.gestures;

import org.bm.firestorm.functionspace.VectorUtils;
import java.util.Map;

/**
 * <p>This interface recognizes coefficients against preset coefficients.</p>
 * @author ae3263
 */
public abstract class CoClassifier<T> {

    /** Retrieves mapping from coefficient arrays to objects. */
    abstract public Map<double[][], T> getMapped();

    /** 
     * Finds object in database that is closest to the specified coefficients.
     * 
     * @param coeffx
     * @param coeffy
     * @param base scale to assume in calculations
     * @return
     */
    public T classify(double[] coeffx, double[] coeffy, double base) {
        double[][] bestCoeff = null;
        double bestValue = Double.MAX_VALUE;
        for (double[][] arr : getMapped().keySet()) {
            System.out.println(" testing "+getMapped().get(arr));
            double dist = VectorUtils.distance(arr[0], coeffx) + VectorUtils.distance(arr[1], coeffy);
            System.out.println("   [dx,dy] = ["+VectorUtils.distance(arr[0], coeffx)+","+VectorUtils.distance(arr[1], coeffy)+"]");
            dist += VectorUtils.cosTheta(arr[0], coeffx) + VectorUtils.cosTheta(arr[1], coeffy);
            System.out.println("   [thx,thy] = ["+VectorUtils.cosTheta(arr[0], coeffx)+","+VectorUtils.cosTheta(arr[1], coeffy)+"]");
            System.out.println("   total dist = " + dist);
            if (dist < bestValue) {
                bestValue = dist;
                bestCoeff = arr;
            }
        }
        return getMapped().get(bestCoeff);
    }

    
}
