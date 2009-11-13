/*
 * FunPanel1.java
 * Created on Oct 15, 2009
 */

package org.bm.firestorm.gestures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import org.bm.firestorm.functionspace.Function;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import org.bm.firestorm.functionspace.FunctionUtils.CFunction;

/**
 * <p>
 *  This is a <code>GeneralPath</code> which represents a parametric function.
 * </p>
 * @author Elisha Peterson
 */
public class ParametricPathPanel extends JPanel {

    //
    //
    // PROPERTIES
    //
    //

    /** Function */
    Function fx;

    /** Function */
    Function fy;

    /** Range */
    double[] range = new double[]{-1,1};

    /** Samples */
    int samples = 500;

    //
    //
    // CONSTRUCTORS
    //
    //

    public ParametricPathPanel() {
    }

    public ParametricPathPanel(Function fx, Function fy) {
        this.fx = fx;
        this.fy = fy;
    }

    //
    //
    // GET/SET
    //
    //

    public Function getFX() {
        return fx;
    }

    public void setFX(Function fx) {
        this.fx = fx;
    }

    public Function getFY() {
        return fy;
    }

    public void setFY(Function fy) {
        this.fy = fy;
    }

    public void setFunctions(Function fx, Function fy) {
        setFX(fx);
        setFY(fy);
    }

    public double[] getRange() {
        return range;
    }

    public void setRange(double[] range) {
        this.range = range;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    // Paint

    transient GeneralPath path;
    
    public GeneralPath getPath() {
        return path;
    }

    protected void recompute() {
        path = new GeneralPath();
        float x = (float) fx.getValue(range[0]);
        float y = (float) fy.getValue(range[0]);
        path.moveTo(x/2, -y/2);
        double step = (range[1] - range[0]) / (samples - 1);
        for (int i = 0; i < samples; i++) {
            x = (float) fx.getValue(range[0] + i * step);
            y = (float) fy.getValue(range[0] + i * step);
            path.lineTo(x/2, -y/2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fx != null && fy != null) {
            recompute();
            if (path != null)
                ((Graphics2D)g).draw(path);
        }
    }
}
