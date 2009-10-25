/*
 * PathReader.java
 * Created on Oct 23, 2009
 */

package org.bm.firestorm.gestures;

import java.awt.geom.GeneralPath;

/**
 * <p>
 *   This class converts paths into recognizable gestures/actions.
 * </p>
 *
 * @param <T> the type of object returned by the main algorithm
 *
 * @author Elisha Peterson
 */
public interface PathReader<T> {

    /** 
     * Algorithm to convert a path into an object.
     * @param path the path to convert into gestures or actions
     * @return an object T of the specified type.
     */
    public T convertPath(GeneralPath path);

}
