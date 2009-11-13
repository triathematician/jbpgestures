/*
 * GContexts.java
 * Created on Oct 31, 2009
 */

package org.bm.firestorm.gestures.data;

/**
 *
 * @author ae3263
 */
public enum TrainContext {
    NONE ("No Context"),
    NUMBERS ("Number"),
    LOWERCASE ("Lowercase"),
    UPPERCASE ("Uppercase"),
    CURRENCY ("Currency"),
    WEB ("Web"),
    SYMBOLS ("Symbols"),
    MATH ("Math"),
    SHAPES ("Shapes");

    String name;

    TrainContext(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
