/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bm.firestorm.gestures;

import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bm.firestorm.functionspace.Function;

/**
 *
 * @author ae3263
 */
public class PathUtils {

    /** Converts a path into a set of continguous portions of the path. */
    public static List<Point2D.Double[]> splitPath(GeneralPath path) {
        PathIterator pi = path.getPathIterator(null);
        int segType = -1;
        double[] coords = new double[6];
        ArrayList<Point2D.Double[]> result = new ArrayList<Point2D.Double[]>();
        ArrayList<Point2D.Double> curPath = null;
        while (!pi.isDone()) {
            segType = pi.currentSegment(coords);
//            System.out.println("type="+segType+", coords="+Arrays.toString(coords));
            switch(segType) {
                case PathIterator.SEG_MOVETO:
                    if (curPath != null) {
                        result.add(curPath.toArray(new Point2D.Double[]{}));
                    }
                    curPath = new ArrayList<Point2D.Double>();
                    // falls through
                default:
                    curPath.add(new Point2D.Double(coords[0], coords[1]));
            }
            pi.next();
        }
        if (curPath != null) {
            result.add(curPath.toArray(new Point2D.Double[]{}));
        }
        for (Point2D.Double[] sp : result) {
            System.out.println("subPath: " + Arrays.toString(sp));
        }
        return result;
    }

    /**
     * Takes a path specified by a collection of points, and converts the straight-line
     * functions defined by the x and y coordinates into functions.
     */
    public static class IFunc {
        private final Point2D.Double[] path;
        public final Function xFunc;
        public final Function yFunc;

        public IFunc(Point2D.Double[] path) {
            this.path = path;//bounded(path, new double[]{-1, 1});
            xFunc = new Function() {
                public double getValue(double x) {
                    double pos = (IFunc.this.path.length-1)*(x+1)/2.0;
                    int nPos = (int) Math.floor(pos);
                    double percent = pos % 1;
                    if (nPos + 1 < IFunc.this.path.length) {
                        return IFunc.this.path[nPos].x * (1-percent) + IFunc.this.path[nPos+1].x * percent;
                    } else {
                        return IFunc.this.path[nPos].x;
                    }
                }
            };
            yFunc = new Function() {
                public double getValue(double x) {
                    double pos = (IFunc.this.path.length-1)*(x+1)/2.0;
                    int nPos = (int) Math.floor(pos);
                    double percent = pos % 1;
                    if (nPos + 1 < IFunc.this.path.length) {
                        return -(IFunc.this.path[nPos].y * (1-percent) + IFunc.this.path[nPos+1].y * percent);
                    } else {
                        return -IFunc.this.path[nPos].y;
                    }
                }
            };
        }

        /** Converts path's x and y values to be in the specified range. */
        public Point2D.Double[] bounded(Point2D.Double[] path, double[] range) {
            double[] px = {path[0].x, path[0].x};
            double[] py = {path[0].y, path[0].y};
            for (int i = 1; i < path.length; i++) {
                if(path[i].x < px[0]) px[0] = path[i].x;
                if(path[i].x > px[1]) px[1] = path[i].x;
                if(path[i].y < py[0]) py[0] = path[i].y;
                if(path[i].y > py[1]) py[1] = path[i].y;
            }
            Point2D.Double[] result = new Point2D.Double[path.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Point2D.Double(
                        range[0] + (range[1] - range[0]) / (px[1] - px[0]) * (path[i].x - px[0]),
                        range[0] + (range[1] - range[0]) / (py[1] - py[0]) * (path[i].y - py[0])
                );
            }
            return result;
        }
    }

}
