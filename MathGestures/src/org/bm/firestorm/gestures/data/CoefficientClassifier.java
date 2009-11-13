/*
 * CoClassifier.java
 * Created on Oct 24, 2009
 */

package org.bm.firestorm.gestures.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <p>
 *  This interface recognizes coefficients against preset coefficients.
 * </p>
 * @author Elisha Peterson
 */
public class CoefficientClassifier<T> {

    /** Mapping from coefficient arrays to objects. */
    public Map<TrainGesture, T> database;

    public CoefficientClassifier() {
        database = new HashMap<TrainGesture, T>();
    }

    //
    // DELEGATE METHODS
    //

    public int size() {
        return database.size();
    }

    public T remove(TrainGesture key) {
        return database.remove(key);
    }

    public T put(TrainGesture key, T value) {
        return database.put(key, value);
    }

    public T put(double[][] coeffs, T value) {
        return database.put(new TrainGesture(coeffs), value);
    }

    public T put(TrainContext context, double[][] coeffs, T value) {
        return database.put(new TrainGesture(context, coeffs), value);
    }

    public boolean isEmpty() {
        return database.isEmpty();
    }

    public T get(TrainGesture key) {
        return database.get(key);
    }

    public Set<Entry<TrainGesture, T>> entrySet() {
        return database.entrySet();
    }

    public void clear() {
        database.clear();
    }

    //
    // COMPARING METHODS
    //

    /**
     * Creates a sorter based on a specified gesture... the sorter keeps elements
     * in order by the distance from a specified input gesture.
     * @param gesture the gesture to be sorted by
     */
    public static Comparator<TrainGesture> getComparator(final TrainGesture gesture) {
        return new Comparator<TrainGesture>(){
            public int compare(TrainGesture o1, TrainGesture o2) {
                return (int) Math.signum( o1.distance(gesture) - o2.distance(gesture) );
            }
        };
    }

    /** 
     * Computes objects in database that are closest to the specified input gesture.
     * @param gesture the gesture to classify
     * @param nBest the number of results to return
     * @return map containing the top <code>nBest</code> gestures in the database, as
     *   well as the distances these are from the input gesture
     */
    public SortedSet<TrainGesture> sortDatabase(TrainGesture gesture) {
        SortedSet<TrainGesture> result = new TreeSet<TrainGesture>(getComparator(gesture));
        result.addAll(database.keySet());
        return result;
    }

    /**
     * Computes objects in database that are closest to the specified input gesture.
     * @param context the context
     * @param coeffs the coefficients
     * @param nBest the number of results to return
     * @return map containing the top <code>nBest</code> gestures in the database, as
     *   well as the distances these are from the input gesture
     */
    public SortedSet<TrainGesture> sortDatabase(TrainContext trainContext, double[][] context) {
        return sortDatabase(new TrainGesture(trainContext, context));
    }

    /**
     * Returns value in database most closely associated to the provided gesture.
     * @param gesture gesture to classify
     */
    public T getBestValue(TrainGesture gesture) {
        SortedSet<TrainGesture> data = sortDatabase(gesture);
        return database.get(data.last());
    }

    /**
     * Returns value in database most closely associated to the provided gesture.
     * @param context the context
     * @param coeffs the coefficients
     */
    public T getBestValue(TrainContext context, double[][] coeffs) {
        SortedSet<TrainGesture> data = sortDatabase(new TrainGesture(context, coeffs));
        return database.get(data.last());
    }
    
}
