/*
 * TrainGesture.java
 * Created on Nov 13, 2009
 */

package org.bm.firestorm.gestures.data;

import org.bm.firestorm.functionspace.VectorUtils;

/**
 * <p>
 *  Stores a trained value of a gesture... associates a context and an array of numbers
 *  (possibly multiple such arrays) with a <code>String</code>.
 * </p>
 * @author Elisha Peterson
 */
public class TrainGesture {
    /** Stores context of the gesture. */
    TrainContext context = TrainContext.NONE;
    /** Stores arrays of the gesture. First array is x coords, second is y coords. */
    double[][] arrays;

    /**
     * Construct with specified information, without a training context
     * @param arrays the arrays containing the numeric data for the gesture
     */
    public TrainGesture(double[][] arrays) {
        this.arrays = arrays;
    }

    /**
     * Construct with specified information.
     * @param context the training context, e.g. letters/numbers/etc.
     * @param arrays the arrays containing the numeric data for the gesture
     */
    public TrainGesture(TrainContext context, double[][] arrays) {
        this.context = context;
        this.arrays = arrays;
    }

    //
    // GET/SET PATTERNS
    //

    public double[][] getArrays() {
        return arrays;
    }

    public void setArrays(double[][] arrays) {
        this.arrays = arrays;
    }

    public TrainContext getContext() {
        return context;
    }

    public void setContext(TrainContext context) {
        this.context = context;
    }

    //
    // COMPUTATIONS
    //

    /** 
     * Returns the distance to another gesture. Does this by pairwise-comparison
     * of the associated coefficient arrays. If no array is supplied, the array is
     * assumed to be zero.
     *
     */
    public double distance(TrainGesture gesture) {
        double totDist = 0;
        for (int i = 0; i < Math.min(arrays.length, gesture.arrays.length); i++) {
            totDist += VectorUtils.distance(arrays[i], gesture.arrays[i]);
        }
        if (arrays.length > gesture.arrays.length) {
            for (int i = gesture.arrays.length; i < arrays.length; i++) {
                totDist += VectorUtils.magnitude(arrays[i]);
            }
        } else if (gesture.arrays.length > arrays.length) {
            for (int i = arrays.length; i < gesture.arrays.length; i++) {
                totDist += VectorUtils.magnitude(gesture.arrays[i]);
            }            
        }   
        return totDist;
    }
}
