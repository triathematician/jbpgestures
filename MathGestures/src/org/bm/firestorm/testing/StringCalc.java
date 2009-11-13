/*
 * GestureCalc.java
 * Created on Nov 13, 2009
 */

package org.bm.firestorm.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Uses a stream of input strings to perform calculator commands.
 *
 * @author Jacob Baxter
 * @author Elisha Peterson
 */
public class StringCalc implements ActionListener {

    /** Default constructor. */
    public StringCalc() {
    }

    /** Call this command with a string to handle the next input string. */
    public void handleNextString(String next) {
        // TODO - implement logic here
    }
    
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        handleNextString(s);
    }

}
