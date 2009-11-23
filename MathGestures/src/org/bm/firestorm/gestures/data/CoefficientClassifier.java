/*
 * CoClassifier.java
 * Created on Oct 24, 2009
 */

package org.bm.firestorm.gestures.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
     * @param context the context
     * @param coeffs the coefficients
     */
    public TrainGesture closestTo(TrainContext context, double[][] coeffs) {
        return Collections.min(database.keySet(), gComparator(new TrainGesture(context, coeffs)));
    }

    //
    // TABLE METHODS
    //


    public static <T> Object[][] getRowData(CoefficientClassifier<T> cocl) {
        final Object[][] rowData = new Object[cocl.database.size()][3];
        int i = 0;
        for (Entry<TrainGesture,T> en : cocl.database.entrySet()) {
            rowData[i] = new Object[] { en.getValue(), en.getKey().context, Arrays.deepToString(en.getKey().arrays) };
            i++;
        }
        return rowData;
    }

    public static final Object[] HEADER = new Object[]{"Value", "Context", "Coefficients"};

    /** Implements a table displaying each entry in the gesture table by its (i) value, (ii) context, (iii) coefficients. */
    public static class GestureTableModel extends DefaultTableModel implements ChangeListener {
        CoefficientClassifier cocl;

        public GestureTableModel(CoefficientClassifier cocl) {
            super(getRowData(cocl), HEADER);
            this.cocl = cocl;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 1;
        }

        public void stateChanged(ChangeEvent e) {
            setDataVector(getRowData(cocl), HEADER);
        }
    }
}
