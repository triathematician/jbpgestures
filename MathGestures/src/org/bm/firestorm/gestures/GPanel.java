/*
 * GPanel.java
 * Created on Oct 15, 2009
 */

package org.bm.firestorm.gestures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.MouseInputListener;

/**
 * <p>
 *  This is a <code>JPanel</code> which stores mouse input gestures. Has a cache that
 *  keeps track of past gestures.
 * </p>
 * @author Elisha Peterson
 */
public class GPanel extends JPanel implements ActionListener, MouseInputListener {

    //
    //
    // PROPERTIES
    //
    //

    /** Stores all previous paths. */
    List<GeneralPath> cachedPaths;

    /** Number of paths to keep stored. */
    int cacheSize = 10;

    /** Stores the last path created. */
    GeneralPath lastPath;

    //
    //
    // CONSTRUCTORS
    //
    //

    /** Construct with defaults. */
    public GPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        cachedPaths = new ArrayList<GeneralPath>();
        lastPath = new GeneralPath();
    }

    //
    //
    // GET/SET
    //
    //

    public GeneralPath getLastPath() {
        return lastPath;
    }

    public void setLastPath(GeneralPath lastPath) {
        this.lastPath = lastPath;
    }

    public List<GeneralPath> getPaths() {
        return cachedPaths;
    }

    public void setPaths(List<GeneralPath> paths) {
        this.cachedPaths = paths;
    }

    public int getMaxPaths() {
        return cacheSize;
    }

    public void setMaxPaths(int maxPaths) {
        this.cacheSize = maxPaths;
    }

    // Path handling

    /** Caches a path and clears the present path. */
    protected void cachePath() {
        if (pathOn) {
            cachedPaths.add(lastPath);
            while (cachedPaths.size() > cacheSize) {
                cachedPaths.remove(0);
            }
            lastPath = new GeneralPath();
        }
    }

    // Paint

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pressed)
            g.setColor(Color.LIGHT_GRAY);
        else
            g.setColor(Color.GRAY);
        for (GeneralPath p : cachedPaths) {
            ((Graphics2D)g).draw(p);
        }
        g.setColor(new Color(128, 0, 0));
        ((Graphics2D)g).draw(lastPath);
    }

    // Actions

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("NEW")) {
            cachePath();
        }
        if (command.equals("CLEAR")) {
            cachedPaths = new ArrayList<GeneralPath>();
        }
    }

    // Mouse listening

    transient Point mousePt;
    transient boolean pressed = false;
    transient boolean pathOn = false;

    public void mousePressed(MouseEvent e) {
        cachePath();
        lastPath.moveTo(e.getPoint().x, e.getPoint().y);
        pathOn = true;
        pressed = true;
    }

    public void mouseDragged(MouseEvent e) {
        lastPath.lineTo(e.getPoint().x, e.getPoint().y);
        pathOn = true;
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        fireStateChanged();
        pressed = false;
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        mousePt = e.getPoint();
        System.out.println(mousePt);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    //
    //
    // EVENT HANDLING
    //
    //

    /**
     * Handles a change event. By default, passes the ChangeEvent along
     * to interested listeners (particularly the parent class), provided this class
     * itself did not originate the event.
     * @param e the change event
     */
    public void stateChanged(ChangeEvent e) {
        if (!e.getSource().equals(this)) {
            changeEvent = e;
            fireStateChanged();
        }
    }

    protected transient ChangeEvent changeEvent = new ChangeEvent(this);
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * Removes a listener from the list of classes receiving <code>ChangeEvent</code>s
     *
     * @param l the listener
     */
    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    /**
     * Adds a listener to receive <code>ChangeEvent</code>s
     *
     * @param l the listener
     */
    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    /**
     * Fires the change event to listeners.
     */
    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    return;
                }
                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }
}
