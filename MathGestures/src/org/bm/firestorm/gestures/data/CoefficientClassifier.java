/*
 * CoClassifier.java
 * Created on Oct 24, 2009
 */

package org.bm.firestorm.gestures.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <p>
 *  This interface recognizes coefficients against preset coefficients.
 * </p>
 * @author Elisha Peterson
 */
public class CoefficientClassifier<T> implements java.io.Serializable {

    /** Mapping from coefficient arrays to objects. */
    public TreeMap<TrainGesture, T> database;

    public CoefficientClassifier() {
        database = new TreeMap<TrainGesture, T>();
    }

    //
    // DELEGATE METHODS
    //

    /** @return size of database, i.e. number of trained gestures. */
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

    public void putAll(TreeMap moreGestures) {
        database.putAll(moreGestures);
    }

    public boolean isEmpty() {
        return database.isEmpty();
    }

    public T get(TrainGesture key) {
        return database.get(key);
    }

    public Entry<TrainGesture, T> get(int index) {
        int i = 0;
        for (Entry<TrainGesture, T> entry : database.entrySet()) {
            if (i == index) return entry;
            i++;
        }
        return null;
    }

    public Set<Entry<TrainGesture, T>> entrySet() {
        return database.entrySet();
    }

    public TreeMap<TrainGesture, T> getDatabase() {
        return database;
    }

    public void setDatabase(TreeMap<TrainGesture, T> database) {
        this.database = database;
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
    public static Comparator<TrainGesture> gComparator(final TrainGesture gesture) {
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
        SortedSet<TrainGesture> result = new TreeSet<TrainGesture>(gComparator(gesture));
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
    public TrainGesture closestTo(TrainGesture gesture) {
        return Collections.min(database.keySet(), gComparator(gesture));
    }

    /**
     * Returns value in database most closely associated to the provided gesture.
     * @param gesture gesture to classify
     */
    public TrainGesture[] nClosestTo(int n, TrainGesture gesture) {
        TrainGesture[] sortedArray = sortDatabase(gesture).toArray(new TrainGesture[]{});
        if (sortedArray.length < n)
            return sortedArray;
        TrainGesture[] result = new TrainGesture[n];
        for (int i = 0; i < n; i++)
            result[i] = sortedArray[i];
        return result;
    }

    /**
     * Returns value in database most closely associated to the provided gesture.
     * @param context the context
     * @param coeffs the coefficients
     */
    public TrainGesture closestTo(TrainContext context, double[][] coeffs) {
        return Collections.min(database.keySet(), gComparator(new TrainGesture(context, coeffs)));
    }
}
