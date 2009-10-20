/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bm.firestorm.gestures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author ae3263
 */
public class GPanel extends JPanel implements MouseInputListener {

    GeneralPath path;

    public GPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        path = new GeneralPath();
    }


    // Paint

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).draw(path);
    }

    // Mouse listening

    transient Point mousePt;
    transient boolean pressed = false;

    public void mouseClicked(MouseEvent e) {
        mousePt = e.getPoint();
        System.out.println(mousePt);
    }

    public void mousePressed(MouseEvent e) {
        path.moveTo(e.getPoint().x, e.getPoint().y);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        path.lineTo(e.getPoint().x, e.getPoint().y);
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

}
