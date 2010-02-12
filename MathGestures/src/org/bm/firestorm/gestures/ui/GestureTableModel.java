/**
 * GestureTableModel.java
 * Created on Dec 8, 2009
 */
package org.bm.firestorm.gestures.ui;

import org.bm.firestorm.gestures.data.*;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;

/**
 * <p>
 *    This class ...
 * </p>
 * @author Elisha Peterson
 */
public class GestureTableModel extends AbstractTableModel {

    public static final String[] HEADER = new String[]{"Value", "Context", "Coefficients"};

    CoefficientClassifier cocl;

    public GestureTableModel(CoefficientClassifier cocl) {
        this.cocl = cocl;
    }

    public int getRowCount() {
        return cocl.size();
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch(col) {
            case 0: return String.class;
            case 1: return TrainContext.class;
            case 2: return TrainGesture.class;
        }
        return Object.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Entry<TrainGesture, ?> en = cocl.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return en.getValue();
            case 1:
                return en.getKey().context;
            case 2:
                return en.getKey();
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        if (col != 1) return;
        if ( ! (aValue instanceof TrainContext)) return;
        ((TrainGesture)cocl.get(row).getKey()).setContext((TrainContext) aValue);
        fireTableCellUpdated(row, col);
    }
}

